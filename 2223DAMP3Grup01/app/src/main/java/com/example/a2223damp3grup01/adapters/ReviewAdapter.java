package com.example.a2223damp3grup01.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.objects.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private List<Review> reviews;


    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_rating,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(reviews.get(position));

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView gasolinera;
        RatingBar ratingBar;
        TextView comment;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            gasolinera = itemView.findViewById(R.id.gasolineraR);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            comment = itemView.findViewById(R.id.comment);

        }

        void bindData(final Review review){
            gasolinera.setText(review.getGasolinera());
            ratingBar.setRating(review.getPuntuacio());
            comment.setText(review.getComentari());
        }
    }
}
