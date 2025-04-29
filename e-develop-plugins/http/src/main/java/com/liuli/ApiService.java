package com.liuli;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * api服务
 *
 * @author zhanghui
 * @date 2024/10/09
 */
public interface ApiService {
    @POST("/PQMES/a/smartBom/bom/submit")
    Call<Void> submitBom(@Body RequestData requestData);



    @POST("/PQSmart/a/newOptional/test")
    Call<Void> submitBom2(@Body RequestData requestData);


    @Headers("Content-Type: application/json")
    @POST("a/newOptional/test")
    Call<Void> releaseProduction(@Body RequestData requestData);
}
