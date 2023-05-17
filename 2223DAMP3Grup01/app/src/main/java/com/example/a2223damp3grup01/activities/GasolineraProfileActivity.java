package com.example.a2223damp3grup01.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.fragments.PostReviewDialogFragment;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.Review;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GasolineraProfileActivity extends AppCompatActivity {
    List<Review> reviews;
    RecyclerView reviewsRecycler;
    ReviewAdapter reviewAdapter;
    ServiceApi serviceApi;
    Button veureMapa,ressenya;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolinera_profile);
        init();
        initRecyclerReview();
        clickListeners();
    }

    public void init(){
        serviceApi = FitRetro.getServiceApi();
        reviewsRecycler = findViewById(R.id.reviewsGasoRecycler);
        veureMapa = findViewById(R.id.inMap);
        ressenya = findViewById(R.id.postReview);
        id = getIntent().getLongExtra("id",0);
    }

    public void clickListeners(){
        ressenya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostReviewDialogFragment postReviewDialogFragment = new PostReviewDialogFragment();
                postReviewDialogFragment.show(getSupportFragmentManager(),"postReviewDialogFragment");
            }
        });
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