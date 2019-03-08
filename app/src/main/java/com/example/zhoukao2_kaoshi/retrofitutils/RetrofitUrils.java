package com.example.zhoukao2_kaoshi.retrofitutils;

import android.os.Handler;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUrils {
    private OkHttpClient client;
    private Retrofit.Builder builder;
    private static  RetrofitUrils  instance;
    private Handler handler=new Handler();

    private  RetrofitUrils(){
        client=new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();
        builder=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client);
    }

    //双重锁
    public  static  RetrofitUrils getInstance(){
        if (instance==null){
            synchronized (RetrofitUrils.class){
                if (instance==null){
                    instance=new RetrofitUrils();
                }
            }
        }
        return instance;
    }
    //创建Retrofit对象
    private MyInterface  getRetrofitIViewObject(String url) {
        Retrofit retrofit = builder.baseUrl(url)
                .build();
        MyInterface myInterface = retrofit.create(MyInterface.class);
        return myInterface;
    }
    public RetrofitUrils getSearch(String url, String keyurl, final MyCallBack myCallBack) {
        //得到retrofit对象
        MyInterface retrofitIViewObject = getRetrofitIViewObject(url);
        retrofitIViewObject.doget(keyurl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(myCallBack));
        return instance;


    }

    public Observer getObserver(final MyCallBack myCallBack) {
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (myCallBack!=null){
                    myCallBack.onFailed(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if(myCallBack!=null){
                    try {
                        String result = responseBody.string();
                        if(!result.equals("")){
                            myCallBack.onSuccesss(result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if(myCallBack!=null){
                            myCallBack.onFailed(e.getMessage());
                        }
                    }
                }
            }
        };
        return observer;

    }

}
