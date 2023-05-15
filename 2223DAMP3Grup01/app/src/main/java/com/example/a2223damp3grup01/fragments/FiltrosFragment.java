package com.example.a2223damp3grup01.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.a2223damp3grup01.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FiltrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FiltrosFragment extends Fragment {
    View view;
    CheckBox SubministradorBenzineresCKBX, SubministradorHidrogeneresCKBX, SubministradorPuntsCKBX, SubministradorGasosCKBX;
    CheckBox CombustibleBenzina, CombustibleGasoil,
            gasGLP,gasGNC,gasGNL,
            mennekesm, chademo, mennekesf,tesla,cssCombo,schuko;


    public FiltrosFragment() {
        // Required empty public constructor
    }

    public static FiltrosFragment newInstance(String param1, String param2) {
        FiltrosFragment fragment = new FiltrosFragment();
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
        view = inflater.inflate(R.layout.fragment_filtros, container, false);
        SubministradorBenzineresCKBX = (CheckBox) view.findViewById(R.id.ckeckboxBenzineres);
        SubministradorHidrogeneresCKBX = (CheckBox) view.findViewById(R.id.checkBoxHidrogeneresRut);
        SubministradorPuntsCKBX = (CheckBox) view.findViewById(R.id.ckeckboxPuntsRut);
        SubministradorGasosCKBX = (CheckBox) view.findViewById(R.id.ckeckboxGASRut);
        return view;
    }
}