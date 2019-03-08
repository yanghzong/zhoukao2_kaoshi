package com.example.zhoukao2_kaoshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoukao2_kaoshi.adapter.XrecycleViewAdapter;
import com.example.zhoukao2_kaoshi.entity.SearchEntity;
import com.example.zhoukao2_kaoshi.mvp.presenter.ShopPresenter;
import com.example.zhoukao2_kaoshi.mvp.view.ShopView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ShopView {


    @BindView(R.id.search_left)
    ImageView searchLeft;
    @BindView(R.id.search_right)
    TextView searchRight;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.xrv_search)
    XRecyclerView xrvSearch;
    private Unbinder bind;
    private List<SearchEntity.ResultBean> searchlist;
    private ShopPresenter shopPresenter;
    private String keyurl = "板鞋";

    private XrecycleViewAdapter xrecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bind = ButterKnife.bind(this);
        //初始化p层
        initPresenter();
        //初始化adapter
        initAdapter();
        //初始化list
        initList();

    }

    private void initList() {
        searchlist = new ArrayList<>();
    }

    private void initAdapter() {
        xrecycleViewAdapter = new XrecycleViewAdapter(this, searchlist);
        xrvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        xrvSearch.setAdapter(xrecycleViewAdapter);
        xrecycleViewAdapter.setOnProductClickListener(new XrecycleViewAdapter.OnCommodityClickListener() {
            @Override
            public void onCommodityClick(int commodityId) {
                EventBus.getDefault().postSticky(commodityId + "");
                Intent intent = new Intent(MainActivity.this, XiangqingActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initPresenter() {
        shopPresenter = new ShopPresenter();
        shopPresenter.attach(this);
        shopPresenter.doSearch(keyurl);
    }


    @Override
    public void successful(Object obj) {
        SearchEntity searchEntity = (SearchEntity) obj;
        List<SearchEntity.ResultBean> result = searchEntity.getResult();
        if (searchEntity != null) {
            searchlist.clear();
            searchlist.addAll(result);

        }
        xrecycleViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void failure(String e) {

    }

    @OnClick(R.id.search_right)
    public void onViewClicked() {
        String s = search.getText().toString();
        if (s.length()>0) {
          keyurl=s;
          shopPresenter.doSearch(keyurl);
        }else {
            Toast.makeText(this,"请输入您想要查找的东西",Toast.LENGTH_SHORT).show();
        }
    }
}
