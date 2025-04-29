package com.liuli;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

// Step 1: Define the API interface
public interface ProductionApi {
    @Headers("Content-Type: application/json")
    @POST("/PQMES/a/smartBom/bom/submit")
    Call<Void> releaseProduction(@Body RequestData requestData);
}
