package com.wdy.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kernal.plateid.CreateActivity;
import com.wdy.basicinfo.BasicActivity;
import com.wdy.list.Basic2Activity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG= LoginActivity.class.getSimpleName();
    Button mOut,mIn,mFee,mInfo,mRoad,mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String userName=sharedPreferences.getString("userName","");

        getAllView();


    }

    private void getAllView(){
        mInfo=findViewById(R.id.btn_basic_info);
        mIn=findViewById(R.id.btn_in);
//        mRoad=findViewById(R.id.btn_onroad);
        mOut=findViewById(R.id.btn_out);
//        mData=findViewById(R.id.btn_data);
        mFee=findViewById(R.id.btn_fee);

        mInfo.setOnClickListener(this);
        mOut.setOnClickListener(this);
        mFee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_out){
            Intent intent=new Intent(HomeActivity.this, CreateActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btn_fee){
            Intent intent=new Intent(HomeActivity.this, Basic2Activity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btn_basic_info){
            Intent intent=new Intent(HomeActivity.this, BasicActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btn_in){

        }
    }
}

