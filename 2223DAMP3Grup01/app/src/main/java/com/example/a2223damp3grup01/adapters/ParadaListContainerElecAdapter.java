package com.example.a2223damp3grup01.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.objects.ParadaListItemBENZ;
import com.example.a2223damp3grup01.objects.ParadaListItemELEC;

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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView numParada;
        RecyclerView estacions;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            numParada = (TextView) itemView.findViewById(com.example.a2223damp3grup01.R.id.textParadaELEC);
            estacions = (RecyclerView) itemView.findViewById(com.example.a2223damp3grup01.R.id.estacionsParadasELEC);
        }

        void bindData(final ParadaListItemELEC parada) {

            numParada.setText(parada.getNumeroParada());
            ParadaListItemElecAdapter adapter = new ParadaListItemElecAdapter(parada.getPuntsList());
            estacions.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            estacions.setAdapter(adapter);


        }
    }
}
