package com.example.a2223damp3grup01.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.BenzineresAdapter;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    ServiceApi serviceApi;
    List<Benzinera> benzinerasList = new ArrayList<>();
    View view;
    RecyclerView benzineresRecycler;
    BenzineresAdapter benzineresAdapter;

    long durationInSeconds;
    long durationInMinutes;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationManager locationManager;
    private String provider;
    private LocationListener locationListener;
    private Location actualPos;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list,container,false);
        init();
        return view;
    }

    public void init(){
        serviceApi = FitRetro.getServiceApi();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                actualPos = location;
                Log.d("quepasaeeeeFragment", "Latitude: " + actualPos.getLatitude() + ", Longitude: " + actualPos.getLongitude());
                LatLng inici = new LatLng(actualPos.getLatitude(),actualPos.getLongitude());
                getBenzineresFinder(3,inici.lat,inici.lng);
            }
        };
        benzineresRecycler = view.findViewById(R.id.listGasolinerasRecycler);
        localize();

    }

    public void initRecyclerBenzineres(){
        benzineresAdapter = new BenzineresAdapter(benzinerasList);
        Log.d("lolol",benzinerasList.get(0).getNom());
        benzineresRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        benzineresRecycler.setHasFixedSize(true);
        benzineresRecycler.setAdapter(benzineresAdapter);
    }

    /*
    public void getBenzineres(){

        Call<List<Benzinera>> call = serviceApi.listBenzineres();

        call.enqueue(new Callback<List<Benzinera>>() {
            @Override
            public void onResponse(Call<List<Benzinera>> call, retrofit2.Response<List<Benzinera>> response) {
                benzinerasList = response.body();
                Log.d("getingbenzineres","aquiva1");
                try {
                    if (benzinerasList != null) {
                        Log.d("getingbenzineres", benzinerasList.get(0).getNom());
                        Log.d("getingbenzineres", String.valueOf(benzinerasList.size()));
                        LatLng inici = new LatLng(actualPos.getLatitude(),actualPos.getLongitude());
                        for (Benzinera b: benzinerasList) {
                            Log.d("quepasa","lol");
                            LatLng benzinera = new LatLng(b.getLatitude(),b.getLongitude());
                            Log.d("quepasa","Latitude: " + b.getLatitude() + " Longitude: " + b.getLongitude());
                            tal(inici,benzinera);
                            b.setDistFromActual(durationInMinutes);
                        }
                        initRecyclerBenzineres();

                    }
                }catch (Exception e){
                    Log.d("getingbenzineres",e.toString());
                }

            }

            @Override
            public void onFailure(Call<List<Benzinera>> call, Throwable t) {
                Log.e("getingbenzineres", t.getMessage());

                if (t instanceof IOException) {
                    // Error de red o servidor
                    Log.e("getingbenzineres", "Error de red o servidor");
                } else {
                    // Otro tipo de error
                    Log.e("getingbenzineres", "Otro tipo de error");
                }
            }
        });
    }
    */

    public void tal(LatLng inci, LatLng Final) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCqWBGxRFvmbc0zqwhClvJRCAqfw4KcXEM")
                .build();

        DirectionsResult result = DirectionsApi.newRequest(context)
                .origin(new com.google.maps.model.LatLng(inci.lat, inci.lng))
                .destination(new com.google.maps.model.LatLng(Final.lat, Final.lng))
                .mode(TravelMode.DRIVING)
                .await();

        if (result.routes.length > 0) {
            DirectionsRoute route = result.routes[0];
            durationInSeconds = route.legs[0].duration.inSeconds;
            durationInMinutes = durationInSeconds/60;
        }

    }

    @SuppressLint("MissingPermission")
    public void localize(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);



        provider = locationManager.getBestProvider(new Criteria(), false);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                actualPos = location;
                Log.d("quepasaeeeeFragment", "Latitude: " + actualPos.getLatitude() + ", Longitude: " + actualPos.getLongitude());

            }
        };
        locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);
    }

    public void getBenzineresFinder(double kmRedonda, double locationLat, double locationLong){
        Call<List<Benzinera>> call = serviceApi.listBenzineresFinder(kmRedonda, locationLat, locationLong);

        call.enqueue(new Callback<List<Benzinera>>() {
            @Override
            public void onResponse(Call<List<Benzinera>> call, retrofit2.Response<List<Benzinera>> response) {
                if (response.isSuccessful()) {
                    benzinerasList = response.body();
                    if (benzinerasList != null) {
                        Log.d("getingbenzineres", benzinerasList.get(0).getNom());
                        Log.d("getingbenzineres", String.valueOf(benzinerasList.size()));
                        initRecyclerBenzineres();
                    }
                } else {
                    // Respuesta no exitosa
                    Log.e("getingbenzineres", "Respuesta no exitosa: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Benzinera>> call, Throwable t) {
                Log.e("getingbenzineres", t.getMessage());

                if (t instanceof IOException) {
                    // Error de red o servidor
                    Log.e("getingbenzineres", "Error de red o servidor");
                } else {
                    // Otro tipo de error
                    Log.e("getingbenzineres", "Otro tipo de error");
                }
            }
        });
    }
}