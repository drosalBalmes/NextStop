package com.example.a2223damp3grup01.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.interfaces.SelectListenerGaso;
import com.example.a2223damp3grup01.objects.Benzinera;

import java.util.List;

public class GasAdapter extends RecyclerView.Adapter<GasAdapter.MyViewHolder> {
    private List<Benzinera> benzineres;
    private SelectListenerGaso listener;

    public GasAdapter(List<Benzinera> benzineres, SelectListenerGaso listener) {
        this.benzineres = benzineres;
        this.listener = listener;
    }

    public GasAdapter(List<Benzinera> benzineres){
        this.benzineres = benzineres;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_parades_item_gas_david,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(benzineres.get(position));

        holder.rl.setOnClickListener(new View.OnClickListener() {
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
        TextView nReviews;
        TextView gasPreu;
        TextView dist;
        RatingBar rating;
        RelativeLayout rl;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nom = itemView.findViewById(R.id.nomBenzineraGAS);
            gasPreu = itemView.findViewById(R.id.priceGas);
            dist = itemView.findViewById(R.id.minutsCotxeGAS);
            rating = itemView.findViewById(R.id.ratingBarGAS);
            rl = itemView.findViewById(R.id.parades_item_gas_david);
            nReviews = itemView.findViewById(R.id.reviewsTVGAS);
        }
        void bindData(final Benzinera benzinera){
            nom.setText(benzinera.getNom());
            dist.setText(benzinera.getDistFromActual() + " Min en cotxe");
            nReviews.setText(benzinera.getNumReviews() + " Reviews");
            if (benzinera.getMitjaReviews() != null) {
                rating.setRating(benzinera.getMitjaReviews().floatValue());
            }
            if (benzinera.getGnl()){
                gasPreu.setText(benzinera.getPreuGNL() + "");
            }
            if (benzinera.getGnc()){
                gasPreu.setText(benzinera.getPreuGNC() + "");
            }
            if (benzinera.getGlp()){
                gasPreu.setText(benzinera.getPreuGLP() + "");
            }
            //TODO preus
        }
    }
}
