package com.example.t1.RetrofitApis;

import com.example.t1.getlgformat;
import com.example.t1.sendlgformat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    //login credentials
    @POST("api/user/login")
    Call<getlgformat> getlogindata(@Body sendlgformat sendlgformat);



}
