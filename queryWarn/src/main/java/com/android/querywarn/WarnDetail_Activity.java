package com.android.querywarn;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.querywarn.data_class.Corporation_class;
import com.android.querywarn.data_class.Truck_class;
import com.android.querywarn.data_class.Warn_class;
import com.android.querywarn.fastjsontools.FastjsonTools;
import com.android.querywarn.utilies.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class WarnDetail_Activity extends AppCompatActivity implements View.OnClickListener{

    private Button button_return;
    private TextView textView_coporation,textView_outStorageForm,textView_truck,textView_truckLog,
            textView_createTime,textView_place,textView_id,textView_status,textView_warnType;

    private String url_get_corporation = "";
    private String url_get_truck = "";

    private String jsonString_getWarn,jsonString_getCorporation,jsonString_getTruck;
    private ArrayList<Corporation_class> arrayList_getCorporation = new ArrayList<>();
    private ArrayList<Truck_class> arrayList_getTruck = new ArrayList<>();
    private ArrayList<Warn_class> arrayList_getWarn= new ArrayList<>();
    private Warn_class warn_class = new Warn_class();
    private int clickedPosition_getWarn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warn_detail);

        textView_coporation = (TextView)findViewById(R.id.textView_corporation_warnDetail);
        textView_createTime = (TextView)findViewById(R.id.textView_createTime_warnDetail);
        textView_id = (TextView)findViewById(R.id.textView_id_warnDetail);
        textView_outStorageForm = (TextView)findViewById(R.id.textView_outStorageForm_warnDetail);
        textView_place = (TextView)findViewById(R.id.textView_place_warnDetail);
        textView_status = (TextView)findViewById(R.id.textView_status_warnDetail);
        textView_truck = (TextView)findViewById(R.id.textView_truck_warnDetail);
        textView_truckLog = (TextView)findViewById(R.id.textView_truckLog_warnDetail);
        textView_warnType = (TextView)findViewById(R.id.textView_warnType_warnDetail);

        button_return = (Button)findViewById(R.id.button_return_warnDetail);
        button_return.setOnClickListener(this);

        jsonString_getWarn = getIntent().getStringExtra("jsonString_getWarn");
        clickedPosition_getWarn =  getIntent().getIntExtra("clickedPosition_getWarn",0);
        arrayList_getWarn = FastjsonTools.jsonStringParseToArrayList(jsonString_getWarn,"$.content.data",Warn_class.class);
        warn_class = arrayList_getWarn.get(clickedPosition_getWarn);

        url_get_corporation = "http://120.76.219.196:8082/ScsyERP/BasicInfo/Corporation/query?id="+warn_class.getCorporation();
        url_get_truck = "http://120.76.219.196:8082/ScsyERP/BasicInfo/Truck/query?id="+warn_class.getTruck();

        new HTTP_GET_info().execute();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_return_warnDetail){
            finish();
        }
    }

    /**
     * AsyncTask异步任务类:HTTP_GET_info
     */
    private class HTTP_GET_info extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                jsonString_getCorporation = NetworkUtils.getResponseFromHttpUrl_GET(new URL(url_get_corporation));
                jsonString_getTruck = NetworkUtils.getResponseFromHttpUrl_GET(new URL(url_get_truck));

                Log.i("WarnDetail_Activity","jsonString_getCorporation:"+jsonString_getCorporation);
            } catch (IOException e) {
                Log.e("WarnDetail_Activity",Log.getStackTraceString(e));
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            arrayList_getCorporation = FastjsonTools.jsonStringParseToArrayList(jsonString_getCorporation,"$.content.data", Corporation_class.class);
            arrayList_getTruck = FastjsonTools.jsonStringParseToArrayList(jsonString_getTruck,"$.content.data", Truck_class.class);



            textView_id.setText(warn_class.getId()+"");
            textView_outStorageForm.setText(warn_class.getOutStorageForm()+"");
            textView_place.setText("("+warn_class.getgPSX()+","+warn_class.getgPSY()+")");
            textView_status.setText(warn_class.getStatus()+"");
            textView_truckLog.setText(warn_class.getTruckLog()+"");
            textView_warnType.setText(warn_class.getWarnType()+"");
            textView_coporation.setText(arrayList_getCorporation.get(0).getName());
            textView_truck.setText(arrayList_getTruck.get(0).getCarNumber());

            Date date_createTime = new Date((long)warn_class.getCreateTime());
            SimpleDateFormat format_createTime = new SimpleDateFormat("yyyy-MM-dd");
            textView_createTime.setText(format_createTime.format(date_createTime));




        }
    }


}
