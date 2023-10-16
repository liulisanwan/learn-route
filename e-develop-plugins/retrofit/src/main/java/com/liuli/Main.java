package com.liuli;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //构建一个Retrofit对象 url为请求的服务器地址
        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl("http://localhost:18083/")
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建一个请求接口的实例
        Test test = retrofit.create(Test.class);
        //对发送请求进行封装
        Call<ClientResponseData> dataCall = test.postWrite();
        //使用execute方法发送请求并返回Response对象
        Response<ClientResponseData> response = dataCall.execute();

        dataCall.enqueue(new retrofit2.Callback<ClientResponseData>() {
            @Override
            public void onResponse(Call<ClientResponseData> call, Response<ClientResponseData> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<ClientResponseData> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
        //判断请求是否成功
        System.out.println(response.body());
        //输出响应内容
        System.err.println(response.code());

        System.err.println(response.message());
    }
}