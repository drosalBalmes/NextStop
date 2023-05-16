package com.example.a2223damp3grup01.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class MapaRutaFragment extends Fragment implements OnMapReadyCallback, FiltrosRutaFragment.FiltrosRutaListener {
    GoogleMap mMap;
    Polyline ruteLine;
    Button botonBasura;
    List<LatLng> rutaTOP;
    List<LatLng> paradasTOP;
    ServiceApi serviceApi;

    List<Benzinera> benzinerasList = new ArrayList<>();

    View vista;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            getRouteFromPrefs();



        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       vista =  inflater.inflate(R.layout.fragment_maps, container, false);

        serviceApi = FitRetro.getServiceApi();




        return vista;
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
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

    }

    public void getRouteFromPrefs(){
        SharedPreferences prefs = getActivity().getSharedPreferences("route_pref", Context.MODE_PRIVATE);


        if (prefs.contains("ruta") && prefs.contains("paradas")&&prefs.contains("posiblesParades")){
            Log.d("drawRouteMapsFragment", "onMapReady: CONTE");

            Gson gson = new Gson();
            mMap.clear();
            String rutaRecuperado = prefs.getString("ruta", "");
            Type type = new TypeToken<List<LatLng>>(){}.getType();
            List<LatLng> ListaRutaRecuperada = gson.fromJson(rutaRecuperado, type);

            String paradasRecuperado = prefs.getString("paradas", "");
            Type type2 = new TypeToken<List<LatLng>>(){}.getType();
            List<LatLng> ListaParadasRecuperada2 = gson.fromJson(paradasRecuperado, type2);

            String typestopsPREFS = prefs.getString("posiblesParadesType", "");

            if (typestopsPREFS.equals("1")){
                String posibleParadasRecuperando = prefs.getString("posiblesParades","");
                Type typeBenz = new TypeToken<List<Benzinera>>(){}.getType();
                List<Benzinera> posiblesParadasRe = gson.fromJson(posibleParadasRecuperando, typeBenz);
                drawRouteWithStops(ListaRutaRecuperada,ListaParadasRecuperada2,typestopsPREFS,posiblesParadasRe);
            }
            if (typestopsPREFS.equals("2")){
                String posibleParadasRecuperando = prefs.getString("posiblesParades","");
                Type typeBenz = new TypeToken<List<Benzinera>>(){}.getType();
                List<Benzinera> posiblesParadasRe = gson.fromJson(posibleParadasRecuperando, typeBenz);
                drawRouteWithStops(ListaRutaRecuperada,ListaParadasRecuperada2,typestopsPREFS,posiblesParadasRe);

            }
            if (typestopsPREFS.equals("3")){
                String posibleParadasRecuperando = prefs.getString("posiblesParades","");
                Type typePunt = new TypeToken<List<PuntRecarrega>>(){}.getType();
                List<PuntRecarrega> posiblesParadasRe = gson.fromJson(posibleParadasRecuperando, typePunt);
                drawRouteWithStopsPunts(ListaRutaRecuperada,ListaParadasRecuperada2,typestopsPREFS,posiblesParadasRe);


            }

        }else{
            Log.d("drawRouteMapsFragment", "onMapReady: no hi ha res al shared preferences");
        }
    }

    public void drawRouteWithStops(List<LatLng> ruta,List<LatLng> paradas,String stopType,List<Benzinera> posParades){
        Log.d("drawRouteMapsFragment", "RUTA SIZE " + ruta.size());
        Log.d("drawRouteMapsFragment", "paradas SIZE " + paradas.size());
        Log.d("drawRouteMapsFragment", "stopType SIZE " + stopType);


        mMap.clear();

        PolylineOptions options2 = new PolylineOptions().width(5).color(Color.RED).geodesic(true);

        for (int i = 0; i < ruta.size(); i++) {
            if (i==1){
                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(ruta.get(i));
                markerOptions3.title("Principi");
                markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMap.addMarker(markerOptions3);
            }else if (i==ruta.size()-2){
                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(ruta.get(i));
                markerOptions3.title("Final");
                markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions3);
            }
            LatLng point = ruta.get(i);
            options2.add(point);

        }
        ruteLine = mMap.addPolyline(options2);
        for (int i = 0; i < paradas.size(); i++) {
            MarkerOptions markerOptions3 = new MarkerOptions();
            markerOptions3.position(paradas.get(i));
            markerOptions3.title("Parada");
            markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
            mMap.addMarker(markerOptions3);
        }

        for (int i = 0; i < posParades.size(); i++) {
            if (stopType=="1"){
                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(new LatLng(posParades.get(i).getLatitude(),posParades.get(i).getLongitude()));
                markerOptions3.title("Benzinera: "+ posParades.get(i).getNom());
                markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                mMap.addMarker(markerOptions3);
            }
            if (stopType=="2"){
                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(new LatLng(posParades.get(i).getLatitude(),posParades.get(i).getLongitude()));
                markerOptions3.title("Benzinera amb surtidor de gas: "+ posParades.get(i).getNom());
                markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                mMap.addMarker(markerOptions3);
            }
        }

        
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ruta.get(0),10));

    }

    public void drawRouteWithStopsPunts(List<LatLng> ruta,List<LatLng> paradas,String stopType,List<PuntRecarrega> posParades){

        Log.d("drawRouteMapsFragment", "posparadessiz e " + posParades.size() );
        Log.d("drawRouteMapsFragment", "paradasSize " + paradas.size() );

        mMap.clear();

        PolylineOptions options2 = new PolylineOptions().width(5).color(Color.RED).geodesic(true);

        for (int i = 0; i < ruta.size(); i++) {
            if (i==1){
                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(ruta.get(i));
                markerOptions3.title("Principi");
                markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMap.addMarker(markerOptions3);
            }else if (i==ruta.size()-2){
                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(ruta.get(i));
                markerOptions3.title("Final");
                markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions3);
            }
            LatLng point = ruta.get(i);
            options2.add(point);

        }
        ruteLine = mMap.addPolyline(options2);
        for (int i = 0; i < paradas.size(); i++) {
            MarkerOptions markerOptions3 = new MarkerOptions();
            markerOptions3.position(paradas.get(i));
            markerOptions3.title("Parada");
            markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
            mMap.addMarker(markerOptions3);
        }

        for (int i = 0; i < posParades.size(); i++) {

                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(new LatLng(posParades.get(i).getLatitude(),posParades.get(i).getLongitude()));
                markerOptions3.title("Electrolinera "+ posParades.get(i).getNom());
                markerOptions3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                mMap.addMarker(markerOptions3);


        }


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ruta.get(0),10));
    }

}