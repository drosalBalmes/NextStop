package com.example.a2223damp3grup01.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2223damp3grup01.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    Button login,signup,guest;

    EditText userNameET,passwordET;
    boolean sesionIniciada=false;
    String userNameS,passwordS;
    int id;
    CheckBox checkBoxShow;
    FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        init();
    }

    public void init(){

        signup = (Button) findViewById(R.id.btnSignup);
        login = (Button) findViewById(R.id.btnIniciar);
        guest = (Button) findViewById(R.id.guest);

        userNameET = (EditText) findViewById(R.id.inputNom);
        passwordET = (EditText) findViewById(R.id.inputContra);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(a);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(a);
            }
        });
    }

    public void logIn(){
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

        String url="http://10.0.2.2:8080/login";

        HashMap<String,String> params = new HashMap<String,String>();
        params.put("email",userNameET.getText().toString());
        params.put("password",passwordET.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            id = (Integer) response.get("id");


                            Log.d("Login","obtiene todo");



                            Log.d("Login","valor del idd = " + String.valueOf(id));
                            sesionIniciada=true;
                            Intent a = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(a);


                        }catch (JSONException e){
                            Log.d("Login","fallo1");
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                            Toast.makeText(getApplicationContext(),"LOGIN FAILED",Toast.LENGTH_SHORT).show();
                            sesionIniciada=false;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Log.d("Login","fallo2");
                Toast.makeText(getApplicationContext(),"LOGIN FAILED",Toast.LENGTH_SHORT).show();
                sesionIniciada=false;
            }
        });
        queue.add(jsonObjectRequest);

    }


}