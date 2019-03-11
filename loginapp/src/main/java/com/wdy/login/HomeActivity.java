package com.wdy.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kernal.plateid.CreateActivity;

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
        mRoad=findViewById(R.id.btn_onroad);
        mOut=findViewById(R.id.btn_out);
        mData=findViewById(R.id.btn_data);
        mFee=findViewById(R.id.btn_fee);

        mOut.setOnClickListener(this);
        mFee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_out){
            Intent intent=new Intent(HomeActivity.this, CreateActivity.class);
            startActivity(intent);
            finish();
        }else if(v.getId()==R.id.btn_fee){
            SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putString("name","");
            editor.putString("phone","");
            editor.putString("userName","");
            editor.putInt("corporation",0);
            editor.apply();
            finish();
        }
    }
}

