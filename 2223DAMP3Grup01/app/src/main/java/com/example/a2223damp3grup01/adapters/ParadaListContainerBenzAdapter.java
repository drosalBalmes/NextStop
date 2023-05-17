package com.example.a2223damp3grup01.adapters;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.interfaces.SelectListenerListItemBenz;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.EstacioBenz;
import com.example.a2223damp3grup01.objects.ParadaListItemBENZ;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
public class ParadaListContainerBenzAdapter extends RecyclerView.Adapter<ParadaListContainerBenzAdapter.MyViewHolder>{


    List<ParadaListItemBENZ> paradesAmbLlista;

    public ParadaListContainerBenzAdapter(List<ParadaListItemBENZ> paradesAmbLlista) {
        this.paradesAmbLlista = paradesAmbLlista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(com.example.a2223damp3grup01.R.layout.rv_list_parades_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(paradesAmbLlista.get(position));
    }

    @Override
    public int getItemCount() {
        return paradesAmbLlista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements SelectListenerListItemBenz {
        TextView numParada;
        RecyclerView estacions;
        ParadaListItemBENZ paradaa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            numParada = (TextView) itemView.findViewById(com.example.a2223damp3grup01.R.id.textParada);
            estacions = (RecyclerView) itemView.findViewById(com.example.a2223damp3grup01.R.id.estacionsParadas);
        }

        void bindData(final ParadaListItemBENZ parada){
            paradaa=parada;
            if (parada.getType()==1){
                numParada.setBackgroundResource(com.example.a2223damp3grup01.R.color.orange);
                numParada.setText(parada.getNumeroParada());
                ParadaListItemBenzAdapter adapter = new ParadaListItemBenzAdapter(parada.getBenzList(),this);
                estacions.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
                estacions.setAdapter(adapter);

            }else if(parada.getType()==2){
                numParada.setBackgroundResource(com.example.a2223damp3grup01.R.color.secondary);
                numParada.setText(parada.getNumeroParada());
                ParadaListItemBenzAdapter adapter = new ParadaListItemBenzAdapter(parada.getBenzList(),this);
                estacions.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
                estacions.setAdapter(adapter);
            }



        }
        @Override
        public void onItemClickedBenzineraItem(Benzinera benzinera) {
            Toast.makeText(itemView.getContext(), benzinera.getNom()+" sera la parada "+String.valueOf(paradesAmbLlista.indexOf(paradaa)+1) , Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = itemView.getContext().getSharedPreferences("ruta_final", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            LatLng latLng = new LatLng(benzinera.getLatitude(),benzinera.getLongitude());
            String latLngString = latLng.latitude + "," + latLng.longitude;


            Log.d("haberbaher", "onItemClickedBenzineraItem: " + "parada" + String.valueOf(paradesAmbLlista.indexOf(paradaa)));
            editor.putString("parada" + String.valueOf(paradesAmbLlista.indexOf(paradaa)), latLngString);
            editor.apply();


            Log.d("haberbaher", "onItemClickedBenzineraItem: "+prefs.getString("parada" +String.valueOf(paradesAmbLlista.indexOf(paradaa)),""));

        }

    }

}
