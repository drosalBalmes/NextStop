package com.example.a2223damp3grup01.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.interfaces.SelectListenerGaso;
import com.example.a2223damp3grup01.objects.Benzinera;

import java.util.List;

public class BenzineresAdapter extends RecyclerView.Adapter<BenzineresAdapter.MyViewHolder> {
    private List<Benzinera> benzineres;
    private SelectListenerGaso listener;

    public BenzineresAdapter(List<Benzinera> benzineres, SelectListenerGaso listener) {
        this.benzineres = benzineres;
        this.listener = listener;
    }

    public BenzineresAdapter(List<Benzinera> benzineres){
        this.benzineres = benzineres;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_benzineres_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(benzineres.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickedBenzinera(benzineres.get(position));
            }
        });
    }

    @Override
    public int getItemCount(){
        return benzineres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView gasolinaPreu;
        TextView gasoilPreu;
        TextView hidroPreu;
        TextView dist;
        RatingBar rating;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nom = itemView.findViewById(R.id.nomTxt);
            gasoilPreu = itemView.findViewById(R.id.gasoilPreu);
            gasolinaPreu = itemView.findViewById(R.id.gasolinaPreu);
            hidroPreu = itemView.findViewById(R.id.hidroPreu);
            dist = itemView.findViewById(R.id.distancia);
            rating = itemView.findViewById(R.id.ratingBar);
            cardView = itemView.findViewById(R.id.cardBenzineres);
        }
        void bindData(final Benzinera benzinera){
            nom.setText(benzinera.getNom());
            dist.setText(benzinera.getDistFromActual() + " Min en cotxe");

            //TODO
        }
    }
}
