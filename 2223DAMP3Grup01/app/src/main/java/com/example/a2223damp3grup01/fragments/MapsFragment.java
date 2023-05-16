package com.example.a2223damp3grup01.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment implements FiltrosFragment.FiltrosListener {

    private GoogleMap mMap;
    private List<Benzinera> benzinerasList = new ArrayList<>();
    private List<PuntRecarrega> puntRecarregaList = new ArrayList<>();
    double latActual;
    double lngActual;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            getFiltros();
            Log.d("Mapeando", "entras??");
            Log.d("Mapeando", "entras2??");
            LatLng posActual = new LatLng(latActual, lngActual);
            Log.d("Mapeando", "Latitude2323: " + latActual + "Longitude2323: " + lngActual);
            googleMap.addMarker(new MarkerOptions().position(posActual).title("Posició actual"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(posActual));

            putStopPoints(googleMap);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void getFiltros() {
        SharedPreferences preferences = getActivity().getSharedPreferences("gaso_list", Context.MODE_PRIVATE);
        benzinerasList.clear();
        puntRecarregaList.clear();
        Log.d("Mapeando", "prePreferences ");

        if (preferences.contains("lat")){
            latActual = Double.parseDouble(preferences.getString("lat",""));
            lngActual = Double.parseDouble(preferences.getString("lng",""));
            Log.d("Mapeando", "Latitude2323: " + latActual + "Longitude2323: " + lngActual);
        } else if (preferences.contains("benzineres")){
            Log.d("Mapeando", "containsBenzineres ");
            Gson gson = new Gson();
            String listRecuperado = preferences.getString("benzineres", "");
            Type type = new TypeToken<List<Benzinera>>(){}.getType();
            List<Benzinera> listaGasolineraRecuperada = gson.fromJson(listRecuperado, type);
            benzinerasList = listaGasolineraRecuperada;
        } else if (preferences.contains("punts")) {
            Log.d("Mapeando", "containsPunts ");
            Gson gson = new Gson();
            String listRecuperado = preferences.getString("punts", "");
            Type type = new TypeToken<List<PuntRecarrega>>(){}.getType();
            List<PuntRecarrega> listaPuntsRecuperada = gson.fromJson(listRecuperado, type);
            puntRecarregaList = listaPuntsRecuperada;
        } else {
            Log.d("RecyclerGaso", "No hi ha res al shared preferences");
        }
    }

    public void putStopPoints(GoogleMap googleMap){
        Log.d("Mapeando", "putStopPoints ");
        if (puntRecarregaList.size() != 0) {
            for (PuntRecarrega pr: puntRecarregaList) {
                Log.d("Mapeando", "putStopPoints " + pr.getNom());
                LatLng point = new LatLng(pr.getLatitude(),pr.getLongitude());
                addMarkerToMap(point,googleMap);
            }
        } else if (benzinerasList.size() != 0) {
            for (Benzinera b: benzinerasList) {
                Log.d("Mapeando", "putStopPoints " + b.getNom());
                LatLng point = new LatLng(b.getLatitude(), b.getLongitude());
                addMarkerToMap(point, googleMap);
            }
        }
    }

    public void addMarkerToMap(LatLng latLng,GoogleMap googleMap){
        Log.d("Mapeando", "addMarkerToMap1: ");
        googleMap.addMarker(new MarkerOptions().position(latLng));
        Log.d("Mapeando", "Latitude: " + latLng.latitude + " Longitude: " + latLng.longitude);
    }
}