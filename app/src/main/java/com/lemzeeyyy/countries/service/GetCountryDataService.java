package com.lemzeeyyy.countries.service;

import com.lemzeeyyy.countries.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {
    //Retrofit Interface
    @GET("countries")
    Call<Result> getResult();

}
