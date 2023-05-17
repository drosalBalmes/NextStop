package com.example.a2223damp3grup01.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.adapters.TipoSubAdapter;
import com.example.a2223damp3grup01.fragments.PostReviewDialogFragment;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.Review;
import com.example.a2223damp3grup01.objects.TipoSub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PuntRecarregaProfileActivity extends AppCompatActivity implements PostReviewDialogFragment.PostReviewDialogListener {

    List<Review> reviews = new ArrayList<>();
    List<String> tipusPunts = new ArrayList<>();
    List<TipoSub> tipoSubs = new ArrayList<>();
    RecyclerView reviewsRecycler,tipusRecycler;
    ReviewAdapter reviewAdapter;
    TipoSubAdapter tipoSubAdapter;
    ServiceApi serviceApi;
    Button veureMapa,ressenya;
    TextView TVnom,TVAvis;
    String nom;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punt_recarrega_profile);
        init();
        initRecyclerReview();
        clickListeners();
    }

    public void init(){
        serviceApi = FitRetro.getServiceApi();
        reviewsRecycler = findViewById(R.id.reviewsPuntRecycler);
        tipusRecycler = findViewById(R.id.tipusSubRecycler);
        veureMapa = findViewById(R.id.inMap);
        ressenya = findViewById(R.id.postReview);
        TVnom = findViewById(R.id.nomPunt);
        TVAvis = findViewById(R.id.TVAvis);
        tipusPunts = getIntent().getStringArrayListExtra("tipusPunts");
        id = getIntent().getIntExtra("id",0);
        Log.d("postReview","id en perfil: " + id);
        nom = getIntent().getStringExtra("nom");
        TVnom.setText(nom);
        initRecyclerTipus();
        getReviewsByPuntId(id);
    }

    public void clickListeners(){
        ressenya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostReviewDialogFragment postReviewDialogFragment = new PostReviewDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("nom",nom);
                bundle.putInt("idPunt",id);
                postReviewDialogFragment.setArguments(bundle);
                postReviewDialogFragment.show(getSupportFragmentManager(),"postReviewDialogFragment");
            }
        });
    }
    private void initRecyclerReview() {
        reviewAdapter = new ReviewAdapter(reviews);

        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecycler.setHasFixedSize(true);
        reviewsRecycler.setAdapter(reviewAdapter);
    }

    private void initRecyclerTipus(){
        initTipoList();
        tipoSubAdapter = new TipoSubAdapter(tipoSubs);

        tipusRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        tipusRecycler.setHasFixedSize(true);
        tipusRecycler.setAdapter(tipoSubAdapter);
    }

    public void getReviewsByPuntId(int id_punt){
        Call<List<Review>> call = serviceApi.listReviewsByPuntId(id_punt);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()){
                    reviews = response.body();
                    if (reviews!=null){
                        if (reviews.size()!=0){
                            TVAvis.setVisibility(View.GONE);
                            reviewsRecycler.setVisibility(View.VISIBLE);
                            initRecyclerReview();
                        }
                    }
                } else{
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

    @Override
    public void onReviewPosted() {
        getReviewsByPuntId(id);
    }

    public void initTipoList(){
        for (String s: tipusPunts) {
            tipoSubs.add(new TipoSub(s," "));
        }
    }
}