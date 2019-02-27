package com.kernal.plateid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kernal.plateid.adapter.ProjectAdapter;
import com.kernal.plateid.adapter.StaffAdapter;
import com.kernal.plateid.adapter.TruckAdapter;
import com.kernal.plateid.adapter.WarehouseAdapter;
import com.kernal.plateid.my.MyData;
import com.kernal.plateid.my.MyMethod;
import com.kernal.plateid.objects.Project;
import com.kernal.plateid.objects.Staff;
import com.kernal.plateid.objects.Truck;
import com.kernal.plateid.objects.Warehouse;
import com.kernal.plateid.utills.CheckPermission;
import com.kernal.plateid.utills.PermissionActivity;

import java.util.ArrayList;
import java.util.List;

import static com.kernal.plateid.CreateActivity.PERMISSION;

public class OutFormActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG=OutFormActivity.class.getSimpleName();
    private Spinner mProject,mWarehouse,mTruck,mStaff;
    private Button mScan,mSearchGoods,mShowList,mShowContract,mChange,mFinish,mDetail;
    List<Project> listProject;
    List<Warehouse> listWarehouse;
    List<Truck> listTruck;
    List<Staff> listStaff;
    ProjectAdapter projectAdapter;
    WarehouseAdapter warehouseAdapter;
    TruckAdapter truckAdapter;
    StaffAdapter staffAdapter;
    private int projectId,warehouseId,truckId,staffId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.out_form);
        getAllView();

        SharedPreferences sharedPreferences=getSharedPreferences("zju",MODE_PRIVATE);
        projectId=sharedPreferences.getInt("project",0);
        warehouseId=sharedPreferences.getInt("warehouse",0);
        truckId=sharedPreferences.getInt("truck",0);
        staffId=sharedPreferences.getInt("staff",0);

        listWarehouse= MyData.getWarehouses();
        listProject=MyData.getProjects();
        listTruck=MyData.getTrucks();
        listStaff=MyData.getStaff();
        warehouseAdapter=new WarehouseAdapter(listWarehouse,this);
        projectAdapter=new ProjectAdapter(listProject,this);
        truckAdapter=new TruckAdapter(listTruck,this);
        staffAdapter=new StaffAdapter(listStaff,this);
        mWarehouse.setAdapter(warehouseAdapter);
        mProject.setAdapter(projectAdapter);
        mTruck.setAdapter(truckAdapter);
        mStaff.setAdapter(staffAdapter);

        mProject.setSelection((int)projectAdapter.getItemId(projectId));
        mWarehouse.setSelection((int)warehouseAdapter.getItemId(warehouseId));
        mTruck.setSelection((int)truckAdapter.getItemId(truckId));
        mStaff.setSelection((int)staffAdapter.getItemId(staffId));
    }

    private void getAllView(){
        mWarehouse=(Spinner)this.findViewById(R.id.sp_warehouse);
        mProject=(Spinner)findViewById(R.id.sp_project);
        mTruck=(Spinner)findViewById(R.id.sp_truck);
        mStaff =(Spinner)findViewById(R.id.sp_pick_worker);
        mScan=(Button)findViewById(R.id.btn_scan);
        mSearchGoods=(Button)findViewById(R.id.btn_search_goods);
        mShowList=(Button)findViewById(R.id.btn_show_list);
        mShowContract=(Button)findViewById(R.id.btn_show_contract);
        mChange=(Button)findViewById(R.id.btn_change);
        mFinish=(Button)findViewById(R.id.btn_finish);
        mDetail=(Button)findViewById(R.id.btn_detail);

        mScan.setOnClickListener(this);
        mSearchGoods.setOnClickListener(this);
        mShowList.setOnClickListener(this);
        mShowContract.setOnClickListener(this);
        mChange.setOnClickListener(this);
        mFinish.setOnClickListener(this);
        mDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_scan) {
            //点击“扫一扫”，立即跳转到扫描界面
            Intent video_intent = new Intent(OutFormActivity.this, MemoryCameraActivity.class);
            if (Build.VERSION.SDK_INT >= 23) {
                CheckPermission checkPermission = new CheckPermission(OutFormActivity.this);
                if (checkPermission.permissionSet(PERMISSION)) {
                    PermissionActivity.startActivityForResult(OutFormActivity.this, 0, "true", PERMISSION);
                } else {
                    video_intent.setClass(getApplicationContext(), MemoryCameraActivity.class);
                    video_intent.putExtra("camera", true);
                    startActivityForResult(video_intent, 4);
                }
            } else {
                video_intent.setClass(getApplicationContext(), MemoryCameraActivity.class);
                video_intent.putExtra("camera", true);
                startActivityForResult(video_intent, 4);
            }
        }else if(v.getId()==R.id.btn_search_goods){
            Intent intent=new Intent(OutFormActivity.this,GoodsInWarehouseActivity.class);
            startActivity(intent);

        }else if(v.getId()==R.id.btn_show_list){

        }else if(v.getId()==R.id.btn_show_contract){

        }else if(v.getId()==R.id.btn_change){

        }else if(v.getId()==R.id.btn_finish){
            finish();
        }else if(v.getId()==R.id.btn_detail){
            Intent intent=new Intent(OutFormActivity.this,DetailActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == 4) {
            String carNumber = data.getStringExtra("number");
            //todo choose a car or create a car
//            listTruck.add(carNumber);
//            MyMethod.fillSpinner(OutFormActivity.this,mTruck,listTruck,
//                    listTruck.size()-1);
        }
    }
}


