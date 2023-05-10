package com.example.a2223damp3grup01.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ParadesKilometresAdapter;
import com.example.a2223damp3grup01.objects.ParadaKilometre;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class FiltrosRutaFragment extends Fragment implements LocationListener {

    CheckBox SubministradorBenzineresCKBX, SubministradorHidrogeneresCKBX, SubministradorPuntsCKBX, SubministradorGasosCKBX;

    CheckBox CombustibleBenzina, CombustibleGasoil;
    EditText direccioIniciEditText, direccioFinalEditText;
    Button utilitzarDireccioActual, buscarAlMapa, ValidarRuta;
    TextView KilometresDeLaRuta;
    EditText numeroDeParadesEditText;
    CheckBox ParadesAutomatiques, ParadesManuals;
    RecyclerView ParadesManualsRecyclerView;
    Button DibuixaRutaManual;
    Button DibuixaRutaAutomatic;

    RelativeLayout RelativeSubministrador, RelativeTipusCombustible, RelativeIniciFinalBenz, RelativeNumeroParades, RelativeDibuixaManuals, RelativeDibuixaAutomatiques;

    View view;
    String Subministrador;

    FusedLocationProviderClient fusedLocationClient;

    Location actualPos;
    LocationManager locationManager;
    // Obtener el proveedor de ubicación
    LocationListener locationListener;
    String provider;

    double distanciaMetresRuta1=0;

    Location posInici;
    Location posFinal;

    LatLng latlngInici;
    LatLng latLngFinal;

    ArrayList<LatLng> rutaNoParades = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filtros_ruta, container, false);
        //metodo para inicializar todos los componentes para interactuar
        localize();
        initItems();
        ClickListeners();
        initSubministradorsListeners();


        return view;

    }

    @SuppressLint("MissingPermission")
    public void localize(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);



        provider = locationManager.getBestProvider(new Criteria(), false);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                actualPos = location;
                Log.d("quepasaeeeeFragment", "Latitude: " + actualPos.getLatitude() + ", Longitude: " + actualPos.getLongitude());

            }
        };
        locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);
    }
    public void initItems() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());


        SubministradorBenzineresCKBX = (CheckBox) view.findViewById(R.id.ckeckboxBenzineresRut);
        SubministradorHidrogeneresCKBX = (CheckBox) view.findViewById(R.id.checkBoxHidrogeneresRut);
        SubministradorPuntsCKBX = (CheckBox) view.findViewById(R.id.ckeckboxPuntsRut);
        SubministradorGasosCKBX = (CheckBox) view.findViewById(R.id.ckeckboxGASRut);

        RelativeSubministrador = (RelativeLayout) view.findViewById(R.id.RelativeSubministrador);
        RelativeTipusCombustible = (RelativeLayout) view.findViewById(R.id.relativeBenzineresRut);
        RelativeNumeroParades = (RelativeLayout) view.findViewById(R.id.relativeNumeroParadesBenz);
        RelativeDibuixaAutomatiques = (RelativeLayout) view.findViewById(R.id.DibuixaParadesAutomatiques);
        RelativeDibuixaManuals = (RelativeLayout) view.findViewById(R.id.DibuixaParadesManuals);

        CombustibleBenzina = (CheckBox) view.findViewById(R.id.ckeckboxBenzRut);
        CombustibleGasoil = (CheckBox) view.findViewById(R.id.checkBoxGasoil);

        ParadesAutomatiques = (CheckBox) view.findViewById(R.id.paradesAutoRut);
        ParadesManuals = (CheckBox) view.findViewById(R.id.paradesManuRut);

        RelativeIniciFinalBenz = (RelativeLayout) view.findViewById(R.id.iniciFinalBenzRut);
        utilitzarDireccioActual = (Button) view.findViewById(R.id.buttonActualRut);

        direccioIniciEditText = (EditText) view.findViewById(R.id.ETiniciRuta);
        direccioFinalEditText = (EditText) view.findViewById(R.id.ETfinalRuta);
        numeroDeParadesEditText = (EditText) view.findViewById(R.id.numParadesET);

        KilometresDeLaRuta= (TextView) view.findViewById(R.id.kilometresRut);

        ValidarRuta = (Button) view.findViewById(R.id.buttonValidateRut);

        ParadesManualsRecyclerView = (RecyclerView) view.findViewById(R.id.RVparadasManuBenz);
    }

    public void ClickListeners() {
        utilitzarDireccioActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarETInici();
            }
        });

        ValidarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    validarIniciFinalRuta(direccioIniciEditText.getText().toString(),direccioFinalEditText.getText().toString());
                } catch (IOException | InterruptedException | ApiException e) {
                    e.printStackTrace();
                }

                DisplayRelativeLayout(RelativeNumeroParades);
                DecimalFormat df = new DecimalFormat("#.##");
                String formatted = df.format(distanciaMetresRuta1/1000);
                KilometresDeLaRuta.setText(String.valueOf(formatted)+"km");
                initParadesListeners(ParadesAutomatiques,ParadesManuals);
            }
        });
    }

    public void validarIniciFinalRuta(String inici, String Final) throws IOException, InterruptedException, ApiException {
        LatLng iniciL;
        LatLng finalL;

        iniciL=latLngFromString(inici);
        finalL=latLngFromString(Final);

        Log.d("validarRuta", "INICI"+String.valueOf(iniciL.latitude)+ " "+String.valueOf(iniciL.longitude));
        Log.d("validarRuta", "FINAL"+String.valueOf(finalL.latitude)+ " "+String.valueOf(finalL.longitude));

        getLatLngsIniciFinal(iniciL,finalL);


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
        DisplayTypeRelative();
    }

    public void initParadesListeners(CheckBox auto, CheckBox manu){
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numeroDeParadesEditText.getText().toString().equals(null)){
                    Toast.makeText(getContext(), "posa un numero de parades", Toast.LENGTH_SHORT).show();
                }
                else{
                    manu.setChecked(false);
                    DisplayRelativeLayout(RelativeDibuixaAutomatiques);
                    HideRelativeLayout(RelativeDibuixaManuals);

                }
                }
        });

        manu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (numeroDeParadesEditText.getText().toString().equals(null)){
                Toast.makeText(getContext(), "posa un numero de parades", Toast.LENGTH_SHORT).show();
            }else{
                auto.setChecked(false);
                DisplayRelativeLayout(RelativeDibuixaManuals);
                HideRelativeLayout(RelativeDibuixaAutomatiques);

                createRecyclerParadesManu(ParadesManualsRecyclerView,numeroDeParadesEditText);
            }
            }
        });



    }

    public void createRecyclerParadesManu(RecyclerView rv, EditText numET){
        String numParadesString = numET.getText().toString();
        Log.d("creatingRV", "createRecyclerParadesManu: "+numParadesString);
        int numParades = Integer.parseInt(numParadesString);
        List<ParadaKilometre> parades= new ArrayList<>();

        for (int i = 0; i < numParades; i++) {
            ParadaKilometre pk = new ParadaKilometre();
            pk.setNumParadaString("PARADA" + String.valueOf(i+1));
            pk.setNumParadaInt(i);
            parades.add(pk);
        }

        ParadesKilometresAdapter adapter = new ParadesKilometresAdapter(parades);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        rv.setAdapter(adapter);
    }



    public void DisplayTypeRelative() {
        if (SubministradorBenzineresCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.VISIBLE);
        }
        if (SubministradorGasosCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeIniciFinalBenz.setVisibility(View.GONE);

        }
        if (SubministradorHidrogeneresCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeIniciFinalBenz.setVisibility(View.GONE);


        }
        if (SubministradorPuntsCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeIniciFinalBenz.setVisibility(View.GONE);


        }
        DisplayIniciFinalRut();
    }

    public void DisplayIniciFinalRut() {
        if (SubministradorBenzineresCKBX.isChecked()) {
            RelativeIniciFinalBenz.setVisibility(View.VISIBLE);

        }
        if (SubministradorGasosCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.GONE);

        }
        if (SubministradorHidrogeneresCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.GONE);

        }
        if (SubministradorPuntsCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.GONE);

        }
    }

    public void DisplayRelativeLayout(RelativeLayout layout){
        layout.setVisibility(View.VISIBLE);
    }

    public void HideRelativeLayout(RelativeLayout layout){
        layout.setVisibility(View.GONE);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    public void cambiarETInici(){
        if (actualPos == null){
            Log.d("quepasaeeeeFragment", "ES NULL");
            Toast.makeText(getContext(), "Buscant Localitzacio", Toast.LENGTH_SHORT).show();

        }else{
            Log.d("quepasaeeeeFragment", "Latitude: " + actualPos.getLatitude() + ", Longitude: " + actualPos.getLongitude());

            posInici = actualPos;

            latlngInici = new LatLng(posInici.getLatitude(),posInici.getLongitude());

            try {
                direccioIniciEditText.setText(getAdresa(latlngInici));
            } catch (IOException e) {
                e.printStackTrace();
            }    
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

    public LatLng latLngFromString(String direccio) throws IOException {
        LatLng returnL = null;
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addresses = geocoder.getFromLocationName(direccio, 1);
        if (addresses.size() > 0) {
            Address address = addresses.get(0);
            double latitude = address.getLatitude();
            double longitude = address.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            // hacer algo con el objeto LatLng
            returnL = latLng;
        } else {
            // no se encontró la dirección
        }

        return returnL;
    }

    public void getLatLngsIniciFinal(LatLng inci, LatLng Final) throws IOException, InterruptedException, ApiException {
        Log.d("ruta1", "getLatLngsIniciFinal: inici latitude:"+ inci.latitude + " inici longitude "+ inci.longitude);
        Log.d("ruta1", "getLatLngsIniciFinal: FINAL latitude:"+ Final.latitude + " Final longitude "+ Final.longitude);


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
            distanciaMetresRuta1=0;
            for (DirectionsLeg leg : route.legs) {
                distanciaMetresRuta1+=leg.distance.inMeters;
                for (DirectionsStep step : leg.steps) {
                    List<com.google.maps.model.LatLng> path = step.polyline.decodePath();

                    for (com.google.maps.model.LatLng latLng : path) {
                        rutaNoParades.add(new LatLng(latLng.lat, latLng.lng));
                    }
                }

            }
            Log.d("ruta1", "getLatLngsIniciFinal: num latlongs "+ rutaNoParades.size());
            Log.d("ruta1", "getLatLngsIniciFinal: distancia en m "+ distanciaMetresRuta1);
            Log.d("ruta1", "getLatLngsIniciFinal: distancia en km "+ distanciaMetresRuta1/1000);


        } else {
            // no se encontró una ruta
        }


    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // código para manejar el cambio de estado de la ubicación
    }

}