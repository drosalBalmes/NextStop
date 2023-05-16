package com.example.a2223damp3grup01.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.objects.PuntRecarrega;

import java.util.List;

public class PuntRecarregaAdapter extends RecyclerView.Adapter<PuntRecarregaAdapter.MyViewHolder> {
    private List<PuntRecarrega> puntRecarregaList;

    public PuntRecarregaAdapter(List<PuntRecarrega> puntRecarregaList){
        this.puntRecarregaList = puntRecarregaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_punts_recarrega,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(puntRecarregaList.get(position));
    }

    @Override
    public int getItemCount() {
        return puntRecarregaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView tipus;
        RatingBar rating;
        TextView dist;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nom= itemView.findViewById(R.id.nomTxt);
            tipus = itemView.findViewById(R.id.tipos);
            rating = itemView.findViewById(R.id.ratingBar);
            dist = itemView.findViewById(R.id.distancia);
        }

        void bindData(final PuntRecarrega puntRecarrega){
            nom.setText(puntRecarrega.getNom());
            dist.setText(puntRecarrega.getDistFromActual() + " Min en cotxe");
        }
    }
}
