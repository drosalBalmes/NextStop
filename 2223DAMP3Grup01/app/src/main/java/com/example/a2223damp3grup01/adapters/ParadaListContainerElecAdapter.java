package com.example.a2223damp3grup01.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.interfaces.SelectListenerListItemELEC;
import com.example.a2223damp3grup01.objects.ParadaListItemBENZ;
import com.example.a2223damp3grup01.objects.ParadaListItemELEC;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class ParadaListContainerElecAdapter extends RecyclerView.Adapter<ParadaListContainerElecAdapter.MyViewHolder> {

    List<ParadaListItemELEC> paradesAmbLlista;

    public ParadaListContainerElecAdapter(List<ParadaListItemELEC> paradesAmbLlista) {
        this.paradesAmbLlista = paradesAmbLlista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParadaListContainerElecAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_parades_container_elec,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(paradesAmbLlista.get(position));

    }

    @Override
    public int getItemCount() {
        return paradesAmbLlista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements SelectListenerListItemELEC {
        TextView numParada;
        RecyclerView estacions;
        ParadaListItemELEC paradaa;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            numParada = (TextView) itemView.findViewById(com.example.a2223damp3grup01.R.id.textParadaELEC);
            estacions = (RecyclerView) itemView.findViewById(com.example.a2223damp3grup01.R.id.estacionsParadasELEC);
        }

        void bindData(final ParadaListItemELEC parada) {
            paradaa=parada;
            numParada.setText(parada.getNumeroParada());
            ParadaListItemElecAdapter adapter = new ParadaListItemElecAdapter(parada.getPuntsList(),this);
            estacions.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            estacions.setAdapter(adapter);


        }

        @Override
        public void onItemClickedPuntItem(PuntRecarrega puntRecarrega) {

            Toast.makeText(itemView.getContext(), puntRecarrega.getNom()+" sera la parada "+String.valueOf(paradesAmbLlista.indexOf(paradaa)+1) , Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = itemView.getContext().getSharedPreferences("ruta_final", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            LatLng latLng = new LatLng(puntRecarrega.getLatitude(),puntRecarrega.getLongitude());
            String latLngString = latLng.latitude + "," + latLng.longitude;


            Log.d("haberbaher", "onItemClickedBenzineraItem: " + "parada" + String.valueOf(paradesAmbLlista.indexOf(paradaa)));
            editor.putString("parada" + String.valueOf(paradesAmbLlista.indexOf(paradaa)), latLngString);
            editor.apply();


            Log.d("haberbaher", "onItemClickedBenzineraItem: "+prefs.getString("parada" +String.valueOf(paradesAmbLlista.indexOf(puntRecarrega)),""));


        }
    }
}
