package com.example.a2223damp3grup01.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
import java.util.List;
import java.util.Map;

public class PostReviewDialogFragment extends DialogFragment {

    View view;
    EditText ETcomentari;
    RatingBar ratingBar;
    int puntuacio;
    String comentari;
    Button post;

    @SuppressLint("MissingInflatedId")
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Crear una instancia del AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Obtener la vista del diálogo
        //TODO get Id por putExtra
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_post_review_dialog, null);


        init();
        clickListener();
        builder.setView(view);
        return builder.create();
    }

    public void init(){
        ETcomentari = view.findViewById(R.id.ETcomentari);
        post = view.findViewById(R.id.post);
        ratingBar = view.findViewById(R.id.ratingBarPost);
        //puntuacio = (int) ratingBar.getRating();
        //FATAL EXCEPTION
        comentari = "";
    }

    public void clickListener(){
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postReview();
                dismiss();
            }
        });
    }

    public void postReview(){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url="http://10.0.2.2:8080/valoracio/newValoracio";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("success")) {
                    ETcomentari.setText(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("postReview","Error: " + error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("puntuacio", String.valueOf((int) ratingBar.getRating()));
                params.put("comentari",ETcomentari.getText().toString());

                return params;
            }
        };

        queue.add(stringRequest);
    }
}