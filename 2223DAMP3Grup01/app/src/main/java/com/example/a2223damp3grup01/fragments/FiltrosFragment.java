package com.example.a2223damp3grup01.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.a2223damp3grup01.R;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FiltrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FiltrosFragment extends Fragment {
    View view;
    RelativeLayout RelativeTipusCombustible;
    CheckBox SubministradorBenzineresCKBX, SubministradorHidrogeneresCKBX, SubministradorPuntsCKBX, SubministradorGasosCKBX;
    CheckBox CombustibleBenzina, CombustibleGasoil,
            gasGLP,gasGNC,gasGNL,
            mennekesm, chademo, mennekesf,tesla,cssCombo,schuko;
    RelativeLayout RelativeTipusCarregador;
    RelativeLayout RelativeTipusGas;
    ArrayList<CheckBox> enchufeTypes= new ArrayList<>();
    EditText ETKmVoltant;
    Button useActual,buscar;
    boolean actualB;
    RelativeLayout RelativeKmRedonda;


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
        initItems();
        ClickListeners();
        initSubministradorsListeners();

        enchufeTypes.add(CombustibleBenzina);
        enchufeTypes.add(CombustibleGasoil);
        enchufeTypes.add(gasGLP);
        enchufeTypes.add(gasGNC);
        enchufeTypes.add(gasGNL);
        enchufeTypes.add(mennekesm);
        enchufeTypes.add(chademo);
        enchufeTypes.add(mennekesf);
        enchufeTypes.add(tesla);
        enchufeTypes.add(cssCombo);
        enchufeTypes.add(schuko);
        return view;
    }

    public void initItems() {
        SubministradorBenzineresCKBX = (CheckBox) view.findViewById(R.id.ckeckboxBenzineres);
        SubministradorHidrogeneresCKBX = (CheckBox) view.findViewById(R.id.checkBoxHidrogeneres);
        SubministradorPuntsCKBX = (CheckBox) view.findViewById(R.id.ckeckboxPunts);
        SubministradorGasosCKBX = (CheckBox) view.findViewById(R.id.ckeckboxGAS);
        buscar = view.findViewById(R.id.btnBuscar);
        RelativeKmRedonda = view.findViewById(R.id.relativekmredonda);
        ETKmVoltant = view.findViewById(R.id.ETKmVoltant);
        useActual = view.findViewById(R.id.buttonActual);

        initItemsBenz();
        initItemsElec();
        initItemsGas();
    }

    public void initItemsBenz(){
        RelativeTipusCombustible = (RelativeLayout) view.findViewById(R.id.relativeBenzineres);
        CombustibleBenzina = (CheckBox) view.findViewById(R.id.ckeckboxBenz);
        CombustibleGasoil = (CheckBox) view.findViewById(R.id.checkBoxGasoil);
    }

    public void initItemsElec(){
        RelativeTipusCarregador = (RelativeLayout) view.findViewById(R.id.relativeCarregadors);

        mennekesm = (CheckBox) view.findViewById(R.id.mennekesmCheck);
        schuko = (CheckBox) view.findViewById(R.id.schukCheck);
        chademo = (CheckBox) view.findViewById(R.id.chademoCheck);
        cssCombo = (CheckBox) view.findViewById(R.id.ccscomboCheck);
        mennekesf = (CheckBox) view.findViewById(R.id.mennekesf);
        tesla = (CheckBox) view.findViewById(R.id.TESLA);

    }

    public void initItemsGas(){
        RelativeTipusGas=(RelativeLayout) view.findViewById(R.id.relativeGasos);

        gasGLP = view.findViewById(R.id.GLPcheck);
        gasGNC = view.findViewById(R.id.GNCcheck);
        gasGNL = view.findViewById(R.id.GNLcheck);
    }
/*
    public void ClickListeners() {
        utilitzarDireccioActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarETInici();
            }
        });
    }
    *
 */

    public void initSubministradorsListeners() {

        SubministradorBenzineresCKBX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCheckBoxSubministrador(SubministradorBenzineresCKBX);
            }
        });
        SubministradorHidrogeneresCKBX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCheckBoxSubministrador(SubministradorHidrogeneresCKBX);
            }
        });
        SubministradorPuntsCKBX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCheckBoxSubministrador(SubministradorPuntsCKBX);
            }
        });
        SubministradorGasosCKBX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCheckBoxSubministrador(SubministradorGasosCKBX);
            }
        });
    }

    public void clickCheckBoxSubministrador(CheckBox clicked) {

        if (clicked.equals(SubministradorBenzineresCKBX)) {
            if (SubministradorBenzineresCKBX.isChecked()) {
                SubministradorHidrogeneresCKBX.setChecked(false);
                SubministradorPuntsCKBX.setChecked(false);
                SubministradorGasosCKBX.setChecked(false);
            } else {
                SubministradorBenzineresCKBX.setChecked(true);
            }
        } else if (clicked.equals(SubministradorHidrogeneresCKBX)) {
            if (SubministradorHidrogeneresCKBX.isChecked()) {
                SubministradorBenzineresCKBX.setChecked(false);
                SubministradorPuntsCKBX.setChecked(false);
                SubministradorGasosCKBX.setChecked(false);
            } else {
                SubministradorHidrogeneresCKBX.setChecked(true);
            }
        } else if (clicked.equals(SubministradorPuntsCKBX)) {
            if (SubministradorPuntsCKBX.isChecked()) {
                SubministradorBenzineresCKBX.setChecked(false);
                SubministradorHidrogeneresCKBX.setChecked(false);
                SubministradorGasosCKBX.setChecked(false);
            } else {
                SubministradorPuntsCKBX.setChecked(true);
            }
        } else if (clicked.equals(SubministradorGasosCKBX)) {
            if (SubministradorGasosCKBX.isChecked()) {
                SubministradorBenzineresCKBX.setChecked(false);
                SubministradorHidrogeneresCKBX.setChecked(false);
                SubministradorPuntsCKBX.setChecked(false);
            } else {
                SubministradorGasosCKBX.setChecked(true);
            }
        }
        for (CheckBox ck: enchufeTypes) {
            ck.setChecked(false);
        }
        DisplayTypeRelative();
    }

    public void DisplayTypeRelative() {
        if (SubministradorBenzineresCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.VISIBLE);
            RelativeTipusCarregador.setVisibility(View.GONE);
            RelativeTipusGas.setVisibility(View.GONE);
            RelativeKmRedonda.setVisibility(View.VISIBLE);
        } else if (SubministradorGasosCKBX.isChecked()) {
            RelativeKmRedonda.setVisibility(View.VISIBLE);
            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeTipusCarregador.setVisibility(View.GONE);
            RelativeTipusGas.setVisibility(View.VISIBLE);
        } else if (SubministradorHidrogeneresCKBX.isChecked()) {
            RelativeKmRedonda.setVisibility(View.GONE);
            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeTipusCarregador.setVisibility(View.GONE);
            RelativeTipusGas.setVisibility(View.GONE);
        } else if (SubministradorPuntsCKBX.isChecked()) {
            RelativeKmRedonda.setVisibility(View.VISIBLE);
            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeTipusCarregador.setVisibility(View.VISIBLE);
            RelativeTipusGas.setVisibility(View.GONE);
        } else {
            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeTipusCarregador.setVisibility(View.GONE);
            RelativeTipusGas.setVisibility(View.GONE);
        }
        changeMarginsTop();
    }
    public void cambiarMarginTop(RelativeLayout layout,int sp){
        // Obtener el elemento por su ID

// Definir el nuevo valor de marginTop en sp
        int marginInSp = sp;

// Convertir el valor en sp a píxeles
        float scale = getResources().getDisplayMetrics().scaledDensity;
        int marginInPx = (int) (marginInSp * scale + 0.5f);

// Obtener los parámetros de diseño del elemento
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();

// Establecer el nuevo valor de marginTop en píxeles
        params.topMargin = marginInPx;

// Actualizar los parámetros de diseño del elemento
        layout.setLayoutParams(params);

    }

    public void changeMarginsTop(){
        if (SubministradorBenzineresCKBX.isChecked()) {
            cambiarMarginTop(RelativeKmRedonda,250);

        }
        if (SubministradorGasosCKBX.isChecked()) {
            cambiarMarginTop(RelativeKmRedonda,300);
        }
        if (SubministradorHidrogeneresCKBX.isChecked()) {

        }
        if (SubministradorPuntsCKBX.isChecked()) {
            cambiarMarginTop(RelativeKmRedonda,400);
        }
    }

    public void ClickListeners(){
        useActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualB = true;
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Pasar editText KM
                double KmDouble = Double.parseDouble(ETKmVoltant.toString());
                ListFragment.getInstance().getBenzineresFinder(KmDouble,ListFragment.getInstance().lat,ListFragment.getInstance().lng);
            }
        });
    }

    public void DisplayRelativeLayout(RelativeLayout layout){
        layout.setVisibility(View.VISIBLE);
    }

    public void HideRelativeLayout(RelativeLayout layout){
        layout.setVisibility(View.GONE);
    }
}