package com.kernal.demo.plateid;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.kernal.plateid.CreateActivity;
import com.kernal.plateid.MemoryCameraActivity;

import com.kernal.plateid.utills.CheckPermission;
import com.kernal.plateid.utills.PermissionActivity;

public class MainActivity01 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //跳转到demo主界面
        Intent intent  = new Intent(MainActivity01.this, CreateActivity.class);
        finish();
        startActivity(intent);
    }
    static final String[] PERMISSION = new String[] {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE, // 读取权限
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.VIBRATE, Manifest.permission.INTERNET,
           };
}