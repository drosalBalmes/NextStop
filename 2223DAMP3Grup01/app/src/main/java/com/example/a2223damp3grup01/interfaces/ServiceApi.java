package com.example.a2223damp3grup01.interfaces;

import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.PuntRecarrega;

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
            @Query("locationLONG") double locationLong,
            @Query("typeGAS") String typeGas);


    @GET("benzineres/closest")
    Call<List<Benzinera>> listBenzClosest(
            @Query("locationLONG") double locationLNG,
            @Query("locationLAT")double locationLAT,
            @Query("num")int num
    );

    @GET("/puntsRecarrega/closest")
    Call<List<PuntRecarrega>> listPuntsClosest(
            @Query("locationLONG") double locationLNG,
            @Query("locationLAT")double locationLAT,
            @Query("num")int num
    );

    @GET("puntsRecarrega/puntsRecarregaFinder")
    Call<List<PuntRecarrega>> listPuntsFinder(
            @Query("KMredonda") double KMredonda,
            @Query("locationLAT") double locationLat,
            @Query("locationLONG") double locationLong,
            @Query("conType") String conType
    );


}
