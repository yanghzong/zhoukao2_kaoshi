package com.example.zhoukao2_kaoshi.mvp.presenter;

import com.example.zhoukao2_kaoshi.entity.SearchEntity;
import com.example.zhoukao2_kaoshi.inter.ApiUrl;
import com.example.zhoukao2_kaoshi.inter.ICallBack;
import com.example.zhoukao2_kaoshi.mvp.model.ShopModel;
import com.example.zhoukao2_kaoshi.mvp.view.ShopView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ShopPresenter  implements ShopPresenterInterface{

    private ShopModel shopModel;
    private ShopView shopView;
    public void attach(ShopView shopView){
        this.shopView=shopView;
        shopModel=new ShopModel();
    }

    public  void detach(){
        if (shopModel!=null){
            shopModel=null;
        }
        if (shopView!=null){
            shopModel=null;
        }
    }



    @Override
    public void doSearch(String keyurl) {
        Type type=new TypeToken<SearchEntity>(){}.getType();
        shopModel.getSearch(ApiUrl.BaseUrl, keyurl, new ICallBack() {
            @Override
            public void onSuccessful(Object obj) {
                    shopView.successful(obj);
            }

            @Override
            public void onFailedful(String e) {
                   shopView.failure(e);
            }
        },type);
    }

    @Override
    public void doXiangqing(int commodityId) {

    }
}
