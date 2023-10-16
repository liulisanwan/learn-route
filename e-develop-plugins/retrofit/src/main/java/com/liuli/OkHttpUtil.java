package com.liuli;


import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Method;
import java.util.function.Function;

@Slf4j
public class OkHttpUtil {
    private String baseUrl="http://localhost:18083/";

    private final Retrofit myRetrofit;


    public OkHttpUtil() {

        this.myRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();;
    }



    public <T,R> R executeApi(Class<T> service,Class<R> resultClass, String methodName) {
        try {
            R result = executeApi(service, methodName, call -> {
                try {
                    Response<R> response = (Response<R>) call.execute();
                    if (response.isSuccessful()) {
                        return response.body();
                    }
                } catch (Exception e) {
                    // 记录异常信息或抛出异常
                    log.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
                return null;
            });
            return result;
        } catch (Exception e) {
            // 记录异常信息或抛出异常
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public <T, R> R executeApi(Class<T> service, String methodName, Function<Call<R>, R> apiCallHandler) {
        try {
            Method method = service.getMethod(methodName);
            if (method != null) {
                Call<R> call = (Call<R>) method.invoke(myRetrofit.create(service));
                return apiCallHandler.apply(call);
            }
        } catch (Exception e) {
            // 记录异常信息或抛出异常
            // log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return null;
    }
}