package com.example.zhoukao2_kaoshi.retrofitutils;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface MyInterface {
    @GET
    Observable<ResponseBody> doget(@Url String url);
}
