package com.example.a2223damp3grup01.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.objects.TipoSub;

import java.util.List;

public class TipoSubAdapter extends RecyclerView.Adapter<TipoSubAdapter.MyViewHolder> {
    private List<TipoSub> tipoSubs;

    public TipoSubAdapter(List<TipoSub> tipoSubs) {
        this.tipoSubs = tipoSubs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tipo_subministrament,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(tipoSubs.get(position));

    }

    @Override
    public int getItemCount() {
        return tipoSubs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView preu;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nom= itemView.findViewById(R.id.tipoTxt);
            preu = itemView.findViewById(R.id.preu);
        }
        void bindData(final TipoSub tipoSub){
            nom.setText(tipoSub.getNom());
            preu.setText(tipoSub.getPreu());
        }
    }
}
