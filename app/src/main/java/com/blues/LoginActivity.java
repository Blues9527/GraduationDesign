package com.blues;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox remember_cb;
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
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
        remember_cb = (CheckBox) findViewById(R.id.rememberId);

        //存储用户信息
        sp = getSharedPreferences("user_data",MODE_PRIVATE);
        spe = sp.edit();

        //判断之前是否记录过用户信息，保存过的则直接读取用户信息
        if (sp.getBoolean("is_remembered",false)){
            etusername.setText(sp.getString("username",""));
            etpassword.setText(sp.getString("psd",""));
        }

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
                            //如果用户存在，则用sharedpreferences.editor保存用户数据
                            if (remember_cb.isChecked()){
                                spe.putString("username",username);
                                spe.putString("psd",password);
                                spe.putBoolean("is_remembered",true);
                                spe.commit();
                            }
                            //执行跳转
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            intent.putExtra("login_username", username);
                            intent.putExtra("login_password", password);
                            startActivity(intent);
                            finish();//完成页面跳转后结束当前activity，即将当前activity从盏移除。
                        } else {
                            Toast.makeText(LoginActivity.this, "用户不存在或用户名密码错误！", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
        });

    }
}
