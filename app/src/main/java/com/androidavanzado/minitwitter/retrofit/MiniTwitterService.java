package com.androidavanzado.minitwitter.retrofit;

import com.androidavanzado.minitwitter.RequestLogin;
import com.androidavanzado.minitwitter.RequestSignUp;
import com.androidavanzado.minitwitter.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MiniTwitterService {
    @POST("/auth/login")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

    @POST("/auth/signup")
    Call<ResponseAuth> doSigUp(@Body RequestSignUp requestSignUp);
}
