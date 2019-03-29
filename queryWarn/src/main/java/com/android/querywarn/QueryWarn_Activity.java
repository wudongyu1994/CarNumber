package com.android.querywarn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.android.querywarn.data_class.Truck_class;
import com.android.querywarn.data_class.Warn_class;
import com.android.querywarn.fastjsontools.FastjsonTools;
import com.android.querywarn.port.OnItemClickListener;
import com.android.querywarn.recyclerview_adapter.RecyclerViewAdapt_getWarn;
import com.android.querywarn.spinner_adapter.SpinnerAdapter_oneColumn;
import com.android.querywarn.utilies.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class QueryWarn_Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private String url_get_warn = "http://120.76.219.196:8082/ScsyERP/Warn/query";
    private static final String URL_GET_TRUCK = "http://120.76.219.196:8082/ScsyERP/BasicInfo/Truck/query";

    private Spinner spinner_truck,spinner_warnType,spinner_status;
    private Button button_return,button_query;
    private RecyclerView recyclerView_getWarn;
    private RecyclerViewAdapt_getWarn recyclerViewAdapt_getWarn;

    private String jsonString_getWarn,jsonString_getTruck;
    private ArrayList<Warn_class> arrayList_getWarn = new ArrayList<>();
    private ArrayList<Truck_class> arrayList_getTruck = new ArrayList<>();
    private ArrayList<String> arrayList_getWarnType = new ArrayList<>();
    private ArrayList<String> arrayList_getStatus= new ArrayList<>();
    private boolean isSpinnerFirstShow_truck = true,isSpinnerFirstShow_status = true,isSpinnerFirstShow_warnType = true;
    private Warn_class warn_class = new Warn_class();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_warn);

        recyclerView_getWarn = (RecyclerView)findViewById(R.id.recyclerView_warn_queryWarn);

        spinner_status = (Spinner)findViewById(R.id.spinner_status_queryWarn);
        spinner_truck = (Spinner)findViewById(R.id.spinner_truck_queryWarn);
        spinner_warnType = (Spinner)findViewById(R.id.spinner_warnType_queryWarn);
        spinner_truck.setOnItemSelectedListener(this);
        spinner_warnType.setOnItemSelectedListener(this);
        spinner_status.setOnItemSelectedListener(this);

        button_query = (Button)findViewById(R.id.button_query_queryWarn);
        button_return = (Button)findViewById(R.id.button_return_queryWarn);
        button_query.setOnClickListener(this);
        button_return.setOnClickListener(this);

        //在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences_getCorporation = getSharedPreferences("QueryWarn", Activity.MODE_PRIVATE);
        //使用getString方法获得value，注意第2个参数是value的默认值
        url_get_warn = sharedPreferences_getCorporation.getString("getQueryURL","http://120.76.219.196:8082/ScsyERP/Warn/query");
        Log.i("QueryWarn_Activity", "url_GET_inStorageFormQuery(first):"+url_get_warn);

        new HTTP_GET_info().execute();
        new HTTP_GET_Warn().execute();

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_return_queryWarn) {
            finish();

        } else if (i == R.id.button_query_queryWarn) {
            url_get_warn = warn_class.getQueryURL();
            Log.i("QueryWarn_Activity", "getQueryURL:" + url_get_warn);
            //实例化SharedPreferences对象（第一步）
            SharedPreferences sharedPreferences_setCorporation = getSharedPreferences("QueryWarn", Activity.MODE_PRIVATE);
            //实例化SharedPreferences.Editor对象（第二步）
            SharedPreferences.Editor editor = sharedPreferences_setCorporation.edit();
            //用putString的方法保存数据
            editor.putString("getQueryURL", warn_class.getQueryURL());
            //提交当前数据
            editor.apply();

            new HTTP_GET_Warn().execute();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int i = parent.getId();
        if (i == R.id.spinner_status_queryWarn) {
            if (isSpinnerFirstShow_status) {
                isSpinnerFirstShow_status = false;
            } else {
                warn_class.setStatus(position + "");
            }

        } else if (i == R.id.spinner_truck_queryWarn) {
            if (isSpinnerFirstShow_truck) {
                isSpinnerFirstShow_truck = false;
            } else {
                warn_class.setTruck(arrayList_getTruck.get(position).getId());
            }

        } else if (i == R.id.spinner_warnType_queryWarn) {
            if (isSpinnerFirstShow_warnType) {
                isSpinnerFirstShow_warnType = false;
            } else {
                warn_class.setWarnType((position + 1) + "");
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    /**
     * AsyncTask异步任务类:HTTP_GET_info
     */
    private class HTTP_GET_info extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                jsonString_getTruck = NetworkUtils.getResponseFromHttpUrl_GET(new URL(URL_GET_TRUCK));
                Log.i("QueryWarn_Activity","jsonString_getTruck:"+jsonString_getTruck);
            } catch (IOException e) {
                Log.e("QueryWarn_Activity",Log.getStackTraceString(e));
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            arrayList_getTruck = FastjsonTools.jsonStringParseToArrayList(jsonString_getTruck,"$.content.data", Truck_class.class);

            SpinnerAdapter_oneColumn spinnerAdapter_getTruck = new SpinnerAdapter_oneColumn(QueryWarn_Activity.this,jsonString_getTruck,arrayList_getTruck,"NO","carNumber",true);
            spinner_truck.setAdapter(spinnerAdapter_getTruck);

            arrayList_getStatus.add("已创建");
            arrayList_getStatus.add("已推送");
            arrayList_getStatus.add("司机已收到");
            arrayList_getStatus.add("司机已处理并上传");
            SpinnerAdapter_oneColumn spinnerAdapter_getStatus = new SpinnerAdapter_oneColumn(QueryWarn_Activity.this,"NO",arrayList_getStatus,"NO","",true);
            spinner_status.setAdapter(spinnerAdapter_getStatus);

            arrayList_getWarnType.add("锁异常");
            arrayList_getWarnType.add("泄露异常");
            arrayList_getWarnType.add("胎压异常");
            arrayList_getWarnType.add("油量异常");
            arrayList_getWarnType.add("超速异常");
            arrayList_getWarnType.add("停车异常");
            arrayList_getWarnType.add("疲劳驾驶异常");
            arrayList_getWarnType.add("急刹车异常");
            arrayList_getWarnType.add("急加速异常");
            arrayList_getWarnType.add("事故异常");
            arrayList_getWarnType.add("超载异常");
            SpinnerAdapter_oneColumn spinnerAdapter_getWarnType = new SpinnerAdapter_oneColumn(QueryWarn_Activity.this,"NO",arrayList_getWarnType,"NO","",true);
            spinner_warnType.setAdapter(spinnerAdapter_getWarnType);

        }
    }
    /**
     * AsyncTask异步任务类:HTTP_GET_info
     */
    private class HTTP_GET_Warn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                jsonString_getWarn = NetworkUtils.getResponseFromHttpUrl_GET(new URL(url_get_warn));
                Log.i("QueryWarn_Activity","jsonString_getWarn:"+jsonString_getWarn);
            } catch (IOException e) {
                Log.e("QueryWarn_Activity",Log.getStackTraceString(e));
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            arrayList_getWarn = FastjsonTools.jsonStringParseToArrayList(jsonString_getWarn,"$.content.data", Warn_class.class);

            RecyclerView.LayoutManager layoutManager_getWarn = new LinearLayoutManager(QueryWarn_Activity.this);
            recyclerView_getWarn.setLayoutManager(layoutManager_getWarn);
            recyclerViewAdapt_getWarn = new RecyclerViewAdapt_getWarn(arrayList_getWarn);
            recyclerView_getWarn.setAdapter(recyclerViewAdapt_getWarn);
            recyclerViewAdapt_getWarn.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent_getWarn = new Intent(QueryWarn_Activity.this,WarnDetail_Activity.class);
                    intent_getWarn.putExtra("clickedPosition_getWarn",position);
                    intent_getWarn.putExtra("jsonString_getWarn",jsonString_getWarn);
                    startActivity(intent_getWarn);
                }
            });

        }
    }
}
