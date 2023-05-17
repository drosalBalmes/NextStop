package com.example.a2223damp3grup01.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.FitRetro;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostReviewDialogFragment extends DialogFragment {

    View view;
    TextView TVnom;
    EditText ETcomentari;
    RatingBar ratingBar;
    int puntuacio;
    String comentari;
    String nom;
    int idGaso;
    int idPunt;
    Button post;
    Bundle bundle;
    ServiceApi serviceApi;
    private PostReviewDialogListener dialogListener;

    @SuppressLint("MissingInflatedId")
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Crear una instancia del AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Obtener la vista del diálogo
        //TODO get Id por putExtra
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_post_review_dialog, null);

        bundle = getArguments();
        if (bundle.containsKey("idGaso")){
            idGaso = bundle.getInt("idGaso",0);
            Log.d("postReview","idGaso: " +idGaso);
        } else if (bundle.containsKey("idPunt")) {
            idPunt = bundle.getInt("idPunt",0);
            Log.d("postReview","idPunt: " +idPunt);
        }
        if (bundle.containsKey("nom")){
            nom = bundle.getString("nom","");
        }
        init();
        clickListener();
        builder.setView(view);
        return builder.create();
    }

    public void init(){
        serviceApi = FitRetro.getServiceApi();
        TVnom = view.findViewById(R.id.nomTxt);
        ETcomentari = view.findViewById(R.id.ETcomentari);
        post = view.findViewById(R.id.post);
        ratingBar = view.findViewById(R.id.ratingBarPost);
        TVnom.setText(nom);
    }

    public void clickListener(){
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle.containsKey("idGaso")) {
                    postReview(Math.toIntExact(idGaso),ETcomentari.getText().toString(), (int) ratingBar.getRating(),1,1);
                } else if (bundle.containsKey("idPunt")) {
                    postReview(Math.toIntExact(idPunt),ETcomentari.getText().toString(), (int) ratingBar.getRating(),1,2);
                    Log.d("postReview","id_Punt: " + Math.toIntExact(idPunt) + "Commentari:" + ETcomentari.getText().toString() + "rating: " + (int) ratingBar.getRating() + "id_user: " + 1);
                    //postReview(1,"comment",3,1);
                }
                dismiss();
            }
        });
    }

    public void postReview(int benzinera_id, String comentari, int puntuacio, int user_id,int type) {
        Call<Void> call = null;
        if (type==1){
            call = serviceApi.postReviewBenz(benzinera_id,comentari,puntuacio,user_id);
        } else if (type == 2) {
            call = serviceApi.postReviewPunt(benzinera_id,comentari,puntuacio,user_id);

        }

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    dialogListener.onReviewPosted();
                    //Toast.makeText(requireContext().getApplicationContext(), "Ressenya publicada amb èxit", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("postReview","Respuesta no exitosa: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("postReview", t.getMessage());
                if (t instanceof IOException) {
                    // Error de red o servidor
                    Log.e("postReview", "Error de red o servidor");
                } else {
                    // Otro tipo de error
                    Log.e("postReview", "Otro tipo de error");
                }
            }
        });
    }

    public interface PostReviewDialogListener {
        void onReviewPosted();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListener = (PostReviewDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement PostReviewDialogListener");
        }
    }
}