package com.example.a2223damp3grup01.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2223damp3grup01.R;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    Button signup;
    EditText userName,password,email;
    String usernameS;
    String passwordS;
    String emailS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
    }

    public void init(){
        signup = (Button) findViewById(R.id.SignupSubmit);
        userName = (EditText) findViewById(R.id.usernameSignup);
        password=(EditText) findViewById(R.id.inputContraSignup);
        email =(EditText) findViewById(R.id.inputemailSignup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });
    }

    public void checkFields(){


        signUpMethod();
    }

    public void signUpMethod(){
        RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);

        String url="http://10.0.2.2:8080/register/registration";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("success")){
                    Toast.makeText(SignupActivity.this,"succesful", Toast.LENGTH_SHORT).show();
                    email.setText(null);
                    password.setText(null);
                    userName.setText(null);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(SignupActivity.this,"Registration UN-succesful", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());
                params.put("username",userName.getText().toString());



                return params;
            }
        };

        queue.add(stringRequest);
    }
}