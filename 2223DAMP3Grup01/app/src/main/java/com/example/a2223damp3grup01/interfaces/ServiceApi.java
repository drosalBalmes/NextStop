package com.example.a2223damp3grup01.interfaces;

import com.example.a2223damp3grup01.objects.Benzinera;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {
    @GET("benzineres/benzinerasNoPreusNoReviews")
    Call<List<Benzinera>> listBenzineres();
}
