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
import com.example.a2223damp3grup01.interfaces.SelectListenerPunts;
import com.example.a2223damp3grup01.objects.PuntRecarrega;

import java.util.List;

public class PuntRecarregaAdapter extends RecyclerView.Adapter<PuntRecarregaAdapter.MyViewHolder> {
    private List<PuntRecarrega> puntRecarregaList;
    private SelectListenerPunts listenerPunts;

    public PuntRecarregaAdapter(List<PuntRecarrega> puntRecarregaList, SelectListenerPunts listenerPunts) {
        this.puntRecarregaList = puntRecarregaList;
        this.listenerPunts = listenerPunts;
    }

    public PuntRecarregaAdapter(List<PuntRecarrega> puntRecarregaList){
        this.puntRecarregaList = puntRecarregaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_parades_item_elec_david,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(puntRecarregaList.get(position));

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerPunts.onItemClickedPunt(puntRecarregaList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return puntRecarregaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView tipus;
        TextView nReviews;
        RatingBar rating;
        TextView dist;
        RelativeLayout rl;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            rl = itemView.findViewById(R.id.parades_item_elec_david);
            nom= itemView.findViewById(R.id.nomElectrolinera);
            rating = itemView.findViewById(R.id.ratingBarELEC);
            dist = itemView.findViewById(R.id.minutsCotxeelec);
            nReviews = itemView.findViewById(R.id.reviewsETelec);
        }

        void bindData(final PuntRecarrega puntRecarrega){
            nom.setText(puntRecarrega.getNom());
            dist.setText(puntRecarrega.getDistFromActual() + " Min en cotxe");
            nReviews.setText(puntRecarrega.getNumReviews() + " Reviews");
            if (puntRecarrega.getMitjaReviews() != null) {
                rating.setRating(puntRecarrega.getMitjaReviews().floatValue());
            }
        }
    }
}
