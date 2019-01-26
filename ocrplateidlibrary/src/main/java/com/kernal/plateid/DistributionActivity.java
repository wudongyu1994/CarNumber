package com.kernal.plateid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kernal.plateid.interfacee.ListItemClickListener;
import com.kernal.plateid.my.Good;
import com.kernal.plateid.my.MyData;

import java.util.ArrayList;

public class DistributionActivity extends AppCompatActivity implements ListItemClickListener {
    private final static String TAG=DistributionActivity.class.getSimpleName();
    private ArrayList<Good> goodsOut;
    private Button mFinish;
    private RecyclerView mRecyclerView;
    private GoodDisAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distribution_form);
        getAllView();
        goodsOut=MyData.getGoodsOut();

        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new GoodDisAdapter(goodsOut,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getAllView(){
        mRecyclerView=(RecyclerView)findViewById(R.id.rv_goods_distributed);
        mFinish=(Button)findViewById(R.id.btn_finish);
        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAdapter.getItemCount()>0){
                    Toast.makeText(DistributionActivity.this,"请将所有货物出库后再完成出库单",Toast.LENGTH_SHORT).show();
                }else{
                    finish();
                }
            }
        });
    }

    @Override
    public void onListItemClick(final int position) {
        String name=mAdapter.getName(position);
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(DistributionActivity.this);
        builder.setTitle("货物出库");
        builder.setMessage("你确定"+ name +"已经出库？\n确定后将删除该货物");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo use api to add detail
                        mAdapter.remove(position);
//                        goodsOut.remove(position);
//                        mAdapter.changeGoods(goodsOut);
//                        mAdapter.notifyDataSetChanged();
                    }
                });
        builder.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        builder.show();
    }
}