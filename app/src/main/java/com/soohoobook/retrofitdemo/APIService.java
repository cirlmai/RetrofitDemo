package com.soohoobook.retrofitdemo;

import com.soohoobook.retrofitdemo.Receivers.RouteReceiver;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Danny on 2015/10/6.
 */
public interface APIService {
    @POST("Get_SectionList")
    Call<ArrayList<RouteReceiver>> routeList(@Query("CityId") String city_id );

    @POST("Get_SectionList")
    Call<ArrayList<RouteReceiver>> routeList();
}
