package com.example.zhoukao2_kaoshi.mvp.model;


import com.example.zhoukao2_kaoshi.inter.ICallBack;
import com.example.zhoukao2_kaoshi.retrofitutils.MyCallBack;
import com.example.zhoukao2_kaoshi.retrofitutils.RetrofitUrils;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class ShopModel implements ShopModelInterface {

    @Override
    public void getSearch(String url, String keyurl, final ICallBack callBack, final Type type) {
          String Url="commodity/v1/findCommodityByKeyword?page=1&count=10&keyword="+keyurl;
        RetrofitUrils.getInstance().getSearch(url, Url, new MyCallBack() {
            @Override
            public void onSuccesss(String result) {
                Gson gson = new Gson();
                Object o = gson.fromJson(result, type);
                if(callBack!=null){
                    //如果对象不为空  就将数据传回
                    callBack.onSuccessful(o);
                }
            }

            @Override
            public void onFailed(String e) {
                if(callBack!=null){
                    //如果对象不为空  就将数据传回
                    callBack.onFailedful(e);
                }
            }
        });
    }

    @Override
    public void getXiangqing(String url, int commoddityId, ICallBack callBack, Type type) {

    }
}

