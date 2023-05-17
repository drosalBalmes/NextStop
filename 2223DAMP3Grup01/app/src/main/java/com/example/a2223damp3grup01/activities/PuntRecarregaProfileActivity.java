package com.example.a2223damp3grup01.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.Review;

import java.util.List;

public class PuntRecarregaProfileActivity extends AppCompatActivity {

    List<Review> reviews;
    RecyclerView reviewsRecycler;
    ReviewAdapter reviewAdapter;
    ServiceApi serviceApi;
    Button veureMapa,ressenya;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punt_recarrega_profile);
    }

    public void init(){
        serviceApi = FitRetro.getServiceApi();
    }
}