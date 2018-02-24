package com.blues.menupages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blues.MainActivity;
import com.example.blues.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by Administrator on 2018/2/12.
 */

public class Checkin_system extends AppCompatActivity {
    private Button qiandao_bt;
    private TextView qiandao_tv;
    private ImageButton qiandao_back;
    private boolean click = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_checkin_system);
        initView();
    }

    private void initView() {
        qiandao_bt = (Button) findViewById(R.id.qiandao_bt);
        qiandao_tv = (TextView) findViewById(R.id.qiandao_tv);
        qiandao_back = (ImageButton)findViewById(R.id.checkin_system_back);


        qiandao_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                String qiandao_date = sdf.format(new Date());

                qiandao_tv.setText("签到成功！当前签到时间为：" + qiandao_date);
                if (click){
                    click = false;
                    qiandao_bt.setEnabled(false);
                }
            }
        });

        qiandao_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkin_intent = new Intent();
                checkin_intent.setClass(Checkin_system.this, MainActivity.class);
                checkin_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                checkin_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(checkin_intent);
            }
        });
    }
}
