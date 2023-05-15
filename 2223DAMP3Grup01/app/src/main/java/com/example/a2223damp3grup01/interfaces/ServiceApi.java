package com.example.a2223damp3grup01.interfaces;

import com.example.a2223damp3grup01.objects.Benzinera;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("benzineres/benzinerasNoPreusNoReviews")
    Call<List<Benzinera>> listBenzineres();


    @GET("benzineres/benzFinder")
    Call<List<Benzinera>> listBenzineresFinder(
            @Query("KMredonda") double KMredonda,
            @Query("locationLAT") double locationLat,
            @Query("locationLONG") double locationLong);


}
