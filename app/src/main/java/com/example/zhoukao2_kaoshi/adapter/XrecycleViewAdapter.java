package com.example.zhoukao2_kaoshi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhoukao2_kaoshi.R;
import com.example.zhoukao2_kaoshi.entity.SearchEntity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class XrecycleViewAdapter extends XRecyclerView.Adapter<XrecycleViewAdapter.ViewHolder>{

    public interface OnCommodityClickListener {
        void onCommodityClick(int commodityId);
    }

    private OnCommodityClickListener commodityClickListener;

    public void setOnProductClickListener(OnCommodityClickListener listener) {
        this.commodityClickListener = listener;
    }

    private Context context;
    private List<SearchEntity.ResultBean> list;

    public XrecycleViewAdapter(Context context, List<SearchEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.item_search, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final SearchEntity.ResultBean resultBean = list.get(i);
        viewHolder.searchImg.setImageURI(resultBean.getMasterPic());
        viewHolder.searchName.setText(resultBean.getCommodityName());
        viewHolder.searchPrice.setText(resultBean.getPrice()+"");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId = resultBean.getCommodityId();
                if (commodityClickListener!=null){
                    commodityClickListener.onCommodityClick(commodityId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView searchImg;
        private final TextView searchName;
        private final TextView searchPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchImg = itemView.findViewById(R.id.search_img);
            searchName = itemView.findViewById(R.id.search_name);
            searchPrice = itemView.findViewById(R.id.search_price);
        }
    }
}
