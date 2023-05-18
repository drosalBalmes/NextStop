package com.example.a2223damp3grup01.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ResumRutaAdapter;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.example.a2223damp3grup01.objects.ResumRutaObject;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class googleMapsFragment extends Fragment {

    String TAG = "googleMapsFragment";

    List<LatLng> ruta;
    List<LatLng> parades;
    List<LatLng> paradesDEF;
    RecyclerView rv;
    Button buton;
    View vista;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        vista =  inflater.inflate(R.layout.fragment_google_maps, container, false);


        searchOnshared();

        getItemsFromSharedPreferences2();
        initItems();

        try {
            createRecycler();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vista;
    }

    public void initItems(){
        rv = vista.findViewById(R.id.lastRecycler);
        buton = vista.findViewById(R.id.btnDibuixaAlMaps);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsIntent();
            }
        });
    }

    public void searchOnshared() {
        SharedPreferences prefss = getActivity().getSharedPreferences("route_pref", Context.MODE_PRIVATE);

        if (prefss.contains("ruta") && prefss.contains("paradas") && prefss.contains("posiblesParades")) {
            Log.d("drawRouteMapsFragment", "onMapReady: CONTE");

            Gson gson = new Gson();

            String rutaRecuperado = prefss.getString("ruta", "");
            Type type = new TypeToken<List<LatLng>>() {
            }.getType();
            List<LatLng> ListaRutaRecuperada = gson.fromJson(rutaRecuperado, type);
            ruta = ListaRutaRecuperada;


            String paradasRecuperado = prefss.getString("paradas", "");
            Type type2 = new TypeToken<List<LatLng>>() {
            }.getType();
            List<LatLng> ListaParadasRecuperada2 = gson.fromJson(paradasRecuperado, type2);
            parades = ListaParadasRecuperada2;
        }

    }

    public void getItemsFromSharedPreferences2(){

        List<String> LatLngsString = new ArrayList<>();
        List<LatLng> LatLngsList= new ArrayList<>();
        SharedPreferences prefs = vista.getContext().getSharedPreferences("ruta_final", Context.MODE_PRIVATE);

        for (int i = 0; i < parades.size(); i++) {
            prefs.getString("parada" + String.valueOf(i), "");
            LatLngsString.add(prefs.getString("parada" + String.valueOf(i), ""));

        }

        for (String s :
                LatLngsString) {
            Log.d(TAG, "saveOnSharedPreferencesDefinitive: ltlngs "  + s);
            String[] latLngParts = s.split(",");
            double latitude = Double.parseDouble(latLngParts[0]);
            double longitude = Double.parseDouble(latLngParts[1]);
            LatLng savedLatLng = new LatLng(latitude, longitude);
            LatLngsList.add(savedLatLng);
        }

        Log.d(TAG, "saveOnSharedPreferencesDefinitive: "+ LatLngsList.size());
        paradesDEF=LatLngsList;


    }

    public void createRecycler() throws IOException {
         List<ResumRutaObject> resumsRuta= new ArrayList<>();

         if (paradesDEF.size()!=parades.size()){
             Toast.makeText(getContext(), "selecciona una estacio per a cada parada", Toast.LENGTH_SHORT).show();
         }else{
             for (LatLng l :
                     paradesDEF) {
                 resumsRuta.add(new ResumRutaObject(
                         "parada "+paradesDEF.indexOf(l),
                         getAdresa(l)
                 ));
             }
         }

        ResumRutaAdapter adapter = new ResumRutaAdapter(resumsRuta);
        rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rv.setAdapter(adapter);

    }

    public void mapsIntent(){
        LatLng inici = ruta.get(0);
        LatLng finall = ruta.get(ruta.size()-1);
        List<LatLng> paradesss= paradesDEF;


        StringBuilder uriBuilder = new StringBuilder("https://www.google.com/maps/dir/?api=1");
        uriBuilder.append("&origin=").append(inici.latitude).append(",").append(inici.longitude);
        uriBuilder.append("&destination=").append(finall.latitude).append(",").append(finall.longitude);
        for (LatLng puntoIntermedio : paradesss) {
            uriBuilder.append("&waypoints=").append(puntoIntermedio.latitude).append(",").append(puntoIntermedio.longitude);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriBuilder.toString()));
        intent.setPackage("com.google.android.apps.maps"); // Limita la búsqueda a la aplicación de Google Maps

        if (requireActivity().getPackageManager() != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "No se encontró Google Maps en tu dispositivo", Toast.LENGTH_SHORT).show();
        }
    }

    public String getAdresa(LatLng a) throws IOException {

        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addressList = geocoder.getFromLocation(a.latitude,a.longitude,1);

        if (addressList.size()>0){
            String poblacio = String.valueOf(addressList.get(0).getAddressLine(0));
            return poblacio;
        }

        return "try again";

    }
}