package com.example.a2223damp3grup01.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.objects.Review;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {





    List<Review> reviews;
    RecyclerView reviewsRecycler;
    ReviewAdapter reviewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        reviewsRecycler = findViewById(R.id.reviewsRecycler);
        initRecyclerReview();
    }


    public void initRecyclerReview(){
        reviews = new ArrayList<>();
        reviews.add(new Review(3,"Comentario","Repsol"));
        reviews.add(new Review(2,"To guapa la gasolinera sisi lolol","Repsol"));
        reviews.add(new Review(0,"Lorem Ipsum tremendo texto yokse wow","Repsol"));
        reviews.add(new Review(5,"La gasolinera muy guapa pero leanse One Piece que esta tremendo","Repsol"));
        reviews.add(new Review(5,"La gasolinera muy guapa pero leanse One Piece que esta tremendo","Repsol"));
        reviews.add(new Review(5,"La gasolinera muy guapa pero leanse One Piece que esta tremendo","Repsol"));
        reviews.add(new Review(5,"La gasolinera muy guapa pero leanse One Piece que esta tremendo","Repsol"));
        reviewAdapter = new ReviewAdapter(reviews);
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecycler.setHasFixedSize(true);
        reviewsRecycler.setAdapter(reviewAdapter);
    }
}