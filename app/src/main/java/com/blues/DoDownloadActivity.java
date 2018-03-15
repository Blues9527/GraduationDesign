package com.blues;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.blues.Log.L;
import com.example.blues.myapplication.R;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/12.
 */

public class DoDownloadActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton back_bt;
    private Button download_bt;
    private ImageView download_image;

    //    private String mBaseUrl = "http://172.25.91.16:8090/DemoOkHttp/";
    private String mBaseUrl = "http://39.108.158.234/DemoOkHttp/";//服务器地址
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_main);
        initView();

    }

    private void initView() {
        back_bt = (ImageButton) findViewById(R.id.download_back_bt);
        back_bt.setOnClickListener(this);
        download_bt = (Button) findViewById(R.id.download_bt);
        download_bt.setOnClickListener(this);
        download_image = (ImageView) findViewById(R.id.download_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_back_bt:
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(intent);
                break;
            case R.id.download_bt:
                doDownload();
                break;
        }

    }

    private void doDownload() {
        /**
         * 1.new 一个okhttp
         * 2.构造request
         * 3.将request封装为call
         * 4.执行call
         *
         * get方法 url = mBaseUrl + "login?username=blues&password=000000"
         * post方法 url = mBaseUrl
         */
        Request.Builder builder = new Request.Builder();
        final Request request = builder
                .get()
                .url(mBaseUrl + "files/blues.jpg")
                .build();
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
                InputStream is = response.body().byteStream();

                //在App下载并动态加载图片
                final Bitmap bitmap = BitmapFactory.decodeStream(is);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        download_image.setImageBitmap(bitmap);
                    }
                });

                //将图片下载到本地文件夹中
                /*int len = 0;
                File file = new File(Environment.getExternalStorageDirectory(), "blues9526.jpg");
                byte[] buf = new byte[128];
                FileOutputStream fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                fos.close();
                is.close();*/
                L.e("download success !");
            }
        });
    }
}
