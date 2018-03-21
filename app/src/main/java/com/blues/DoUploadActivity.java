package com.blues;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blues.Log.L;
import com.example.blues.myapplication.R;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/12.
 */

public class DoUploadActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton back_bt;
    private Button upload_bt, select_bt;
    private TextView path_tv,fileName_tv;
    private static final int REC_REQUESTCODE = 1;
    private static final String TAG = "FilePicker";
    private String fileName;
    File file;

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
        select_bt = (Button) findViewById(R.id.select_bt);
        select_bt.setOnClickListener(this);
        path_tv = (TextView) findViewById(R.id.file_path);
        fileName_tv = (TextView) findViewById(R.id.file_name);
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
            case R.id.select_bt:
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("*/*");
                intent1.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent1, "Select a File to Upload"), REC_REQUESTCODE);
        }
    }

    /**
     * 文件选择
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REC_REQUESTCODE && data != null) {
            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
            String string = uri.toString();
            String a[] = new String[2];
            //判断文件是否在sd卡中
            if (string.indexOf(String.valueOf(Environment.getExternalStorageDirectory())) != -1) {
                //对Uri进行切割
                a = string.split(String.valueOf(Environment.getExternalStorageDirectory()));
                //获取到file
                file = new File(Environment.getExternalStorageDirectory(), a[1]);
                path_tv.setText("文件的路径为：" + file.getAbsolutePath());

                fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/") + 1, file.getAbsolutePath().length());
                fileName_tv.setText("文件名为："+fileName);


            } else if (string.indexOf(String.valueOf(Environment.getDataDirectory())) != -1) { //判断文件是否在手机内存中
                //对Uri进行切割
                a = string.split(String.valueOf(Environment.getDataDirectory()));
                //获取到file
                file = new File(Environment.getDataDirectory(), a[1]);
                path_tv.setText(file.getAbsolutePath());
            } else {
                //出现其他没有考虑到的情况
                Toast.makeText(this, "文件路径解析失败！", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.i(TAG, "onActivityResult" + file.getAbsolutePath());
        }
    }

    private void doUpload() {
        /**
         *
         * login为struts2上的action
         */

        //        File file = new File(Environment.getExternalStorageDirectory(), "wtfblues3.jpg");
        //        File file = new File(Environment.getExternalStorageDirectory(), "blues9526.jpg");
        File file1 = new File(file.getParent(), fileName);
        if (!file1.exists()) {
            L.e(file1.getAbsolutePath() + " not exits!");
            return;
        }

        //mime type
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file1", fileName, RequestBody.create(MediaType.parse("application/octet-stream"), file1))
                .build();
        //RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file1);
        Request.Builder builder = new Request.Builder();
        // Request request = builder.url(mBaseUrl + "postFile").post(requestBody).build();
        Request request = builder.url(mBaseUrl + "uploadFile").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("onFailure:" + e.getMessage());
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DoUploadActivity.this, "上传失败!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.e("onResponse:");
                L.e("upload success !");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DoUploadActivity.this, "上传成功!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
