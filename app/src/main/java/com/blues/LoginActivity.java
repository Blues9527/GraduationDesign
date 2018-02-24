package com.blues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blues.dbmanager.CommonUtils;
import com.example.blues.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private TextView rg_textView;
    private EditText etusername, etpassword;
    private Button lg_button;
    private CommonUtils commonUtils;
    private static final String TAG = "ManiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        commonUtils = new CommonUtils(this);

        rg_textView = (TextView) findViewById(R.id.registerTextView2Id);
        rg_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        lg_button = (Button) findViewById(R.id.loginbutton);
        etusername = (EditText) findViewById(R.id.usernameId);
        etpassword = (EditText) findViewById(R.id.passwordId);

        lg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etusername.getText().toString().trim();
                final String password = etpassword.getText().toString().trim();
                if (username == null || username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //判断用户是否已存在，如果已存在，则跳转到MainAcitity，如果不存在，则提示用户名密码错误
                    if (commonUtils.userisExist(username, password)) {
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        intent.putExtra("login_username",username);
                        intent.putExtra("login_password",password);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "用户不存在或用户名密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();//完成页面跳转后结束当前activity，即将当前activity从盏移除。
            }
        });

    }
}
