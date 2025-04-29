package com.liuli;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class HttpTestService2 {
    @Autowired
    RetrofitClient retrofitClient;

    public Map<Boolean, String> releaseProduction2WithRetrofit() throws IOException {
        RequestData requestData = new RequestData();
        requestData.setBomType("2");


        // 构建 JSON 数据
        String json = "{\n" +
                "  \"bomType\": 1,\n" +
                "  \"data\": {\n" +
                "      \"hub\": 1,\n" +
                "      \"brand\": 2,\n" +
                "      \"tire\": 1\n" +
                "    }\n" +
                "}";
        // Step 4: Create a Retrofit instance
        Retrofit retrofit = retrofitClient.getRetrofitInstance();

        // Step 5: Create an API instance
        ProductionApi productionApi = retrofit.create(ProductionApi.class);

        // Step 6: Create request data
//        RequestData requestData = JSON.parseObject(json, RequestData.class);

        // Step 7: Send the request synchronously
        Call<Void> call = productionApi.releaseProduction(requestData);
        Response<Void> response = call.execute();

        // Step 8: Handle the response
        Map<Boolean, String> result = new HashMap<>();
        if (response.isSuccessful()) {
            result.put(true, "Request was successful: " + response.code());
            System.out.println("Request was successful: " + response.code());
        } else {
            result.put(false, "Request failed: " + response.code());
            System.out.println("Request failed with code: " + response.code());
        }

        return result;
    }
}
