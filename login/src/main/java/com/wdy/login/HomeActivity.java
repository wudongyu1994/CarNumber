package com.wdy.login;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.android.buildinstorageform.InStorage_Activity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kernal.plateid.CreateActivity;
import com.kernal.plateid.MemoryCameraActivity;
import com.kernal.plateid.utills.CheckPermission;
import com.kernal.plateid.utills.PermissionActivity;
import com.wdy.basicinfo.BasicActivity;
import com.wdy.basicinfo.CarInfoActivity;
import com.wdy.basicinfo.FastJsonReturn;
import com.wdy.basicinfo.MySingleton;
import com.wdy.basicinfo.Truck;
import com.wdy.basicinfo.TruckAdapter;
import com.wdy.list.Basic2Activity;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG= LoginActivity.class.getSimpleName();
    Button mOut,mIn,mFee,mInfo,mLogout;
    TextView mName,mPhone,mUserName,mCor;


    static final String[] PERMISSION = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE, // 读取权限
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.VIBRATE, Manifest.permission.INTERNET,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
//        String name=sharedPreferences.getString("name","");
        String phone=sharedPreferences.getString("phone","");
        String userName=sharedPreferences.getString("userName","");
        int cor=sharedPreferences.getInt("corporation",0);

        getAllView();

//        mName.setText("姓名："+name);
        mPhone.setText("手机号："+phone);
        mUserName.setText("用户名："+userName);
        mCor.setText("所属承运方："+cor);
    }
    private void getAllView(){
        mInfo=findViewById(R.id.btn_basic_info);
        mIn=findViewById(R.id.btn_in);
        mOut=findViewById(R.id.btn_out);
        mFee=findViewById(R.id.btn_fee);
        mLogout=findViewById(R.id.button3);
//        mName=findViewById(R.id.textView143);
        mPhone=findViewById(R.id.textView144);
        mUserName=findViewById(R.id.textView145);
        mCor=findViewById(R.id.textView146);

        mInfo.setOnClickListener(this);
        mOut.setOnClickListener(this);
        mFee.setOnClickListener(this);
        mIn.setOnClickListener(this);
        mLogout.setOnClickListener(this);
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
            Intent intent=new Intent(HomeActivity.this, InStorage_Activity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.button3){
            SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putString("name","");
            editor.putString("phone","");
            editor.putString("userName","");
            editor.putInt("corporation",0);
            editor.putInt("userType",-1);
            editor.apply();
            Intent intent=new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


}

