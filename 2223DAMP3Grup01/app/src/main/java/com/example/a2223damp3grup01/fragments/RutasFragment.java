package com.example.a2223damp3grup01.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.activities.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RutasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RutasFragment extends Fragment {

    Button btnLista;
    Button btnMap;
    Button btnFilter;
    CardView btnProfile;
    boolean filterDisplayed;

    public RutasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GasolinerasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RutasFragment newInstance(String param1, String param2) {
        RutasFragment fragment = new RutasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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
        cambiarFragment(btnLista,new ListaRutaFragment());
        cambiarFragment(btnMap,new MapaRutaFragment());
        cambiarFragment2(btnFilter,new FiltrosRutaFragment());
        toProfile(btnProfile);
        return view;
    }



    public void cambiarFragment(Button button, Fragment fragment){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(fragment);
            }
        });
    }

    public void cambiarFragment2(Button button, Fragment fragment){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentDialog(fragment);
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

    public void replaceFragmentDialog(Fragment frahment){
        if (filterDisplayed==true){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(frahment);
            fragmentTransaction.commit();
            filterDisplayed=false;
        }else{
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.dialogRutas,frahment);
            fragmentTransaction.commit();
            filterDisplayed=true;

        }
    }
}