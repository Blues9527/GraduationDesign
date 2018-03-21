package com.blues;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blues.adapter.FragmentAdapter;
import com.blues.fragment.Fragment_contactpage;
import com.blues.fragment.Fragment_homepage;
import com.blues.fragment.Fragment_messagepage;
import com.blues.fragment.Fragment_personalpage;
import com.blues.fragment.Fragment_pluspage;
import com.blues.menupages.Checkin_system;
import com.blues.menupages.Help;
import com.blues.menupages.Individual_center;
import com.blues.menupages.Marking_system;
import com.blues.menupages.My_album;
import com.blues.menupages.Setting;
import com.blues.menupages.Share;
import com.example.blues.myapplication.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/27.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private ImageButton imbt_search;
    private RadioGroup group;
    private RadioButton igbt1, igbt2, igbt3, igbt4, igbt5;
    private TextView header_nickname, header_email, header_phone;
    private LinearLayout header_layout;
    private String picPath;
    private static final int REQUEST_CODE = 5;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        initSearch();
        picPath = Environment.getExternalStorageDirectory().getPath() + "/" + "test.png";
        initCamera();

        //实例化对象
        /**
         * 侧拉菜单加载
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //加载用户名
        View headerView = navigationView.getHeaderView(0);
        header_nickname = (TextView) headerView.findViewById(R.id.header_nickname);
        String login_username = getIntent().getStringExtra("login_username");
        header_nickname.setText(login_username);
        navigationView.setNavigationItemSelectedListener(this);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment_homepage());
        fragments.add(new Fragment_contactpage());
        fragments.add(new Fragment_personalpage());
        fragments.add(new Fragment_messagepage());
        fragments.add(new Fragment_pluspage());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(adapter);
        vp.setCurrentItem(PAGE_ONE, false);
        vp.addOnPageChangeListener(this);
        igbt3.setChecked(true);

    }

    //camera按钮跳转功能
    private void initCamera() {
        ImageButton camera_bt = (ImageButton) findViewById(R.id.imbtn_camera);
        camera_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri = Uri.fromFile(new File(picPath));
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(camera_intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK ){
            if (requestCode == REQUEST_CODE){
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(picPath);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //search按钮跳转功能
    private void initSearch() {
        imbt_search = (ImageButton) findViewById(R.id.imbtn_search);
        imbt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search_intent = new Intent();
                search_intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(search_intent);
            }
        });
    }


    //viewpager绑定imagebutton
    protected void setView() {

        group = (RadioGroup) findViewById(R.id.group);
        igbt1 = (RadioButton) findViewById(R.id.homepageId);
        igbt2 = (RadioButton) findViewById(R.id.contactpageId);
        igbt3 = (RadioButton) findViewById(R.id.personalpageId);
        igbt4 = (RadioButton) findViewById(R.id.messagepageId);
        igbt5 = (RadioButton) findViewById(R.id.plusId);
        group.setOnCheckedChangeListener(this);

    }


    //drawerlayout
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            Intent account_intent = new Intent();
            account_intent.setClass(MainActivity.this, Individual_center.class);
            account_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            account_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
            startActivity(account_intent);

        } else if (id == R.id.nav_gallery) {
            Intent gollery_intent = new Intent();
            gollery_intent.setClass(MainActivity.this, My_album.class);
            gollery_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            gollery_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
            startActivity(gollery_intent);

        } else if (id == R.id.nav_good) {
            Intent marking_system_intent = new Intent();
            marking_system_intent.setClass(MainActivity.this, Marking_system.class);
            marking_system_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            marking_system_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
            startActivity(marking_system_intent);

        } else if (id == R.id.nav_sign) {
            Intent checkin_system_intent = new Intent();
            checkin_system_intent.setClass(MainActivity.this, Checkin_system.class);
            checkin_system_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            checkin_system_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
            startActivity(checkin_system_intent);

        } else if (id == R.id.nav_setting) {
            Intent setting_intent = new Intent();
            setting_intent.setClass(MainActivity.this, Setting.class);
            setting_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            setting_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
            startActivity(setting_intent);

        } else if (id == R.id.nav_share) {
            Intent share_intent = new Intent();
            share_intent.setClass(MainActivity.this, Share.class);
            share_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            share_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
            startActivity(share_intent);

        } else if (id == R.id.nav_help) {
            Intent help_intent = new Intent();
            help_intent.setClass(MainActivity.this, Help.class);
            help_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            help_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
            startActivity(help_intent);

        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }

    //radiogroup 为radiobutton绑定监听器
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.homepageId:
                vp.setCurrentItem(PAGE_ONE);
                break;
            case R.id.contactpageId:
                vp.setCurrentItem(PAGE_TWO);
                break;
            case R.id.personalpageId:
                vp.setCurrentItem(PAGE_THREE);
                break;
            case R.id.messagepageId:
                vp.setCurrentItem(PAGE_FOUR);
                break;
            case R.id.plusId:
                vp.setCurrentItem(PAGE_FIVE);
                break;

        }
    }

    //viewpager监听事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    //滑动页面时按钮选中
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_SETTLING) {
            switch (vp.getCurrentItem()) {
                case PAGE_ONE:
                    igbt1.setChecked(true);
                    break;
                case PAGE_TWO:
                    igbt2.setChecked(true);
                    break;
                case PAGE_THREE:
                    igbt3.setChecked(true);
                    break;
                case PAGE_FOUR:
                    igbt4.setChecked(true);
                    break;
                case PAGE_FIVE:
                    igbt5.setChecked(true);
                    break;
            }
        }

    }


}
