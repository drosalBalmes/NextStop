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
import com.example.a2223damp3grup01.activities.MainActivity;
import com.example.a2223damp3grup01.activities.ProfileActivity;


public class GasolinerasFragment extends Fragment {

    Button btnLista;
    Button btnMap;
    CardView btnProfile;
    public GasolinerasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gasolineras_fragment,container,false);
        btnLista = view.findViewById(R.id.btnListGas);
        btnMap = view.findViewById(R.id.btnMapaGas);
        btnProfile = view.findViewById(R.id.btnProfileGas);
        cambiarFragment(btnMap,new MapsFragment());
        cambiarFragment(btnLista,new ListFragment());
        toProfile(btnProfile);
        return view;
    }


    public void cambiarFragment(Button button,Fragment fragment){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(fragment);
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
        fragmentTransaction.replace(R.id.flFragmentGas,frahment);
        fragmentTransaction.commit();
    }
}