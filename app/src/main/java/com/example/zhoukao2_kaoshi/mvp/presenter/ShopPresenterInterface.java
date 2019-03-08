package com.example.zhoukao2_kaoshi.mvp.presenter;


public interface ShopPresenterInterface {
    //doGet方法  用户搜索
    void doSearch(String keyurl);

    //doGet方法 商品详情
    void doXiangqing(int commodityId);
}
