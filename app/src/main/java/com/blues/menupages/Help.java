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

public class Help extends AppCompatActivity {
    private ImageButton help_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_help);

        help_back = (ImageButton) findViewById(R.id.help_back);
        help_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help_back_intent = new Intent();
                help_back_intent.setClass(Help.this, MainActivity.class);
                help_back_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                help_back_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(help_back_intent);
            }
        });
    }
}
