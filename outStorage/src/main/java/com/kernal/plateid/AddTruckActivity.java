package com.kernal.plateid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kernal.plateid.javabean.FastJsonReturn;
import com.kernal.plateid.my.MySingleton;

import java.util.HashMap;

public class AddTruckActivity extends AppCompatActivity {
    public static final String TAG = AddTruckActivity.class.getSimpleName();
    EditText mCarNumber,mCarLicense,mCarId,mAffiliation;
    Spinner mDriver;
    Button mAddCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_truck);


        mCarNumber=findViewById(R.id.et_car_number);
        mCarLicense=findViewById(R.id.et_car_license);
        mCarId=findViewById(R.id.et_car_id);
        mAffiliation=findViewById(R.id.et_affiliation);
        mDriver=findViewById(R.id.sp_driver);
        mAddCar=findViewById(R.id.btn_add);


        Intent intent=getIntent();
        final String carNumber=intent.getStringExtra("number");
        mCarNumber.setText(carNumber);
        mAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 使用post创建车辆
                String url = "http://120.76.219.196:8082/ScsyERP/BasicInfo/Truck/create";
                StringRequest requestProject = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Log.d(TAG,s);
                                FastJsonReturn fastJsonReturn = JSON.parseObject(s, FastJsonReturn.class);
                                int status=fastJsonReturn.getStatus();
                                if(status!=0){
                                    Toast.makeText(AddTruckActivity.this,"\ncreate truck failed!",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(AddTruckActivity.this,"\ncreate truck success!",Toast.LENGTH_SHORT).show();
                                    setResult(7,new Intent());
                                    finish();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,error.toString());
                    }
                }) {
                    // 携带参数
                    @Override
                    protected HashMap<String, String> getParams()
                            throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        if(!mCarNumber.getText().toString().isEmpty()){
                            hashMap.put("corporation",""+1);
                            hashMap.put("carNumber",mCarNumber.getText().toString());
                            if(!mCarLicense.getText().toString().isEmpty())
                                hashMap.put("carLicense",mCarLicense.getText().toString());
                            if(!mCarId.getText().toString().isEmpty())
                                hashMap.put("carId", mCarId.getText().toString());
                            if(!mAffiliation.getText().toString().isEmpty())
                                hashMap.put("affiliation", mAffiliation.getText().toString());
                            //todo 司机的特殊处理
//                            hashMap.put("driver", ""+staffId);
                        }
                        return hashMap;
                    }
                };
                MySingleton.getInstance(AddTruckActivity.this).addToRequestQueue(requestProject);
            }
        });

    }
}