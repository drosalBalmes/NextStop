package com.example.a2223damp3grup01.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.objects.Benzinera;

import java.util.List;

public class ParadaListItemBenzAdapter extends RecyclerView.Adapter<ParadaListItemBenzAdapter.MyViewHolder> {
    private List<Benzinera> benzineres;

    public ParadaListItemBenzAdapter(List<Benzinera> benzineres) {
        this.benzineres = benzineres;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_parades_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(benzineres.get(position));
    }

    @Override
    public int getItemCount() {
        return benzineres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView preuBenzina;
        TextView preuGasoil;
        TextView numReviews;
        RatingBar barraRating;
        TextView minutsTV;
        Button nuttonAdd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomBenzinera);
            preuBenzina = (TextView) itemView.findViewById(R.id.priceBenzina);
            preuGasoil = (TextView) itemView.findViewById(R.id.priceGasoil);
            numReviews = (TextView) itemView.findViewById(R.id.reviewsET);
            barraRating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            minutsTV = (TextView) itemView.findViewById(R.id.minutsCotxe);
            nuttonAdd = (Button) itemView.findViewById(R.id.addButton);
        }
        void bindData(final Benzinera benzinera){
            nom.setText(benzinera.getNom());
            minutsTV.setText("+"+benzinera.getDistFromActual() + " Min");

        }
    }

}
