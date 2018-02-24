package com.blues.menupages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.blues.MainActivity;
import com.example.blues.myapplication.R;

/**
 * Created by Administrator on 2018/2/12.
 */

public class Individual_center extends AppCompatActivity {
    private ImageButton back_ibt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_individual_center);
        initView();
    }

    private void initView() {
        back_ibt = (ImageButton)findViewById(R.id.indiv_center_back);
        back_ibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indiv_back_intent = new Intent();
                indiv_back_intent.setClass(Individual_center.this, MainActivity.class);
                indiv_back_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                indiv_back_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(indiv_back_intent);
            }
        });
    }
}
