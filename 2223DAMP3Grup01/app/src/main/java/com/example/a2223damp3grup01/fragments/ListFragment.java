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
import com.example.a2223damp3grup01.adapters.PuntRecarregaAdapter;
import com.example.a2223damp3grup01.interfaces.SelectListenerGaso;
import com.example.a2223damp3grup01.interfaces.SelectListenerPunts;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    private LocationListener locationListener;
    private Location actualPos;

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
        initRecyclerBenzineres();
    }

    public void initRecyclerBenzineres() {
        if (puntRecarregaList.size() != 0){
            puntRecarregaAdapter = new PuntRecarregaAdapter(puntRecarregaList,this);
            Log.d("lolol",puntRecarregaList.get(0).getNom());
            benzineresRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            benzineresRecycler.setHasFixedSize(true);
            benzineresRecycler.setAdapter(puntRecarregaAdapter);
        } else if (benzinerasList.size() != 0) {
            benzineresAdapter = new BenzineresAdapter(benzinerasList,this);
            Log.d("lolol", benzinerasList.get(0).getNom());
            benzineresRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            benzineresRecycler.setHasFixedSize(true);
            benzineresRecycler.setAdapter(benzineresAdapter);
        }
    }

    @Override
    public void getFiltros() {
        SharedPreferences preferences = getActivity().getSharedPreferences("gaso_list",Context.MODE_PRIVATE);
        benzinerasList.clear();
        puntRecarregaList.clear();
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
        //SharedPreferences preferences = getActivity().getSharedPreferences("gaso_clicked",Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = preferences.edit();
        //editor.clear();
        Log.d("BenzineraClicked", "id: " + benzinera.getId());
        //editor.putLong("id",benzinera.getId());
        //editor.apply();
        Intent intent = new Intent(getActivity().getApplicationContext(), GasolineraProfileActivity.class);
        intent.putExtra("id",benzinera.getId());
        startActivity(intent);

    }

    @Override
    public void onItemClickedPunt(PuntRecarrega puntRecarrega) {
        Log.d("PuntClicked", "id: " + puntRecarrega.getId());
        Intent intent = new Intent(getActivity().getApplicationContext(), PuntRecarregaProfileActivity.class);
        intent.putExtra("id",puntRecarrega.getId());
        startActivity(intent);
    }
}