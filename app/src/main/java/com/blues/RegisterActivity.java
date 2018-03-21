package com.blues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.blues.dbmanager.CommonUtils;
import com.example.blues.myapplication.R;
import com.student.entity.Student;

public class RegisterActivity extends AppCompatActivity {

    private Button reg_btn;
    private ImageButton back_btn;
    private EditText edName, edPassword, edEmail, edPhone;
    private CommonUtils mcommonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        mcommonUtils = new CommonUtils(this);//问题语句，原因--->>空指针异常

        reg_btn = (Button) findViewById(R.id.register_registerbuttonId);
        back_btn = (ImageButton) findViewById(R.id.register_backbuttonId);

        edName = (EditText) findViewById(R.id.register_usernameId);
        edPassword = (EditText) findViewById(R.id.register_passwordId);
        edEmail = (EditText) findViewById(R.id.register_emailId);
        edPhone = (EditText) findViewById(R.id.register_phoneId);


        /**
         * private CommonUtils commonUtils;
         * 数据库操作例子
         * 按钮绑定监听器
         * public void xx(View view){
         * 1. Student student = new Student();
         * 2.student.setXX("");
         * 3.commonUtils.xxStudent();
         * }
         */


        //注册按钮功能实现
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edName.getText().toString().trim();
                final String password = edPassword.getText().toString().trim();
                final String email = edEmail.getText().toString().trim();
                final String phone = edPhone.getText().toString().trim();
                //四个EditText 的值不能为空
                if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty()) {
                    //提示注册失败
                    Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                } else {

                    if (mcommonUtils.userisExist(username, password)) {
                        //提示用户已存在
                        Toast.makeText(RegisterActivity.this, "用户已存在！", Toast.LENGTH_SHORT).show();


                    } else {
                        //执行插入操作，并返回注册页面
                        Student student = new Student();
                        student.setUsername(username);
                        student.setPassword(password);
                        student.setEmail(email);
                        student.setPhone(phone);
                        mcommonUtils.insertStudent(student);
                        Toast.makeText(RegisterActivity.this,"注册成功,两秒后跳转到登陆界面！",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setClass(RegisterActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                        startActivity(intent);
                    }

                }
                finish();
            }
        });
        /*
        跳转到login.activity

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/

        //返回按钮，返回登陆界面
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    //设置延时跳转
    /*public void setTimeoutIntent() {
        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

            @Override
            public void run() {
                startActivity(intent);
            }
        };
        time.schedule(tk, 3000);
    }*/

}
