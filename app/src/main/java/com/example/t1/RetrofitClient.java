package com.example.t1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    static Retrofit getClient()
    {
        String BaseUrl="http://172.16.191.196:3000/";//by default
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(BaseUrl)//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        retrofit=builder.build();
        return retrofit;
    }


}
