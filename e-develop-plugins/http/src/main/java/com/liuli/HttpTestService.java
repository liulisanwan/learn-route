package com.liuli;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * http测试服务
 *
 * @author zhanghui
 * @date 2024/10/10
 */
@Service
public class HttpTestService {
    public Map<Boolean, String> releaseProduction2WithHutool( ) {
        // 构建 JSON 数据
        String json = "{\n" +
                "  \"bomType\": 1,\n" +
                "  \"data\": {\n" +
                "      \"hub\": 1,\n" +
                "      \"brand\": 2,\n" +
                "      \"tire\": 1\n" +
                "    }\n" +
                "}";

        // 发起 POST 请求
        HttpResponse response = HttpRequest.post("http://localhost:8981/PQSmart/a/newOptional/test")
                .body(json) // 设置请求体
                .timeout(20000) // 设置超时（毫秒）
                .execute();

        // 处理响应结果
        Map<Boolean, String> result = new HashMap<>();
        if (response.isOk()) {
            // 请求成功
            result.put(true, "Request was successful: " + response.getStatus());
            System.out.println("Request was successful: " + response.body());
        } else {
            // 请求失败
            result.put(false, "Request failed: " + response.getStatus());
            System.out.println("Request failed with code: " + response.getStatus());
            System.out.println("Response body: " + response.body());
        }

        return result;
    }

    private final OkHttpClient client = new OkHttpClient();

    public Map<Boolean, String> releaseProduction2WithOkHttp3( ) throws IOException {
        // 构建 JSON 数据
        String json = "{\n" +
                "  \"bomType\": 1,\n" +
                "  \"data\": {\n" +
                "      \"hub\": 1,\n" +
                "      \"brand\": 2,\n" +
                "      \"tire\": 1\n" +
                "    }\n" +
                "}";

        // 创建 RequestBody
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));

        // 创建请求对象
        Request request = new Request.Builder()
                .url("http://localhost:8981/PQSmart/a/newOptional/test")
                .post(body) // 设置 POST 请求
                .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            Map<Boolean, String> result = new HashMap<>();
            if (response.isSuccessful()) {
                // 请求成功
                result.put(true, "Request was successful: " + response.code());
                System.out.println("Request was successful: " + response.body().string());
            } else {
                // 请求失败
                result.put(false, "Request failed: " + response.code());
                System.out.println("Request failed with code: " + response.code());
                System.out.println("Response body: " + response.body().string());
            }
            return result;
        }
    }



}
