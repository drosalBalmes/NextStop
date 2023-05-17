package com.example.a2223damp3grup01.interfaces;

import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.example.a2223damp3grup01.objects.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("benzineres/benzinerasNoPreusNoReviews")
    Call<List<Benzinera>> listBenzineres();


    @GET("benzineres/benzFinder")
    Call<List<Benzinera>> listBenzineresFinder(
            @Query("locationLAT") double locationLat,
            @Query("locationLONG") double locationLong,
            @Query("KMredonda") double KMredonda,
            @Query("typeGAS") String typeGas);


    @GET("benzineres/closest")
    Call<List<Benzinera>> listBenzClosest(
            @Query("locationLONG") double locationLNG,
            @Query("locationLAT")double locationLAT,
            @Query("num")int num,
            @Query("typeGAS") String typeGAS
    );

    @GET("/puntsRecarrega/closest")
    Call<List<PuntRecarrega>> listPuntsClosest(
            @Query("locationLONG") double locationLNG,
            @Query("locationLAT")double locationLAT,
            @Query("num")int num,
            @Query("conType") String type
    );

    @GET("puntsRecarrega/puntsRecarregaFinder")
    Call<List<PuntRecarrega>> listPuntsFinder(
            @Query("locationLAT") double locationLat,
            @Query("locationLONG") double locationLong,
            @Query("KMredonda") double KMredonda,
            @Query("conType") String conType
    );

    @GET("valoracio/valoracionsByBenzinera/idBenzinera")
    Call<List<Review>> listReviewsByBenzId(
            @Query("id") int id
    );



}
