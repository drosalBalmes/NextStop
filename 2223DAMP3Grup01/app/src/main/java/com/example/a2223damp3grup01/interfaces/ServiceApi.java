package com.example.a2223damp3grup01.interfaces;

import com.example.a2223damp3grup01.objects.Benzinera;
import com.example.a2223damp3grup01.objects.Preu;
import com.example.a2223damp3grup01.objects.PuntRecarrega;
import com.example.a2223damp3grup01.objects.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("benzineres/benzinerasNoPreusNoReviews")
    Call<List<Benzinera>> listBenzineres();


    @GET("benzineres/benzFinder")
    Call<List<Benzinera>> listBenzineresFinder(
            @Query("locationLAT") double locationLat,
            @Query("locationLONG") double locationLong,
            @Query("KMredonda") double KMredonda,
            @Query("typeGAS") String typeGas
    );

    @GET("benzineres/benzFinder/val")
    Call<List<Benzinera>> listBenzineresFinderVal(
            @Query("locationLAT") double locationLat,
            @Query("locationLONG") double locationLong,
            @Query("KMredonda") double KMredonda,
            @Query("typeGAS") String typeGas
    );

    @GET("benzineres/benzFinder/val/price")
    Call<List<Benzinera>> listBenzineresFinderValPrice(
            @Query("locationLAT") double locationLat,
            @Query("locationLONG") double locationLong,
            @Query("KMredonda") double KMredonda,
            @Query("typeGAS") String typeGas
    );

    @GET("benzineres/closest")
    Call<List<Benzinera>> listBenzClosest(
            @Query("locationLONG") double locationLNG,
            @Query("locationLAT")double locationLAT,
            @Query("num")int num,
            @Query("typeGAS") String typeGAS
    );

    @GET("benzineres/closest/val/price")
    Call<List<Benzinera>> listBenzClosestValPrice(
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

    @GET("puntsRecarrega/puntsRecarregaFinder/val")
    Call<List<PuntRecarrega>> listPuntsFinderVal(
            @Query("locationLAT") double locationLat,
            @Query("locationLONG") double locationLong,
            @Query("KMredonda") double KMredonda,
            @Query("conType") String conType
    );

    @GET("/preus/lastPriceBenzId")
    Call<List<Preu>> listPreusByBenz(
            @Query("benzID") long benzID
    );

    @GET("/preus/preusByBenzId")
    Call<Preu> PreuByBenz(
            @Query("benzID") long benzID
    );





    @GET("valoracio/valoracionsByBenzinera/idBenzinera/{id}")
    Call<List<Review>> listReviewsByBenzId(
            @Path("id") int id
    );

    @GET("valoracioElec/valoracionsByPuntRecarrega/idPuntRecarrega/{id}")
    Call<List<Review>> listReviewsByPuntId(
            @Path("id") int id
    );

    @GET("valoracio/valoracionsByUser/idUser/{id}")
    Call<List<Review>> listReviewsByUserId(
            @Path("id") int id
    );



    @POST("valoracio/newValoracio")
    Call<Void> postReviewBenz(
            @Query("benzinera_id") int benzineraId,
            @Query("comentari") String comentari,
            @Query("puntuacio") int puntuacio,
            @Query("user_id") int userId
    );

    @POST("valoracioElec/newValoracio")
    Call<Void> postReviewPunt(
            @Query("puntRecarrega_id") int puntRecarregaId,
            @Query("comentari") String comentari,
            @Query("puntuacio") int puntuacio,
            @Query("user_id") int userId
    );
}
