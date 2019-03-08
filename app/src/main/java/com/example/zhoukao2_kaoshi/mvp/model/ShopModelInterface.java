package com.example.zhoukao2_kaoshi.mvp.model;

import com.example.zhoukao2_kaoshi.inter.ICallBack;

import java.lang.reflect.Type;

public interface ShopModelInterface  {
    void  getSearch(String url, String keyurl ,ICallBack callBack, Type type);
    void  getXiangqing(String  url,int  commoddityId,ICallBack callBack,Type type);
}
