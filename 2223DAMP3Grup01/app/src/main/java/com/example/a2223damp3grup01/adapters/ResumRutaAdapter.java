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
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.example.a2223damp3grup01.objects.ResumRutaObject;

import java.util.List;

public class ResumRutaAdapter extends RecyclerView.Adapter<ResumRutaAdapter.MyViewHolder>{

    private List<ResumRutaObject> resumsRuta;

    public ResumRutaAdapter(List<ResumRutaObject> resumsRuta) {
        this.resumsRuta = resumsRuta;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResumRutaAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_route_preview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(resumsRuta.get(position));

    }

    @Override
    public int getItemCount() {
       return resumsRuta.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView parada;
        TextView direccio;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            parada = (TextView) itemView.findViewById(R.id.tvparadaFInal);
            direccio = (TextView) itemView.findViewById(R.id.direccioFinal);


        }

        void bindData(final ResumRutaObject resum){
            parada.setText(resum.getParada());
            direccio.setText(resum.getDireccio());

        }
    }
}
