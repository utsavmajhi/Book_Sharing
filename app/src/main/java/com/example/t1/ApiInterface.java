package com.example.t1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    //login credentials
    @POST("api/user/login")
    Call<getlgformat> getlogindata(@Body sendlgformat sendlgformat);



}
