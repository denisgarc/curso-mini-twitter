package com.androidavanzado.minitwitter.retrofit;

import com.androidavanzado.minitwitter.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthTwitterClient {
    private static AuthTwitterClient instance = null;
    private AuthTwitterService authTwitterService;
    private Retrofit retrofit;

    public AuthTwitterClient() {

        // Incluir en la cabecera de la peticion el token de autorizacion
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addNetworkInterceptor(new AuthInterceptor());
        OkHttpClient client = okHttpClientBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        authTwitterService = retrofit.create(AuthTwitterService.class);
    }

    // Patrón Sigleton
    public static AuthTwitterClient getInstance() {
        if(instance == null){
            instance = new AuthTwitterClient();
        }
        return instance;
    }

    public AuthTwitterService getAuthTwitterService() {
        return authTwitterService;
    }

}
