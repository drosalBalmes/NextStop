package com.example.a2223damp3grup01.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FiltrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FiltrosFragment extends Fragment implements LocationListener {
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
    RelativeLayout RelativeKmRedonda;
    private FiltrosListener filtrosListener;
    private LocationListener locationListener;

    private ListFragment listFragment;
    List<Benzinera> benzinerasList;
    List<PuntRecarrega> puntRecarregaList;
    ServiceApi serviceApi;
    private Location actualPos;
    private LatLng actualPosBtn;
    private FusedLocationProviderClient fusedLocationClient;
    String tipusSub;


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
        localize();
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
        serviceApi = FitRetro.getServiceApi();
        return view;
    }

    public void initItems() {
        SubministradorBenzineresCKBX = (CheckBox) view.findViewById(R.id.ckeckboxBenzineres);
        SubministradorHidrogeneresCKBX = (CheckBox) view.findViewById(R.id.checkBoxHidrogeneres);
        SubministradorPuntsCKBX = (CheckBox) view.findViewById(R.id.ckeckboxPunts);
        SubministradorGasosCKBX = (CheckBox) view.findViewById(R.id.ckeckboxGAS);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
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

    public String checkBoxTypeGas(){
        String typeGas = "";
        for (CheckBox ck: enchufeTypes) {
            if (ck.isChecked()){
                if (!ck.equals(CombustibleGasoil)){
                    typeGas = (String) ck.getText();
                } else {
                    typeGas = "gasoil";
                }
            }
        }
        return typeGas;
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
                actualPosBtn = new LatLng(actualPos.getLatitude(),actualPos.getLongitude());
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int KmInt = Integer.parseInt(ETKmVoltant.getText().toString());
                String typeGas = checkBoxTypeGas();
                Log.d("dades","KM: " + KmInt + "Lat: " + actualPosBtn.lat + " Lng: " + actualPosBtn.lng + " " + typeGas);
                if (SubministradorPuntsCKBX.isChecked()){
                    getPuntsFinder(KmInt,actualPosBtn.lat,actualPosBtn.lng,typeGas);
                    tipusSub = "punts";
                } else if(SubministradorBenzineresCKBX.isChecked()) {
                    tipusSub = "benz";
                    getBenzineresFinder(KmInt, actualPosBtn.lat, actualPosBtn.lng,typeGas);
                } else {
                    tipusSub = "gas";
                    getBenzineresFinder(KmInt,actualPosBtn.lat,actualPosBtn.lng,typeGas);
                }
            }
        });
    }

    public void storeListOnPrefs(List<Benzinera> benzineras, String tipusSub){
        for (Benzinera benzinera: benzineras) {
            Log.d("benzinera",benzinera.getNom() + " DistFromActual: " +benzinera.getDistFromActual());
        }
        if (getActivity()!=null) {

            SharedPreferences preferences = getActivity().getSharedPreferences("gaso_list", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            Gson gson = new Gson();
            String jsonGasolineras = gson.toJson(benzineras);
            editor.clear();
            editor.putString("benzineres", jsonGasolineras);
            editor.putString("lat", String.valueOf(actualPosBtn.lat));
            editor.putString("lng", String.valueOf(actualPosBtn.lng));
            editor.putString("sub", tipusSub);
            Log.d("editoraaaa",tipusSub);
            if (tipusSub.equalsIgnoreCase("benz") || tipusSub.equalsIgnoreCase("benzina")) {
                Log.d("editoraaaa2",tipusSub);
                editor.putString("tipo","benz");
            } else {
                editor.putString("tipo","gas");
            }
            Log.d("Mapeando", "Latitude2323: " + actualPosBtn.lat + "Longitude2323: " + actualPosBtn.lng);
            editor.apply();
        }

        if (filtrosListener != null) {
            filtrosListener.getFiltros();
        }else{
            Log.d("filtros", "storeOnPrefsBenz: listener es null");
        }
    }

    public void storeListPuntsOnPrefs(List<PuntRecarrega> puntRecarregaList){
        for (PuntRecarrega pr: puntRecarregaList) {
            Log.d("punt",pr.getNom() + " DistFromActual: " +pr.getDistFromActual());
        }
        SharedPreferences preferences = getActivity().getSharedPreferences("gaso_list",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String jsonPunts = gson.toJson(puntRecarregaList);
        editor.clear();
        editor.putString("punts",jsonPunts);
        editor.putString("lat", String.valueOf(actualPosBtn.lat));
        editor.putString("lng", String.valueOf(actualPosBtn.lng));
        editor.putString("sub",tipusSub);
        Log.d("Mapeando", "Latitude2323: " + actualPosBtn.lat + "Longitude2323: " + actualPosBtn.lng);
        editor.apply();

        if (filtrosListener != null) {
            filtrosListener.getFiltros();
        }else{
            Log.d("filtros", "storeOnPrefsPunts: listener es null");
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    public interface FiltrosListener{

        void getFiltros();
    }


    public void getBenzineresFinder(double kmRedonda, double locationLat, double locationLong,String typeGAS){
        Call<List<Benzinera>> call = serviceApi.listBenzineresFinderValPrice(locationLat, locationLong,kmRedonda,typeGAS);

        call.enqueue(new Callback<List<Benzinera>>() {
            @Override
            public void onResponse(Call<List<Benzinera>> call, retrofit2.Response<List<Benzinera>> response) {
                if (response.isSuccessful()) {
                    benzinerasList = response.body();
                    if (benzinerasList != null) {
                        if (benzinerasList.size() != 0){
                        Log.d("getingbenzineres", benzinerasList.get(0).getNom());
                        Log.d("getingbenzineres", String.valueOf(benzinerasList.size()));
                            for (Benzinera b: benzinerasList) {
                                //LatLng inici = new LatLng(lat,lng); -> actualPos
                                LatLng benzinera = new LatLng(b.getLatitude(),b.getLongitude());
                                try {
                                    b.setDistFromActual(getDurationInMinutes(actualPosBtn,benzinera));
                                } catch (IOException | InterruptedException | ApiException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        storeListOnPrefs(benzinerasList,tipusSub);
                        } else {
                            Toast.makeText(getContext(), "No hi han subministradors propers", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Respuesta no exitosa
                    Log.e("getingbenzineres", "Respuesta no exitosa: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Benzinera>> call, Throwable t) {
                Log.e("getingbenzineres", t.getMessage());

                if (t instanceof IOException) {
                    // Error de red o servidor
                    Log.e("getingbenzineres", "Error de red o servidor");
                } else {
                    // Otro tipo de error
                    Log.e("getingbenzineres", "Otro tipo de error");
                }
            }
        });
    }

    public void getPuntsFinder(double kmRedonda, double locationLat, double locationLong,String typePunt){
        Call<List<PuntRecarrega>> call = serviceApi.listPuntsFinderVal(locationLat, locationLong,kmRedonda, typePunt);

        call.enqueue(new Callback<List<PuntRecarrega>>() {
            @Override
            public void onResponse(Call<List<PuntRecarrega>> call, Response<List<PuntRecarrega>> response) {
                if (response.isSuccessful()){
                    puntRecarregaList = response.body();
                    if (puntRecarregaList!=null){
                        if (puntRecarregaList.size()!=0){
                            Log.d("getingpuntsRecarrega", puntRecarregaList.get(0).getNom());
                            Log.d("getingpuntsRecarrega", String.valueOf(puntRecarregaList.size()));
                            for (PuntRecarrega pr: puntRecarregaList) {
                                Log.d("getingpuntsRecarrega", "lat: " + pr.getLatitude() + "lng : " + pr.getLongitude());
                                LatLng punt = new LatLng(pr.getLatitude(),pr.getLongitude());
                                Log.d("getingpuntsRecarrega", "lat2: " + pr.getLatitude() + "lng2 : " + pr.getLongitude());
                                try {
                                    pr.setDistFromActual(getDurationInMinutes(actualPosBtn,punt));
                                    Log.d("getingpuntsRecarrega", "lat3: " + pr.getLatitude());
                                } catch (IOException | InterruptedException | ApiException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            storeListPuntsOnPrefs(puntRecarregaList);
                            Log.d("getingpuntsRecarrega", "lat: ");
                        } else {
                            Toast.makeText(getContext(), "No hi han subministradors propers", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Log.e("getingpuntsRecarrega", "Respuesta no exitosa: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PuntRecarrega>> call, Throwable t) {
                Log.e("getingpuntsRecarrega", t.getMessage());

                if (t instanceof IOException) {
                    // Error de red o servidor
                    Log.e("getingpuntsRecarrega", "Error de red o servidor");
                } else {
                    // Otro tipo de error
                    Log.e("getingpuntsRecarrega", "Otro tipo de error");
                }
            }
        });
    }

    public long getDurationInMinutes(LatLng inci, LatLng Final) throws IOException, InterruptedException, ApiException {
        long durationInMinutes = 0;
        Log.d("geting","iniciduration");
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCqWBGxRFvmbc0zqwhClvJRCAqfw4KcXEM")
                .build();
        Log.d("geting","afterContext");

        DirectionsResult result = DirectionsApi.newRequest(context)
                .origin(new com.google.maps.model.LatLng(inci.lat, inci.lng))
                .destination(new com.google.maps.model.LatLng(Final.lat, Final.lng))
                .mode(TravelMode.DRIVING)
                .await();
        Log.d("geting","afterDirectionsResult");

        if (result.routes.length > 0) {
            Log.d("geting","if");
            DirectionsRoute route = result.routes[0];
            Log.d("geting","routes0");
            long durationInSeconds = route.legs[0].duration.inSeconds;
            Log.d("geting","durationInSecs: " + durationInSeconds);
            durationInMinutes = durationInSeconds/60;
            Log.d("geting","durationInMinutes: " + durationInMinutes);
        }
        return  durationInMinutes;
    }

    @SuppressLint("MissingPermission")
    public void localize(){
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);


        String provider = locationManager.getBestProvider(new Criteria(), false);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                actualPos = location;
                Log.d("quepasaeeeeFragment", "Latitude: " + actualPos.getLatitude() + ", Longitude: " + actualPos.getLongitude());

            }
        };
        locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);
    }
}