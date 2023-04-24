package com.example.a2223damp3grup01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a2223damp3grup01.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    Button btnLista;
    Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btnLista = findViewById(R.id.btnList);
        btnMap = findViewById(R.id.btnMapa);
        replaceFragment(new Fragment1());
        cambiarFragment(btnLista,new FragmentArriba());
        cambiarFragment(btnMap,new MapsFragment());
        bottomNavSelected();

    }
    public void bottomNavSelected(){
        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.page_1:
                    replaceFragment(new Fragment1());
                    break;
                    case R.id.page_2:
                        replaceFragment(new Fragment2());
                        break;
            }
            return  true;
        });
    }

    public void cambiarFragment(Button button,Fragment fragment){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(fragment);
            }
        });
    }

    private void replaceFragment(Fragment frahment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment,frahment);
        fragmentTransaction.commit();

    }
}