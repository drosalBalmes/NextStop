package com.example.a2223damp3grup01.adapters;

import static android.os.Build.VERSION_CODES.R;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.EstacioBenz;
import com.example.a2223damp3grup01.objects.ParadaListItemBENZ;

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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView numParada;
        RecyclerView estacions;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            numParada = (TextView) itemView.findViewById(com.example.a2223damp3grup01.R.id.textParada);
            estacions = (RecyclerView) itemView.findViewById(com.example.a2223damp3grup01.R.id.estacionsParadas);
        }

        void bindData(final ParadaListItemBENZ parada){
            if (parada.getType()==1){
                numParada.setBackgroundResource(com.example.a2223damp3grup01.R.color.orange);
                numParada.setText(parada.getNumeroParada());
                ParadaListItemBenzAdapter adapter = new ParadaListItemBenzAdapter(parada.getBenzList());
                estacions.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
                estacions.setAdapter(adapter);

            }else if(parada.getType()==2){
                numParada.setBackgroundResource(com.example.a2223damp3grup01.R.color.secondary);
                numParada.setText(parada.getNumeroParada());
                ParadaListItemGasAdapter adapter = new ParadaListItemGasAdapter(parada.getBenzList());
                estacions.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
                estacions.setAdapter(adapter);
            }


        }
    }

}
