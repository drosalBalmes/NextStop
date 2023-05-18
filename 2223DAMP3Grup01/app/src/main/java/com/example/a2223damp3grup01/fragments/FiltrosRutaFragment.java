package com.example.a2223damp3grup01.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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

import androidx.annotation.FontRes;
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
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.FitRetro;
import com.example.a2223damp3grup01.objects.ParadaKilometre;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.SphericalUtil;
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
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FiltrosRutaFragment extends Fragment implements LocationListener{

    CheckBox SubministradorBenzineresCKBX, SubministradorHidrogeneresCKBX, SubministradorPuntsCKBX, SubministradorGasosCKBX;

    CheckBox CombustibleBenzina, CombustibleGasoil,
    gasGLP,gasGNC,gasGNL,
    mennekesm, chademo, mennekesf,tesla,cssCombo,schuko;
    ArrayList<CheckBox> enchufeTypes= new ArrayList<>();

    EditText direccioIniciEditText, direccioFinalEditText;
    Button utilitzarDireccioActual, buscarAlMapa, ValidarRuta;
    TextView KilometresDeLaRuta;
    EditText numeroDeParadesEditText;
    CheckBox ParadesAutomatiques, ParadesManuals;
    RecyclerView ParadesManualsRecyclerView;
    Button DibuixaRutaManual;
    Button DibuixaRutaAutomatic;

    RelativeLayout RelativeSubministrador, RelativeTipusCombustible, RelativeIniciFinalBenz, RelativeNumeroParades, RelativeDibuixaManuals, RelativeDibuixaAutomatiques;
    RelativeLayout RelativeTipusCarregador, RelativeTipusGas;

    View view;
    String Subministrador;


    FusedLocationProviderClient fusedLocationClient;

    List<ParadaKilometre> parades= new ArrayList<>();


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

    ServiceApi serviceApi;

    List<Benzinera> benzinerasListParades = new ArrayList<>();
    List<PuntRecarrega> puntsListParades = new ArrayList<>();

    List<Benzinera> benzinerasListParadesEnviar = new ArrayList<>();
    List<PuntRecarrega> puntsListParadesEnviar = new ArrayList<>();
    int stopType=0;

    private FiltrosRutaListener listener;
    private MapaRutaFragment mapaRutaFragment;

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
        for (CheckBox c :
                enchufeTypes) {
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickCheckboxType(c);
                }
            });
        }
        serviceApi = FitRetro.getServiceApi();

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

        initItemsBenz();
        initItemsElec();
        initItemsGas();
    }

    public void initItemsBenz(){
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
        DibuixaRutaManual = (Button) view.findViewById(R.id.dibuixaRutaManuBenz);
        DibuixaRutaAutomatic =(Button) view.findViewById(R.id.buttonParadasAutoButton);
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
        RelativeTipusGas=(RelativeLayout) view.findViewById(R.id.relativeGasosRut);

        gasGLP = view.findViewById(R.id.GLPcheck);
        gasGNC = view.findViewById(R.id.GNCcheck);
        gasGNL = view.findViewById(R.id.GNLcheck);
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
                mostrarNumParadesLayout();
            }
        });

        DibuixaRutaManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ferRutesParades(rutaNoParades,parades);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        });

        DibuixaRutaAutomatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ferRutesParadesAuto(rutaNoParades,numeroDeParadesEditText);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void ClickCheckboxType(CheckBox clicked){
        for (CheckBox c: enchufeTypes
             ) {
            if (c!=clicked){
                c.setChecked(false);
            }
        }
    }

    public void mostrarNumParadesLayout(){
        if (SubministradorBenzineresCKBX.isChecked()){
            cambiarMarginTop(RelativeNumeroParades,580);

        }else if (SubministradorPuntsCKBX.isChecked()){
            cambiarMarginTop(RelativeNumeroParades,700);

        }else if (SubministradorGasosCKBX.isChecked()){

        }

        DisplayRelativeLayout(RelativeNumeroParades);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(distanciaMetresRuta1/1000);
        KilometresDeLaRuta.setText(String.valueOf(formatted)+"km");
        initParadesListeners(ParadesAutomatiques,ParadesManuals);
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
        for (CheckBox ck:
             enchufeTypes) {
            ck.setChecked(false);
        }
        DisplayTypeRelative();
        changeMarginsTop();
    }

    public void DisplayTypeRelative() {
        if (SubministradorBenzineresCKBX.isChecked()) {
            RelativeTipusCombustible.setVisibility(View.VISIBLE);


            RelativeTipusCarregador.setVisibility(View.GONE);
            RelativeTipusGas.setVisibility(View.GONE);
        }
        if (SubministradorGasosCKBX.isChecked()) {
            RelativeTipusGas.setVisibility(View.VISIBLE);


            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeTipusCarregador.setVisibility(View.GONE);

        }
        if (SubministradorHidrogeneresCKBX.isChecked()) {

            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeTipusCarregador.setVisibility(View.GONE);
            RelativeIniciFinalBenz.setVisibility(View.GONE);


        }
        if (SubministradorPuntsCKBX.isChecked()) {
            RelativeTipusCarregador.setVisibility(View.VISIBLE);


            RelativeTipusCombustible.setVisibility(View.GONE);
            RelativeTipusGas.setVisibility(View.GONE);

        }
        DisplayIniciFinalRut();
    }

    public void DisplayIniciFinalRut() {

        RelativeIniciFinalBenz.setVisibility(View.VISIBLE);

    }

    public void changeMarginsTop(){
        if (SubministradorBenzineresCKBX.isChecked()) {
            cambiarMarginTop(RelativeIniciFinalBenz,250);
            cambiarMarginTop(RelativeNumeroParades,550);
            cambiarMarginTop(RelativeDibuixaAutomatiques,950);
            cambiarMarginTop(RelativeDibuixaManuals,950);

        }
        if (SubministradorGasosCKBX.isChecked()) {
            cambiarMarginTop(RelativeIniciFinalBenz,300);
            cambiarMarginTop(RelativeNumeroParades,600);
            cambiarMarginTop(RelativeDibuixaAutomatiques,1000);
            cambiarMarginTop(RelativeDibuixaManuals,1000);
        }
        if (SubministradorHidrogeneresCKBX.isChecked()) {

        }
        if (SubministradorPuntsCKBX.isChecked()) {
            cambiarMarginTop(RelativeIniciFinalBenz,400);
            cambiarMarginTop(RelativeNumeroParades,700);
            cambiarMarginTop(RelativeDibuixaAutomatiques,1100);
            cambiarMarginTop(RelativeDibuixaManuals,1100);


        }
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
        parades.clear();

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


    public void ferRutesParades(List<LatLng> ruta, List<ParadaKilometre> parades ) throws IOException, InterruptedException, ApiException {

        for (ParadaKilometre pk : parades) {
            pk.ETtoInt();


        }

        boolean paradesCorrectes = true;
        float distanciaKMruta1 = (float) (distanciaMetresRuta1 / 1000);

        for (int i = 0; i < parades.size() - 1; i++) {
            ParadaKilometre pkActual = parades.get(i);
            ParadaKilometre pksig = parades.get(i + 1);
            if (pkActual.getKm() > pksig.getKm()) {
                Toast.makeText(getContext(), "Revisa els kilometres de les parades", Toast.LENGTH_SHORT).show();
                paradesCorrectes = false;
            }
        }


        List<LatLng> paradasLatLng = null;
        if (paradesCorrectes == true) {
            /*distancia de la ruta*/
            int distanciaRuta = ruta.size();
            Log.d("paradees", "size de latlngs " + distanciaRuta);

            /*los kilometros en los que hay paradas*/
            List<Integer> intsParades = new ArrayList<>();
            for (ParadaKilometre p : parades) {
                intsParades.add(p.getKm());
                Log.d("paradees", "km de parada " + p.getKm());

            }

            /*obtener que porcentaje de la ruta equivale cada parada en kilometros
             * TODAVIA NO SON LATLONGS*/
            List<Float> percentatgesParades = new ArrayList<>();

            for (Integer Kmparada : intsParades) {
                percentatgesParades.add(Kmparada / distanciaKMruta1 * 100);
                Log.d("paradees", "ferRutesParades: percentatge " + Kmparada / distanciaKMruta1 * 100);

            }

            /*obtener los latlngs de todoas las paradas*/
            paradasLatLng = new ArrayList<>();
            Log.d("paradees", "numero percentatge parades numaafegir " + percentatgesParades.size() );

            for (Float paradapercent : percentatgesParades) {

                double doublenumAafegir1 = (paradapercent / 100);
                double doublenumAafegir2 = doublenumAafegir1 * distanciaRuta;


                Log.d("paradees", "ferRutesParades: numaafegir " + doublenumAafegir2);

                int numAafegir = (int) Math.round(doublenumAafegir2);

                paradasLatLng.add(ruta.get(numAafegir));
                Log.d("paradees", "ferRutesParades: parada " + ruta.get(numAafegir).latitude + " " + ruta.get(numAafegir).longitude);
            }
            Log.d("paradees", "paradesLatlong SIZE " + paradasLatLng.size() );

            List<String> puntas = typesOfEnergy();
            for (String s :
                    puntas) {
                Log.d("puntas", "ferRutesParadesAuto: " + s);
            }

            /*miro si las checkbox son gasolinas o puntos de recarga i busco las estaciones mas cercanas*/
            for (LatLng pos :
                    paradasLatLng) {
                getClosestType(pos,puntas.get(0));
            }


            storeRouteOnPreferencesBenz(ruta,paradasLatLng,puntas.get(0));




        }

    }

    public void ferRutesParadesAuto(List<LatLng> ruta, EditText numParades) throws IOException, InterruptedException, ApiException {
        String numeroParadesS = numParades.getText().toString();
        int numParadesInt = Integer.parseInt(numeroParadesS)+1;

        // Calcular la distancia total de la ruta en metros
        double distanciaTotal = SphericalUtil.computeLength(ruta);



        // Calcular la distancia entre cada parada
        double distanciaEntreParadas = distanciaTotal / (numParadesInt);

        // Recorrer la ruta, añadiendo puntos de parada según la distancia acumulada
        double distanciaAcumulada = 0;
        List<LatLng> paradasList = new ArrayList<>();
        for (int i = 1; i < ruta.size() - 1; i++) {
            LatLng puntoAnterior = ruta.get(i - 1);
            LatLng puntoActual = ruta.get(i);
            double distanciaSegmento = SphericalUtil.computeDistanceBetween(puntoAnterior, puntoActual);
            distanciaAcumulada += distanciaSegmento;
            if (distanciaAcumulada >= distanciaEntreParadas) {
                // Añadir un punto de parada en este punto de la ruta
                double fraccion = (distanciaAcumulada - distanciaEntreParadas) / distanciaSegmento;
                LatLng puntoParada = new LatLng(
                        puntoAnterior.latitude + fraccion * (puntoActual.latitude - puntoAnterior.latitude),
                        puntoAnterior.longitude + fraccion * (puntoActual.longitude - puntoAnterior.longitude));
                paradasList.add(puntoParada);
                distanciaAcumulada = distanciaSegmento - (distanciaAcumulada - distanciaEntreParadas);
            }
        }

        /*cojo de las checkbox las que estan con un tick */
        List<String> puntas = typesOfEnergy();
        for (String s :
                puntas) {
            Log.d("puntas", "ferRutesParadesAuto: " + s);
        }

        /*miro si las checkbox son gasolinas o puntos de recarga i busco las estaciones mas cercanas*/
        for (LatLng pos :
                paradasList) {
            Log.d("buscant parades", "ferRutesParadesAuto: ");

            getClosestType(pos,puntas.get(0));
        }


        storeRouteOnPreferencesBenz(ruta,paradasList,puntas.get(0));

    }

    public List<String> typesOfEnergy(){
        List<String> returnList = new ArrayList<>();

        for (CheckBox ck :
                enchufeTypes) {
            if (ck.isChecked()){
                returnList.add(ck.getText().toString());
            }
        }




        return returnList;
    }



    public void storeRouteOnPreferencesBenz(List<LatLng> ruta, List<LatLng> paradas,String type) throws IOException, InterruptedException, ApiException {
        SharedPreferences prefs = getActivity().getSharedPreferences("route_pref", Context.MODE_PRIVATE);

        Gson gson = new Gson();

        String jsonRuta = gson.toJson(ruta);
        String jsonParadas = gson.toJson(paradas);
        Log.d("paradees", "sasdasdasdasdasd " + paradas.size());



        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.putString("ruta", jsonRuta);
        editor.putString("paradas", jsonParadas);
        editor.putInt("duracio", Math.toIntExact(storeRouteTimeOnPrefs()));
        editor.putString("combus", type);
        editor.apply();

        if (listener != null) {
            listener.getRouteFromPrefs();
        }else{
            Log.d("paradees", "storeRouteOnPreferences: listener es null");
        }
    }





    public void setMapaRutaFragment(MapaRutaFragment mapaRutaFragment) {
        this.mapaRutaFragment = mapaRutaFragment;
        this.listener = mapaRutaFragment;
    }

    public interface FiltrosRutaListener{

        void getRouteFromPrefs();
    }


    public void getClosestType(LatLng position, String type){
        if (type.equals("Benzina")||type.equals("Gasoil/Diesel")){
            stopType = 1;

            Log.d("drawRouteMapsFragment", "getClosestType: busca benzineres type: " + stopType);
            if (type.equalsIgnoreCase("Gasoil/Diesel")){
                type = "gasoil";
            }
            getClosestBenz(position,type);



        }

        if (type.equals("GLP")||type.equals("GNC")||type.equals("GNL")){
            stopType = 2;
            Log.d("drawRouteMapsFragment", "getClosestType: busca gasos type: " + stopType);

            getClosestBenz(position,type);

        }

        if (type.equals("MENNEKES.M")||type.equals("CHADEMO")||type.equals("MENNEKES.F")||type.equals("TESLA")||type.equals("CCS Combo2")||type.equals("Schuko")){
            stopType = 3;
            Log.d("drawRouteMapsFragment", "getClosestType: elec type: "+ stopType);

            getClosestPunt(position,type);

        }

    }



    public void getClosestBenz(LatLng position,String type){
        getBenzineresFinder(position.latitude,position.longitude,type);


    }

    public void getClosestPunt(LatLng position, String type){
        getPuntsFinder(position.latitude,position.longitude,type);
    }

    public void getBenzineresFinder(double locationLat, double locationLong,String type){
        Call<List<Benzinera>> call = serviceApi.listBenzClosestValPrice(locationLong, locationLat,10,type);

        call.enqueue(new Callback<List<Benzinera>>() {
            @Override
            public void onResponse(Call<List<Benzinera>> call, retrofit2.Response<List<Benzinera>> response) {
                if (response.isSuccessful()) {
                    benzinerasListParades = response.body();
                    if (benzinerasListParades != null) {
                        Log.d("getingbenzineres", benzinerasListParades.get(0).getNom());
                        Log.d("getingbenzineres", String.valueOf(benzinerasListParades.size()));
                        Log.d("dimequevaporfa", "onResponse:  " + benzinerasListParades.get(0).getPreuSP95() + "  " + benzinerasListParades.get(0).getPreuGasoil());

                        storeStopsOnPrefs();

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


    public void getPuntsFinder(double locationLat, double locationLong,String type){
        Call<List<PuntRecarrega>> call = serviceApi.listPuntsClosest(locationLong, locationLat,10,type);

        call.enqueue(new Callback<List<PuntRecarrega>>() {
            @Override
            public void onResponse(Call<List<PuntRecarrega>> call, Response<List<PuntRecarrega>> response) {
                if (response.isSuccessful()) {
                    puntsListParades = response.body();
                    if (puntsListParades != null) {
                        Log.d("getingbenzineres", puntsListParades.get(0).getNom());
                        Log.d("getingbenzineres", String.valueOf(puntsListParades.size()));

                        storeStopsOnPrefs();

                    }
                } else {
                    // Respuesta no exitosa
                    Log.e("getingbenzineres", "Respuesta no exitosa: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PuntRecarrega>> call, Throwable t) {

            }
        });

    }



    public void storeStopsOnPrefs(){
        String TAG = "storingPosibleStopsOnPrefs";
        if (stopType==1){
            SharedPreferences prefs = getActivity().getSharedPreferences("route_pref", Context.MODE_PRIVATE);

            Gson gson = new Gson();
            for (Benzinera b :
                    benzinerasListParades) {
                benzinerasListParadesEnviar.add(b);
            }

            String jsonPosParadas = gson.toJson(benzinerasListParadesEnviar);
            Log.d(TAG, "storeStopsOnPrefs: tamany del que guardo "+benzinerasListParadesEnviar.size());


            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("posiblesParades", jsonPosParadas);
            editor.putString("posiblesParadesType", "1");
            Log.d(TAG, "storeStopsOnPrefs: posiblesParadasType 1");

            editor.apply();

        }else if (stopType==2){
            SharedPreferences prefs = getActivity().getSharedPreferences("route_pref", Context.MODE_PRIVATE);

            Gson gson = new Gson();

            for (Benzinera b :
                    benzinerasListParades) {
                benzinerasListParadesEnviar.add(b);
            }

            String jsonPosParadas = gson.toJson(benzinerasListParadesEnviar);
            Log.d(TAG, "storeStopsOnPrefs: tamany del que guardo "+benzinerasListParadesEnviar.size());


            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("posiblesParades", jsonPosParadas);
            editor.putString("posiblesParadesType", "2");

            editor.apply();
            Log.d(TAG, "storeStopsOnPrefs: posiblesParadasType 2");


        }else if (stopType==3){
            SharedPreferences prefs = getActivity().getSharedPreferences("route_pref", Context.MODE_PRIVATE);

            Gson gson = new Gson();

            for (PuntRecarrega pr :
                    puntsListParades) {
                puntsListParadesEnviar.add(pr);
            }



            String jsonPosParadas = gson.toJson(puntsListParadesEnviar);
            Log.d(TAG, "storeStopsOnPrefs: tamany del que guardo "+puntsListParadesEnviar.size());




            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("posiblesParades", jsonPosParadas);
            editor.putString("posiblesParadesType", "3");

            editor.apply();
            Log.d(TAG, "storeStopsOnPrefs: posiblesParadasType 3");


        }

    }

    public long storeRouteTimeOnPrefs() throws IOException, InterruptedException, ApiException {
       long minutes = getDurationInMinutes(rutaNoParades.get(0),rutaNoParades.get(rutaNoParades.size()-1));

        Log.d("storingTimeOnPreferences", "storeRouteTimeOnPrefs: " + minutes);

        return minutes;
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