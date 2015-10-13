package com.soohoobook.retrofitdemo;

import android.app.Application;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Danny on 2015/10/6.
 */
public class MyApplication extends Application {

    //API Domain
    private static final String mStrAPIDomain = "http://papagotour.azurewebsites.net/webapi/";
    private static Retrofit retrofit;
    private static APIService apiService;

    @Override
    public void onCreate() {
        super.onCreate();


        initRetrofit();
        initAPIService();

    }

    private void initAPIService() {
        //初始化Retrofit interface
        apiService=retrofit.create(APIService.class);
    }

    private void initRetrofit(){
        //初始化 Retrofit
        retrofit=new Retrofit.Builder()
                .baseUrl(mStrAPIDomain) //API Domain Name
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit(){
        return retrofit;
    }

    public static APIService getApiService(){
        return apiService;
    }
}
