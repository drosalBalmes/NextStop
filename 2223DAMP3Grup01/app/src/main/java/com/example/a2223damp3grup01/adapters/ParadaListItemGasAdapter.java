package com.example.a2223damp3grup01.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.interfaces.SelectListenerListItemBenz;
import com.example.a2223damp3grup01.objects.Benzinera;

import java.util.List;

public class ParadaListItemGasAdapter extends RecyclerView.Adapter<ParadaListItemGasAdapter.MyViewHolder> {
    private List<Benzinera> benzineres;

    private SelectListenerListItemBenz listener;

    public ParadaListItemGasAdapter(List<Benzinera> benzineres) {
        this.benzineres = benzineres;
    }

    public ParadaListItemGasAdapter(List<Benzinera> benzineres, SelectListenerListItemBenz listener) {
        this.benzineres = benzineres;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParadaListItemGasAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_parades_item_gas,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(benzineres.get(position));

        holder.nuttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickedBenzineraItem(benzineres.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return benzineres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView gasTypeTV;
        TextView preuGas;
        TextView numReviews;
        RatingBar barraRating;
        TextView minutsTV;
        Button nuttonAdd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomBenzineraGAS);
            gasTypeTV = (TextView) itemView.findViewById(R.id.gasTV);
            preuGas = (TextView) itemView.findViewById(R.id.priceGas);
            numReviews = (TextView) itemView.findViewById(R.id.reviewsTVGAS);
            barraRating = (RatingBar) itemView.findViewById(R.id.ratingBarGAS);
            minutsTV = (TextView) itemView.findViewById(R.id.minutsCotxeGAS);
            nuttonAdd = (Button) itemView.findViewById(R.id.addButtonGAS);
        }
        void bindData(final Benzinera benzinera){
            nom.setText(benzinera.getNom());
            gasTypeTV.setText(benzinera.getTypeGAS());
            if (benzinera.getTypeGAS().equals("GNC")){
                preuGas.setText(String.valueOf(benzinera.getPreuGNC()));
            }if (benzinera.getTypeGAS().equals("GLP")){
                preuGas.setText(String.valueOf(benzinera.getPreuGLP()));

            }if (benzinera.getTypeGAS().equals("GNL")){
                preuGas.setText(String.valueOf(benzinera.getPreuGNL()));

            }
            minutsTV.setText("+"+benzinera.getDistFromActual() + " Min");

            numReviews.setText(benzinera.getNumReviews() + " Reviews");
            if (benzinera.getMitjaReviews() != null) {
                barraRating.setRating(benzinera.getMitjaReviews().floatValue());
            }
        }
    }
}
