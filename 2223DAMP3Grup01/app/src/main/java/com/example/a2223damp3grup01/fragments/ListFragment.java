package com.example.a2223damp3grup01.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.activities.GasolineraProfileActivity;
import com.example.a2223damp3grup01.activities.ProfileActivity;
import com.example.a2223damp3grup01.activities.PuntRecarregaProfileActivity;
import com.example.a2223damp3grup01.adapters.BenzineresAdapter;
import com.example.a2223damp3grup01.adapters.GasAdapter;
import com.example.a2223damp3grup01.adapters.PuntRecarregaAdapter;
import com.example.a2223damp3grup01.interfaces.SelectListenerGaso;
import com.example.a2223damp3grup01.interfaces.SelectListenerPunts;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements FiltrosFragment.FiltrosListener, SelectListenerGaso, SelectListenerPunts {

    ServiceApi serviceApi;
    List<Benzinera> benzinerasList = new ArrayList<>();
    List<PuntRecarrega> puntRecarregaList = new ArrayList<>();
    View view;
    RecyclerView benzineresRecycler,puntsRecycler;
    BenzineresAdapter benzineresAdapter;
    PuntRecarregaAdapter puntRecarregaAdapter;
    int tipo;


    private LocationListener locationListener;
    private Location actualPos;
    GasAdapter gasAdapter;
    private LatLng ubiActual;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
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
        getFiltros();
        init();
        return view;
    }

    public void init(){
        serviceApi = FitRetro.getServiceApi();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                actualPos = location;

            }
        };
        benzineresRecycler = view.findViewById(R.id.listGasolinerasRecycler);
        puntsRecycler = view.findViewById(R.id.listGasolinerasRecycler);
        initRecyclerBenzineres(tipo);
    }

    public void initRecyclerBenzineres(int tipo) {
        if (puntRecarregaList.size() != 0){
            puntRecarregaAdapter = new PuntRecarregaAdapter(puntRecarregaList,this);
            Log.d("lolol",puntRecarregaList.get(0).getNom());
            benzineresRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            benzineresRecycler.setHasFixedSize(true);
            benzineresRecycler.setAdapter(puntRecarregaAdapter);
        } else if (benzinerasList.size() != 0) {
            if (tipo==1) {
                Log.d("editoraaa3","tipo1");
                benzineresAdapter = new BenzineresAdapter(benzinerasList, this);
                Log.d("lolol", benzinerasList.get(0).getNom());
                benzineresRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                benzineresRecycler.setHasFixedSize(true);
                benzineresRecycler.setAdapter(benzineresAdapter);
            } else {
                Log.d("editoraaa3","tipo2");
                gasAdapter = new GasAdapter(benzinerasList,this);
                benzineresRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                benzineresRecycler.setHasFixedSize(true);
                benzineresRecycler.setAdapter(gasAdapter);
            }
        }
    }

    @Override
    public void getFiltros() {
        SharedPreferences preferences = getActivity().getSharedPreferences("gaso_list",Context.MODE_PRIVATE);
        benzinerasList.clear();
        puntRecarregaList.clear();
        if (preferences.contains("tipo")){
            Log.d("editoraaa","tipos");
            if (preferences.getString("tipo","").equalsIgnoreCase("benz")){
                Log.d("editoraaa","tipo1");
                tipo = 1;
            } else {
                Log.d("editoraaa","tipo2");
                tipo = 2;
            }
        } else {
            tipo = 3;
        }
        if (preferences.contains("lat")){
            double lat = Double.parseDouble(preferences.getString("lat",""));
            double lng = Double.parseDouble(preferences.getString("lng",""));
            ubiActual = new LatLng(lat,lng);
        }
        if (preferences.contains("benzineres")){
            Gson gson = new Gson();
            String listRecuperado = preferences.getString("benzineres", "");
            Type type = new TypeToken<List<Benzinera>>(){}.getType();
            List<Benzinera> listaGasolineraRecuperada = gson.fromJson(listRecuperado, type);
            benzinerasList = listaGasolineraRecuperada;
        } else if (preferences.contains("punts")) {
            Gson gson = new Gson();
            String listRecuperado = preferences.getString("punts", "");
            Type type = new TypeToken<List<PuntRecarrega>>(){}.getType();
            List<PuntRecarrega> listaPuntsRecuperada = gson.fromJson(listRecuperado, type);
            puntRecarregaList = listaPuntsRecuperada;
        } else {
            Log.d("RecyclerGaso", "No hi ha res al shared preferences");
        }
    }

    @Override
    public void onItemClickedBenzinera(Benzinera benzinera) {
        Log.d("BenzineraClicked", "id: " + benzinera.getId());
        Intent intent = new Intent(getActivity().getApplicationContext(), GasolineraProfileActivity.class);
        if (benzinera.getAdblue()){
            intent.putExtra("adblue",true);
        }
        if (benzinera.getGasoil()){
            intent.putExtra("gasoil",true);
        }
        if (benzinera.getGasolina()){
            intent.putExtra("gasolina",true);
        }
        if (benzinera.getGlp()){
            intent.putExtra("glp",true);
        }
        if (benzinera.getGnc()){
            intent.putExtra("gnc",true);
        }
        if (benzinera.getGnl()){
            intent.putExtra("gnl",true);
        }
        if (benzinera.getHidrogen()){
            intent.putExtra("hidrogen",true);
        }
        if (benzinera.getSp95()){
            intent.putExtra("sp95",true);
        }
        if (benzinera.getSp98()){
            intent.putExtra("sp98",true);
        }
        intent.putExtra("latActual",ubiActual.latitude);
        intent.putExtra("lngActual",ubiActual.longitude);
        intent.putExtra("latBenz",benzinera.getLatitude());
        intent.putExtra("lngBenz",benzinera.getLongitude());
        intent.putExtra("id",benzinera.getId());
        Log.d("id","idGasoListPut: " + benzinera.getId());
        intent.putExtra("nom",benzinera.getNom());
        startActivity(intent);

    }

    @Override
    public void onItemClickedPunt(PuntRecarrega puntRecarrega) {
        Log.d("PuntClicked", "id: " + puntRecarrega.getId());
        Intent intent = new Intent(getActivity().getApplicationContext(), PuntRecarregaProfileActivity.class);
        intent.putExtra("id",puntRecarrega.getId());
        intent.putExtra("nom",puntRecarrega.getNom());
        intent.putExtra("latActual",ubiActual.latitude);
        intent.putExtra("lngActual",ubiActual.longitude);
        intent.putExtra("latBenz",puntRecarrega.getLatitude());
        intent.putExtra("lngBenz",puntRecarrega.getLongitude());
        intent.putStringArrayListExtra("tipusPunts",tiposPunts(puntRecarrega));
        startActivity(intent);
    }


    public ArrayList<String> tiposPunts(PuntRecarrega puntRecarrega){
        return splitByPlus(puntRecarrega.getTipusConexio());
    }

    public ArrayList<String> splitByPlus(String text) {
        String[] parts = text.split("\\+");
        return new ArrayList<>(Arrays.asList(parts));
    }
}