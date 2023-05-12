package com.example.a2223damp3grup01.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.databinding.ActivityMainBinding;
import com.example.a2223damp3grup01.fragments.GasolinerasFragment;
import com.example.a2223damp3grup01.fragments.RutasFragment;
import com.example.a2223damp3grup01.fragments.MapsFragment;
import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;




    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        askPermissions();
        replaceFragment(new GasolinerasFragment());
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//        provider = locationManager.getBestProvider(new Criteria(), false);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                actualPos = location;
//                Log.d("quepasaeeee", "Latitude: " + actualPos.getLatitude() + ", Longitude: " + actualPos.getLongitude());
//
//            }
//        };
//        locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);
//        replaceFragment(new GasolinerasFragment());


        bottomNavSelected();


    }

    public void bottomNavSelected() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.gasolineras:
                    replaceFragment(new GasolinerasFragment());
                    break;
                case R.id.rutas:
                    replaceFragment(new RutasFragment());
                    break;
            }
            return true;
        });
    }

    public void cambiarFragment(Button button, Fragment fragment) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(fragment);
            }
        });
    }

    public void toProfile(CardView button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void replaceFragment(Fragment frahment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, frahment);
        fragmentTransaction.commit();
    }

    public void askPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, proceed to location access
        } else {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 77);
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido
                Toast.makeText(this, "Permiso de ubicación CONCEDIDO", Toast.LENGTH_SHORT).show();

            } else {
                // Permiso denegado
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }




}