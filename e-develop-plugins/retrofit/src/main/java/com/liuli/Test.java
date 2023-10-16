package com.liuli;

import retrofit2.Call;
import retrofit2.http.*;

public interface Test {



    //在方法里面可以写@HEADER，@BODY等 具体可学习influxdb-client-java的com.influxdb.client.service文件夹下的接口
    @Headers({
            "Content-Type:application/json;charset=utf-8",
            "Authorization:Basic NzdlN2E2MDFjYTJjMGE0YTpIZlZrbWx4VWZyQ2dHTUZlNVFkUjFYRGNGeGZoUzNCUTA1eFU4Umo0SmNC"
    })
    @GET("/api/v5/clients")
    Call<ClientResponseData> postWrite(

    );


//    @POST("api/v2/query")
//    @Streaming
//    Call<ResponseBody> postQueryResponseBody(@Header("Zap-Trace-Span") String var1, @Header("Accept-Encoding") String var2, @Header("Content-Type")
//    String var3, @Query("org") String var4, @Query("orgID") String var5, @Body com.influxdb.client.domain.Query var6);
}
