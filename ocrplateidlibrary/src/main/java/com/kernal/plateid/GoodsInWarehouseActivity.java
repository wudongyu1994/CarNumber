package com.kernal.plateid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.kernal.plateid.adapter.GoodAdapter;
import com.kernal.plateid.interfacee.ListItemClickListener;
import com.kernal.plateid.my.Good;
import com.kernal.plateid.my.MyData;

import java.util.ArrayList;

public class GoodsInWarehouseActivity extends AppCompatActivity implements ListItemClickListener {
    private final static String TAG=GoodsInWarehouseActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private GoodAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Good> goods;
    private Button mOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_in_store);
        getAllView();
        goods=MyData.getGoodsInStore();

        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new GoodAdapter(goods,this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void getAllView(){
        mRecyclerView=(RecyclerView)findViewById(R.id.rv_goods_in_store);
        mOk=(Button)findViewById(R.id.btn_ok);

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Good> goodsOut=new ArrayList<>();
                boolean[] mIsChecked=mAdapter.getChecked();
                for(int i=0;i<mAdapter.getItemCount();i++){
                    if(mIsChecked[i]){
                        goodsOut.add(mAdapter.getmGoods().get(i));
                    }
                }
                MyData.setGoodsOut(goodsOut);
                Intent intent=new Intent(GoodsInWarehouseActivity.this,DistributionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onListItemClick(int position) {

    }
}
