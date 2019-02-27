package com.kernal.plateid;

import android.Manifest.permission;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.kernal.plateid.adapter.ProjectAdapter;
import com.kernal.plateid.adapter.StaffAdapter;
import com.kernal.plateid.adapter.TruckAdapter;
import com.kernal.plateid.adapter.WarehouseAdapter;
import com.kernal.plateid.my.MyData;
import com.kernal.plateid.objects.Project;
import com.kernal.plateid.objects.Staff;
import com.kernal.plateid.objects.Truck;
import com.kernal.plateid.objects.Warehouse;
import com.kernal.plateid.utills.CheckPermission;
import com.kernal.plateid.utills.PermissionActivity;

import java.util.List;

public class CreateActivity extends AppCompatActivity implements OnClickListener {
    public static final String TAG = CreateActivity.class.getSimpleName();
    String sn;
    private int ReturnAuthority = -1;
    public AuthService.MyBinder authBinder;

    private Spinner mWarehouse,mProject,mTruck,mStaff;
    private Button mScan,mCreate;
    List<Project> listProject;
    List<Warehouse> listWarehouse;
    List<Truck> listTruck;
    List<Staff> listStaff;
    ProjectAdapter projectAdapter;
    WarehouseAdapter warehouseAdapter;
    TruckAdapter truckAdapter;
    StaffAdapter staffAdapter;
    private int projectId,warehouseId,truckId,staffId;

    public ServiceConnection authConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            authBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            authBinder = (AuthService.MyBinder) service;
            try {
                PlateAuthParameter pap = new PlateAuthParameter();
                pap.sn = sn;
                ReturnAuthority = authBinder.getAuth(pap);
                if (ReturnAuthority != 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.license_verification_failed) + ":" + ReturnAuthority, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.license_verification_success, Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), R.string.failed_check_failure, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } finally {
                if (authBinder != null) {
                    unbindService(authConn);
                }
            }
        }
    };

    static final String[] PERMISSION = new String[]{
            permission.CAMERA,
            permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            permission.READ_EXTERNAL_STORAGE, // 读取权限
            permission.READ_PHONE_STATE,
            permission.VIBRATE, permission.INTERNET,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.out_form_create);
        getAllView();    //找到指定的xml控件
        listWarehouse=MyData.getWarehouses();
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
        mWarehouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                warehouseId=warehouseAdapter.getObjectId(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        mProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                projectId=projectAdapter.getObjectId(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        mTruck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                truckId=truckAdapter.getObjectId(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        mStaff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                staffId=staffAdapter.getObjectId(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void getAllView() {
        mWarehouse=(Spinner)findViewById(R.id.sp_warehouse);
        mProject=(Spinner)findViewById(R.id.sp_project);
        mTruck=(Spinner)findViewById(R.id.sp_truck);
        mStaff=(Spinner)findViewById(R.id.sp_pick_worker);
        mScan=(Button)findViewById(R.id.btn_scan);
        mCreate=(Button)findViewById(R.id.btn_create);

        mScan.setOnClickListener(this);
        mCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_scan) {
            //点击“扫一扫”，立即跳转到扫描界面
            Intent video_intent = new Intent(CreateActivity.this, MemoryCameraActivity.class);
            if (Build.VERSION.SDK_INT >= 23) {
                CheckPermission checkPermission = new CheckPermission(CreateActivity.this);
                if (checkPermission.permissionSet(PERMISSION)) {
                    PermissionActivity.startActivityForResult(CreateActivity.this, 0, "true", PERMISSION);
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
        } else if (v.getId()==R.id.btn_create) {
            //TODO 创建出库单
            SharedPreferences sharedPreferences=getSharedPreferences("zju",MODE_PRIVATE);
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putInt("project",projectId);
            editor.putInt("warehouse",warehouseId);
            editor.putInt("truck",truckId);
            editor.putInt("staff",staffId);
            editor.apply();
            Toast.makeText(CreateActivity.this,"\n"+projectId+"\n"+warehouseId+"\n"+truckId+"\n"+staffId+"\n",Toast.LENGTH_SHORT).show();
            //todo 关闭当前activity，添加部分数据，跳转到OutformActivity
            Intent intent=new Intent(CreateActivity.this, OutFormActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == 4) {
            String carNumber = data.getStringExtra("number");
            //todo choose a car or create a car

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}