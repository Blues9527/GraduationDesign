package com.blues;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.blues.Log.L;
import com.example.blues.myapplication.R;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/12.
 */

public class DoUploadActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton back_bt;
    private Button upload_bt;

    //    private String mBaseUrl = "http://172.25.91.16:8090/DemoOkHttp/"; 本机地址
    private String mBaseUrl = "http://39.108.158.234/DemoOkHttp/";//服务器地址
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_main);
        initView();

    }

    private void initView() {
        back_bt = (ImageButton) findViewById(R.id.upload_back_bt);
        back_bt.setOnClickListener(this);
        upload_bt = (Button) findViewById(R.id.upload_bt);
        upload_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_back_bt:
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(intent);
                break;
            case R.id.upload_bt:
                doUpload();
                break;
        }
    }

    private void doUpload() {
        /**
         *
         * login为struts2上的action
         */

//        File file = new File(Environment.getExternalStorageDirectory(), "wtfblues3.jpg");
        File file = new File(Environment.getExternalStorageDirectory(), "blues9526.jpg");
        if (!file.exists()) {
            L.e(file.getAbsolutePath() + " not exits!");
            return;
        }

        //mime type
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(mBaseUrl + "postFile").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("onFailure:" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.e("onResponse:");
                L.e("upload success !");
            }
        });
    }
}
