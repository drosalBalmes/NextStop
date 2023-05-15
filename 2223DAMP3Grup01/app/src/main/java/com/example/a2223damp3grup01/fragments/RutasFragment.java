package com.example.a2223damp3grup01.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.activities.ProfileActivity;
import com.google.maps.model.LatLng;

import java.util.List;


public class RutasFragment extends Fragment {

    Button btnLista;
    Button btnMap;
    Button btnFilter;
    CardView btnProfile;
    boolean filterDisplayed=false;

    MapaRutaFragment mapaRutaFragment;
    FiltrosRutaFragment filtrosRutaFragment;
    ListaRutaFragment listaRutaFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rutas_fragment,container,false);
        btnLista = view.findViewById(R.id.btnListRut);
        btnMap = view.findViewById(R.id.btnMapaRut);
        btnFilter = view.findViewById(R.id.filterButtonRuta);
        btnProfile = view.findViewById(R.id.btnProfileRut);
        cambiarFragmentLista(btnLista);
        cambiarFragmentMapa(btnMap);
        cambiarFragmentFiltros(btnFilter);
        toProfile(btnProfile);
        return view;
    }



    public void cambiarFragmentLista(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaRutaFragment = new ListaRutaFragment();
                replaceFragment(listaRutaFragment);
            }
        });
    }

    public void cambiarFragmentMapa(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapaRutaFragment = new MapaRutaFragment();
                replaceFragmentMapa(mapaRutaFragment);
            }
        });
    }

    public void cambiarFragmentFiltros(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filtrosRutaFragment = new FiltrosRutaFragment();
                if (mapaRutaFragment!=null){
                    filtrosRutaFragment.setMapaRutaFragment(mapaRutaFragment);
                }
                replaceFragmentDialog();
            }
        });
    }

    public void toProfile(CardView button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void replaceFragment(Fragment frahment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragmentRut,frahment);
        fragmentTransaction.commit();
    }

    public void replaceFragmentMapa(Fragment frahment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragmentRut,frahment);
        fragmentTransaction.commit();
    }


    public void replaceFragmentDialog(){
        if (filterDisplayed==true){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FrameLayout fl = getActivity().findViewById(R.id.dialogRutas);
            fl.removeAllViews();
            fragmentTransaction.commit();
            filterDisplayed=false;
            Log.d("filterDisplay", "replaceFragmentDialog: " + filterDisplayed);
        }else if (filterDisplayed==false){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                            .add(R.id.dialogRutas , filtrosRutaFragment);
            fragmentTransaction.commit();
            filterDisplayed=true;
            Log.d("filterDisplay", "replaceFragmentDialog: " + filterDisplayed);

        }
    }


}