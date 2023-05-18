package com.example.a2223damp3grup01.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.adapters.TipoSubAdapter;
import com.example.a2223damp3grup01.fragments.PostReviewDialogFragment;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.Review;
import com.example.a2223damp3grup01.objects.TipoSub;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GasolineraProfileActivity extends AppCompatActivity implements PostReviewDialogFragment.PostReviewDialogListener {
    List<Review> reviews = new ArrayList<>();
    List<TipoSub> tipos = new ArrayList<>();
    RecyclerView tiposRecycler;
    RecyclerView reviewsRecycler;
    ReviewAdapter reviewAdapter;
    TipoSubAdapter tipoSubAdapter;
    ServiceApi serviceApi;
    Button veureMapa,ressenya;
    TextView TVnom,TVAvis;
    String nom;
    int id;
    private boolean adblue = false;
    private boolean gasoil = false;
    private boolean gasolina = false;
    private boolean glp = false;
    private boolean gnc = false;
    private boolean gnl = false;
    private boolean hidrogen = false;
    private boolean sp95 = false;
    private boolean sp98 = false;
    LatLng ubiActual;
    LatLng ubiGaso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolinera_profile);
        init();
        initRecyclerReview();
        clickListeners();
    }

    public void init(){
        reviewsRecycler = findViewById(R.id.reviewsGasoRecycler);
        tiposRecycler = findViewById(R.id.tipusSubRecycler);
        serviceApi = FitRetro.getServiceApi();
        veureMapa = findViewById(R.id.inMap);
        ressenya = findViewById(R.id.postReview);
        TVAvis = findViewById(R.id.TVAvis);
        TVnom = findViewById(R.id.nomGaso);
        TVnom.setText(nom);
        getIntents();
        initRecyclerTipos();
        getReviewsByBenzId(id);
    }
    public void getIntents(){
        //Se podria haber hecho con una list soy tonto
        adblue = getIntent().getBooleanExtra("adblue",false);
        gasoil = getIntent().getBooleanExtra("gasoil",false);
        gasolina = getIntent().getBooleanExtra("gasolina",false);
        glp = getIntent().getBooleanExtra("glp",false);
        gnc = getIntent().getBooleanExtra("gnc",false);
        gnl = getIntent().getBooleanExtra("gnl",false);
        hidrogen = getIntent().getBooleanExtra("hidrogen",false);
        sp95 = getIntent().getBooleanExtra("sp95",false);
        sp98 = getIntent().getBooleanExtra("sp98",false);//latBenz
        ubiActual = new LatLng(getIntent().getDoubleExtra("latActual",0),
                getIntent().getDoubleExtra("lngActual",0));
        ubiGaso = new LatLng(getIntent().getDoubleExtra("latBenz",0),
                getIntent().getDoubleExtra("lngBenz",0));
        id = (int) getIntent().getLongExtra("id",0);
        Log.d("id","idGasoProfileGetInt: " + id);
        nom = getIntent().getStringExtra("nom");


    }

    public void clickListeners(){
        ressenya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostReviewDialogFragment postReviewDialogFragment = new PostReviewDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("nom",nom);
                bundle.putInt("idGaso",id);
                Log.d("id","idGasoProfilePut: " + id);
                postReviewDialogFragment.setArguments(bundle);
                postReviewDialogFragment.show(getSupportFragmentManager(),"postReviewDialogFragment");
            }
        });

        veureMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mapsIntent();
            }
        });
    }
    public void initRecyclerReview(){
        reviewAdapter = new ReviewAdapter(reviews);
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecycler.setHasFixedSize(true);
        reviewsRecycler.setAdapter(reviewAdapter);
    }
    public void initRecyclerTipos(){
        initTiposList();
        tipoSubAdapter = new TipoSubAdapter(tipos);

        tiposRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        tiposRecycler.setHasFixedSize(true);
        tiposRecycler.setAdapter(tipoSubAdapter);
    }


    public void getReviewsByBenzId(int id_benz){
        Call<List<Review>> call = serviceApi.listReviewsByBenzId(id_benz);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()){
                    reviews = response.body();
                    if (reviews!=null){
                        if (reviews.size() != 0){
                            TVAvis.setVisibility(View.GONE);
                            reviewsRecycler.setVisibility(View.VISIBLE);
                            Log.d("getingReviews", "estan vacias(?)");
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

    @Override
    public void onReviewPosted() {
        getReviewsByBenzId(id);
    }

    public void initTiposList(){
        if (adblue){
            tipos.add(new TipoSub("Adblue","Preu: 1.26€/L"));
        }
        if (gasoil){
            tipos.add(new TipoSub("Gasoil","Preu: 1.26€/L"));
        }
        if (gasolina){
            tipos.add(new TipoSub("Gasolina","Preu: 1.26€/L"));
        }
        if (glp){
            tipos.add(new TipoSub("Glp","Preu: 1.26€/L"));
        }
        if (gnc){
            tipos.add(new TipoSub("Gnc","Preu: 1.26€/L"));
        }
        if (gnl){
            tipos.add(new TipoSub("Gnl","Preu: 1.33€/L"));
        }
        if (hidrogen){
            tipos.add(new TipoSub("Hidrogen","Preu: 1.26€/L"));
        }
        if (sp95){
            tipos.add(new TipoSub("Sp95","Preu: 1.26€/L"));
        }
        if (sp98){
            tipos.add(new TipoSub("Sp98","Preu: 1.26€/L"));
        }
    }

    public void mapsIntent(){
        LatLng inici = ubiActual;
        LatLng finall = ubiGaso;
        Log.d("ubiAct", "Lat: " + ubiActual.latitude + "Lng: " + ubiActual.longitude);
        Log.d("ubiGas", "Lat: " + ubiGaso.latitude + "Lng: " + ubiGaso.longitude);


        StringBuilder uriBuilder = new StringBuilder("https://www.google.com/maps/dir/?api=1");
        uriBuilder.append("&origin=").append(inici.latitude).append(",").append(inici.longitude);
        uriBuilder.append("&destination=").append(finall.latitude).append(",").append(finall.longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriBuilder.toString()));
        intent.setPackage("com.google.android.apps.maps"); // Limita la búsqueda a la aplicación de Google Maps

        if (getPackageManager() != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No se encontró Google Maps en tu dispositivo", Toast.LENGTH_SHORT).show();
        }
    }
}