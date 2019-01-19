package com.kernal.demo.plateid;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.kernal.plateid.MainActivity;
import com.kernal.plateid.MemoryCameraActivity;

import com.kernal.plateid.utills.CheckPermission;
import com.kernal.plateid.utills.PermissionActivity;

public class MainActivity01 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这里提供三中跳转方式 用户可根据需求自行选择；
        //跳转到demo主界面
        Intent intent  = new Intent(MainActivity01.this, MainActivity.class);
        finish();
        startActivity(intent);
        //跳转到拍照识别界面
//        jumpTakePic();
        //跳转到视频扫描识别界面
//        jumpVideoRecog();
    }
    static final String[] PERMISSION = new String[] {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE, // 读取权限
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.VIBRATE, Manifest.permission.INTERNET,
           };


}
