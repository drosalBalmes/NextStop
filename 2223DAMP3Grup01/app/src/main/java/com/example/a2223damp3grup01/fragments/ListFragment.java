package com.example.a2223damp3grup01.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.BenzineresAdapter;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.interfaces.ServiceApi;
import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.FitRetro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    ServiceApi serviceApi;
    List<Benzinera> benzinerasList = new ArrayList<>();
    View view;
    RecyclerView benzineresRecycler;
    BenzineresAdapter benzineresAdapter;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        view = inflater.inflate(R.layout.fragment_list,container,false);
        init();
        return view;
    }

    public void init(){
        serviceApi = FitRetro.getServiceApi();
        benzineresRecycler = view.findViewById(R.id.listGasolinerasRecycler);
        getBenzineres();
    }

    public void initRecyclerBenzineres(){
        benzineresAdapter = new BenzineresAdapter(benzinerasList);
        Log.d("lolol",benzinerasList.get(0).getNom());
        benzineresRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        benzineresRecycler.setHasFixedSize(true);
        benzineresRecycler.setAdapter(benzineresAdapter);
    }
    public void getBenzineres(){

        Call<List<Benzinera>> call = serviceApi.listBenzineres();

        call.enqueue(new Callback<List<Benzinera>>() {
            @Override
            public void onResponse(Call<List<Benzinera>> call, retrofit2.Response<List<Benzinera>> response) {
                benzinerasList = response.body();
                Log.d("getingbenzineres","aquiva1");
                try {
                    if (benzinerasList != null) {
                        Log.d("getingbenzineres", benzinerasList.get(0).getNom());
                        Log.d("getingbenzineres", String.valueOf(benzinerasList.size()));
                        initRecyclerBenzineres();

                    }
                }catch (Exception e){
                    Log.d("getingbenzineres",e.toString());
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
}