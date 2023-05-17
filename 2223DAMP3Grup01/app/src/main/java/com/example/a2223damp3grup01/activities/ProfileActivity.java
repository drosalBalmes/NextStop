package com.example.a2223damp3grup01.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {





    List<Review> reviews = new ArrayList<>();
    RecyclerView reviewsRecycler;
    ReviewAdapter reviewAdapter;
    ServiceApi serviceApi;
    TextView TVAvis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }

    public void init(){
        serviceApi = FitRetro.getServiceApi();
        reviewsRecycler = findViewById(R.id.reviewsRecycler);
        TVAvis = findViewById(R.id.TVAvis);
        getReviewsByUserId(1);
    }


    public void initRecyclerReview(){
        reviewAdapter = new ReviewAdapter(reviews);

        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecycler.setHasFixedSize(true);
        reviewsRecycler.setAdapter(reviewAdapter);
    }

    public void getReviewsByUserId(int id_user){
        Call<List<Review>> call = serviceApi.listReviewsByUserId(id_user);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()){
                    reviews = response.body();
                    if (reviews != null) {
                        if (reviews.size()!=0){
                            TVAvis.setVisibility(View.GONE);
                            reviewsRecycler.setVisibility(View.VISIBLE);
                            for (Review review : reviews) {
                                Log.d("getingReviews", "Comentario: " + review.getComentari());
                            }
                            initRecyclerReview();
                        }
                    }
                } else {
                    Log.e("getingReviews", "Respuesta no exitosa: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.e("getingReviews", t.getMessage());

                if (t instanceof IOException) {
                    // Error de red o servidor
                    Log.e("getingReviews", "Error de red o servidor");
                } else {
                    // Otro tipo de error
                    Log.e("getingReviews", "Otro tipo de error");
                }
            }
        });
    }
}