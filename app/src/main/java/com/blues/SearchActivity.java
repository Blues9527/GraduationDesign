package com.blues;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.blues.myapplication.R;


/**
 * Created by Administrator on 2018/2/10.
 */

public class SearchActivity extends AppCompatActivity {

    private ImageButton back_button;
    private ImageButton search_button;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        initView();
    }

    //加载视图
    private void initView() {
        //实例化后退按钮，并绑定监听器
        back_button = (ImageButton)findViewById(R.id.search_backbt);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchback_intent = new Intent();
                searchback_intent.setClass(SearchActivity.this,MainActivity.class);
                searchback_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                searchback_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(searchback_intent);
            }
        });
        //实例化EditText
        editText = (EditText)findViewById(R.id.search_editText);

        //实例化搜索那你u，并绑定监听器
        search_button = (ImageButton)findViewById(R.id.search_searchbt);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击搜索按钮后，对数据库执行查询功能
                String search_text = editText.getText().toString().trim();
            }
        });
    }
}
