package com.example.a2223damp3grup01.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ParadaListContainerBenzAdapter;
import com.example.a2223damp3grup01.adapters.ParadaListContainerElecAdapter;
import com.example.a2223damp3grup01.adapters.ParadaListItemElecAdapter;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.ParadaKilometre;
import com.example.a2223damp3grup01.objects.ParadaListItemBENZ;
import com.example.a2223damp3grup01.objects.ParadaListItemELEC;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ListaRutaFragment extends Fragment {

    View vista;

    List<LatLng> ruta = new ArrayList<>();

    List<LatLng> parades = new ArrayList<>();

    List<Benzinera> ListPosiblesBenzineresBENZ = new ArrayList<>();

    List<Benzinera> ListPosiblesBenzineresGAS = new ArrayList<>();

    List<PuntRecarrega> ListPosiblesBenzineresPUNTS = new ArrayList<>();

    List<LatLng> paradesAfegides= new ArrayList<>();

    String TAG = "ListaRutaFragment";

    String typeGas="eee";

    String typestopsPREFS="c";

    boolean foundShared=false;

    RecyclerView paradasRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_lista_ruta, container, false);
        paradasRecycler = vista.findViewById(R.id.recyclerParades);
        
        searchOnshared();
        try {
            drawRecycler();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return vista;
    }

    public void searchOnshared() {
        SharedPreferences prefs = getActivity().getSharedPreferences("route_pref", Context.MODE_PRIVATE);

        if (prefs.contains("ruta") && prefs.contains("paradas") && prefs.contains("posiblesParades")) {
            Log.d("drawRouteMapsFragment", "onMapReady: CONTE");

            Gson gson = new Gson();

            String rutaRecuperado = prefs.getString("ruta", "");
            Type type = new TypeToken<List<LatLng>>() {
            }.getType();
            List<LatLng> ListaRutaRecuperada = gson.fromJson(rutaRecuperado, type);
            ruta = ListaRutaRecuperada;


            String paradasRecuperado = prefs.getString("paradas", "");
            Type type2 = new TypeToken<List<LatLng>>() {
            }.getType();
            List<LatLng> ListaParadasRecuperada2 = gson.fromJson(paradasRecuperado, type2);
            parades = ListaParadasRecuperada2;


            typeGas = prefs.getString("combus","");
            Log.d(TAG, "searchOnshared: combusType "+ typeGas );


            typestopsPREFS = prefs.getString("posiblesParadesType", "");

            if (typestopsPREFS.equals("1")) {
                String posibleParadasRecuperando = prefs.getString("posiblesParades", "");
                Type typeBenz = new TypeToken<List<Benzinera>>() {
                }.getType();
                List<Benzinera> posiblesParadasRe = gson.fromJson(posibleParadasRecuperando, typeBenz);
                ListPosiblesBenzineresBENZ = posiblesParadasRe;


            }

            if (typestopsPREFS.equals("2")) {
                String posibleParadasRecuperando = prefs.getString("posiblesParades", "");
                Type typeBenz = new TypeToken<List<Benzinera>>() {
                }.getType();
                List<Benzinera> posiblesParadasRe = gson.fromJson(posibleParadasRecuperando, typeBenz);
                ListPosiblesBenzineresGAS = posiblesParadasRe;

            }


            if (typestopsPREFS.equals("3")) {
                String posibleParadasRecuperando = prefs.getString("posiblesParades", "");
                Type typePunt = new TypeToken<List<PuntRecarrega>>() {
                }.getType();
                List<PuntRecarrega> posiblesParadasRe = gson.fromJson(posibleParadasRecuperando, typePunt);
                ListPosiblesBenzineresPUNTS = posiblesParadasRe;


            }


            Log.d(TAG, "searchOnshared: ruta size" + ruta.size());
            Log.d(TAG, "searchOnshared: paradas size" + parades.size());
            Log.d(TAG, "searchOnshared: type " + typestopsPREFS);
            Log.d(TAG, "searchOnshared: type size" + ListPosiblesBenzineresBENZ.size());
            Log.d(TAG, "searchOnshared: type size" + ListPosiblesBenzineresGAS.size());
            Log.d(TAG, "searchOnshared: type size" + ListPosiblesBenzineresPUNTS.size());

            foundShared=true;
        } else {
            foundShared=false;

        }

    }

    public void drawRecycler() throws IOException, InterruptedException, ApiException {
        if (foundShared==true){

            if (typestopsPREFS.equals("1")/*benzineres*/) {
                List<ParadaListItemBENZ> listItemBENZS = new ArrayList<>();

                for (int i = 0; i < parades.size(); i++) {
                    String parada = "PARADA " + String.valueOf(i+1);
                    List<Benzinera> toadd = new ArrayList<>();
                    for (int j = 0; j < 10; j++) {
                        Benzinera b = ListPosiblesBenzineresBENZ.get(j);
                        b.setDistFromActual(getDurationInMinutes(parades.get(i),new LatLng(b.getLatitude(),b.getLongitude())));
                        toadd.add(b);
                    }
                    listItemBENZS.add(new ParadaListItemBENZ(parada,toadd,1));

                }

                ParadaListContainerBenzAdapter adapter = new ParadaListContainerBenzAdapter(listItemBENZS);
                paradasRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                paradasRecycler.setAdapter(adapter);
            }

            if (typestopsPREFS.equals("2")/*benzineres amb gas*/) {
                List<ParadaListItemBENZ> listItemBENZS = new ArrayList<>();

                for (int i = 0; i < parades.size(); i++) {
                    String parada = "PARADA " + String.valueOf(i+1);
                    List<Benzinera> toadd = new ArrayList<>();
                    for (int j = 0; j < 10; j++) {
                        Benzinera b = ListPosiblesBenzineresGAS.get(j);
                        b.setDistFromActual(getDurationInMinutes(parades.get(i),new LatLng(b.getLatitude(),b.getLongitude())));
                        b.setTypeGAS(typeGas);
                        toadd.add(b);
                    }
                    listItemBENZS.add(new ParadaListItemBENZ(parada,toadd,2));

                }

                ParadaListContainerBenzAdapter adapter = new ParadaListContainerBenzAdapter(listItemBENZS);
                paradasRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                paradasRecycler.setAdapter(adapter);
            }


            if (typestopsPREFS.equals("3")/*electrolineres*/) {
                List<ParadaListItemELEC> listItemELECS = new ArrayList<>();

                for (int i = 0; i < parades.size(); i++) {
                    String parada = "PARADA " + String.valueOf(i+1);
                    List<PuntRecarrega> toadd = new ArrayList<>();
                    for (int j = 0; j < 10; j++) {
                        PuntRecarrega pr = ListPosiblesBenzineresPUNTS.get(j);
                        pr.setDistFromActual(getDurationInMinutes(parades.get(i),new LatLng(pr.getLatitude(),pr.getLongitude())));

                        toadd.add(pr);
                    }
                    listItemELECS.add(new ParadaListItemELEC(parada,toadd));

                }

                ParadaListContainerElecAdapter adapter = new ParadaListContainerElecAdapter(listItemELECS);
                paradasRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                paradasRecycler.setAdapter(adapter);

            }
        }else{
            Toast.makeText(getContext(), "no sha trobat res al sistema", Toast.LENGTH_SHORT).show();
        }
    }

    public long getDurationInMinutes(LatLng inci, LatLng Final) throws IOException, InterruptedException, ApiException {
        long durationInMinutes = 0;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCqWBGxRFvmbc0zqwhClvJRCAqfw4KcXEM")
                .build();

        DirectionsResult result = DirectionsApi.newRequest(context)
                .origin(new com.google.maps.model.LatLng(inci.latitude, inci.longitude))
                .destination(new com.google.maps.model.LatLng(Final.latitude, Final.longitude))
                .mode(TravelMode.DRIVING)
                .await();

        if (result.routes.length > 0) {
            DirectionsRoute route = result.routes[0];
            long durationInSeconds = route.legs[0].duration.inSeconds;
            durationInMinutes = durationInSeconds/60;
        }
        return  durationInMinutes;
    }
}