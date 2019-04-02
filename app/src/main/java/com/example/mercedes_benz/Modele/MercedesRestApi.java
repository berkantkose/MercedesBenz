package com.example.mercedes_benz.Modele;


import retrofit2.Call;
import retrofit2.http.GET;

public interface MercedesRestApi {

    @GET("mercedes")
    Call<RestMercedesResponse> getListMercedes();

    @GET("abilities")
    Call<RestMercedesResponse> getListAbilities();

}

