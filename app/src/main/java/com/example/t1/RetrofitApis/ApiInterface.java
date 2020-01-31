package com.example.t1.RetrofitApis;

import com.example.t1.RetrofitRegisPage.Getregisformat;
import com.example.t1.RetrofitLoginpage.getlgformat;
import com.example.t1.RetrofitLoginpage.sendlgformat;
import com.example.t1.RetrofitRegisPage.Sendregisformat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    //login credentials
    @POST("api/user/login")
    Call<getlgformat> getlogindata(@Body sendlgformat sendlgformat);

    @POST("api/user/register")
    Call<Getregisformat> getregdata(@Body Sendregisformat sendregisformat);



}
