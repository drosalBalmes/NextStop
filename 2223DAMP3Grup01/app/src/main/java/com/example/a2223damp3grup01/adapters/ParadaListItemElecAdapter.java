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
import com.example.a2223damp3grup01.objects.PuntRecarrega;

import java.util.List;

public class ParadaListItemElecAdapter extends RecyclerView.Adapter<ParadaListItemElecAdapter.MyViewHolder>{

    private List<PuntRecarrega> punts;

    public ParadaListItemElecAdapter(List<PuntRecarrega> punts) {
        this.punts = punts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParadaListItemElecAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_parades_item_elec,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(punts.get(position));

    }

    @Override
    public int getItemCount() {
        return punts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView numReviews;
        RatingBar barraRating;
        TextView minutsTV;
        Button nuttonAdd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomElectrolinera);
            numReviews = (TextView) itemView.findViewById(R.id.reviewsETelec);
            barraRating = (RatingBar) itemView.findViewById(R.id.ratingBarELEC);
            minutsTV = (TextView) itemView.findViewById(R.id.minutsCotxeelec);
            nuttonAdd = (Button) itemView.findViewById(R.id.addButtonelec);

        }

        void bindData(final PuntRecarrega pr){
            nom.setText(pr.getNom());
            minutsTV.setText("+"+pr.getDistFromActual() + " Min");

        }
    }
}
