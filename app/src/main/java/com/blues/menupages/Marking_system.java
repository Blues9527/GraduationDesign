package com.blues.menupages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blues.MainActivity;
import com.example.blues.myapplication.R;

/**
 * Created by Administrator on 2018/2/12.
 */

public class Marking_system extends AppCompatActivity {
    private RatingBar ratingBar_01, ratingBar_02, ratingBar_03, ratingBar_04;
    private TextView pingfen_tv;
    private Button pingfen_bt;
    private ImageButton pingfen_back;
    private float i_1, i_2, i_3, i_4, i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_marking_system);
        initView();
    }

    private void initView() {
        ratingBar_01 = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar_02 = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar_03 = (RatingBar) findViewById(R.id.ratingBar3);
        ratingBar_04 = (RatingBar) findViewById(R.id.ratingBar4);
        pingfen_bt = (Button) findViewById(R.id.pingfen_bt);
        pingfen_tv = (TextView) findViewById(R.id.fenshu);
        pingfen_back = (ImageButton) findViewById(R.id.marking_system_back);

        ratingBar_01.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                i_1 = ratingBar_01.getRating();
                Toast.makeText(Marking_system.this, "课堂质量选择的分数是：" + i_1, Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar_02.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                i_2 = ratingBar_02.getRating();
                Toast.makeText(Marking_system.this, "教学态度选择的分数是：" + i_2, Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar_03.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                i_3 = ratingBar_03.getRating();
                Toast.makeText(Marking_system.this, "课后作业选择的分数是：" + i_3, Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar_04.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                i_4 = ratingBar_04.getRating();
                Toast.makeText(Marking_system.this, "负责程度选择的分数是：" + i_4, Toast.LENGTH_SHORT).show();
            }
        });

        pingfen_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = (i_1 + i_2 + i_3 + i_4) / 4;
                pingfen_tv.setText("当前的总评是：" + i);
            }
        });

        pingfen_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent marking_intent = new Intent();
                marking_intent.setClass(Marking_system.this, MainActivity.class);
                marking_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                marking_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(marking_intent);
            }
        });

    }

}
