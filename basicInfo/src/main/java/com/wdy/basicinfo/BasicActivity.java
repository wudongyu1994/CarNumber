package com.wdy.basicinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BasicActivity extends AppCompatActivity {
    Button mCar,mProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        mCar=findViewById(R.id.btn_car_info);
        mProduct=findViewById(R.id.btn_product_info);
        mCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(BasicActivity.this, CarInfoActivity.class);
                startActivity(intent);
            }
        });
        mProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BasicActivity.this,"请从“入库”模块查看货物信息",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
