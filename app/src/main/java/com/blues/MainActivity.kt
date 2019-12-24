package com.blues

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

import com.blues.adapter.FragmentAdapter
import com.blues.base.BaseActivity
import com.blues.database.course.CourseDataBase
import com.blues.database.course.CourseManager
import com.blues.database.user.User
import com.blues.database.user.UserDataBase
import com.blues.database.user.UserManager
import com.blues.fragment.Fragment_contactpage
import com.blues.fragment.Fragment_homepage
import com.blues.fragment.Fragment_messagepage
import com.blues.fragment.Fragment_personalpage
import com.blues.fragment.Fragment_pluspage
import com.blues.menupages.Checkin_system
import com.blues.menupages.Help
import com.blues.menupages.Individual_center
import com.blues.menupages.Marking_system
import com.blues.menupages.My_album
import com.blues.menupages.Setting
import com.blues.menupages.Share
import com.blues.util.ThreadManager
import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.drawer_header.*

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.StringBuilder
import java.util.ArrayList

/**
 * Created by Administrator on 2018/1/27.
 */

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private var picPath: String? = null

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initLayout() {
        tb_title.text = "Blues"
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        picPath = StringBuilder().append(Environment.getExternalStorageDirectory().path)
                .append("/")
                .append(System.currentTimeMillis())
                .append(".png")
                .toString()

        header_nickname?.text = intent.getStringExtra("login_username")

        viewpager.apply {
            adapter = FragmentAdapter(supportFragmentManager, ArrayList<Fragment>().apply {
                add(Fragment_homepage())
                add(Fragment_contactpage())
                add(Fragment_personalpage())
                add(Fragment_messagepage())
                add(Fragment_pluspage())
            })
            setCurrentItem(PAGE_ONE, false)
            addOnPageChangeListener(this@MainActivity)
        }
        group.check(R.id.homepageId)
    }

    override fun setListener() {

        nav_view.setNavigationItemSelectedListener(this)
        group.setOnCheckedChangeListener(this@MainActivity)

        //设置相机监听跳转
        imbtn_camera.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    .apply {
                        putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(picPath)))
                    }, REQUEST_CODE)
        }

        //search按钮跳转到搜索界面
        imbtn_search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                var fis: FileInputStream? = null
                try {
                    fis = FileInputStream(picPath)
                    BitmapFactory.decodeStream(fis)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } finally {
                    try {
                        fis!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }


    //drawer layout
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.START)) {
            drawer_layout.closeDrawer(Gravity.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_account -> {
                startActivity(Intent().apply {
                    setClass(this@MainActivity, Individual_center::class.java)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }
            R.id.nav_gallery -> {
                startActivity(Intent().apply {
                    setClass(this@MainActivity, My_album::class.java)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }
            R.id.nav_good -> {
                startActivity(Intent().apply {
                    setClass(this@MainActivity, Marking_system::class.java)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }

            R.id.nav_sign -> {
                startActivity(Intent().apply {
                    setClass(this@MainActivity, Checkin_system::class.java)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }

            R.id.nav_setting -> {
                startActivity(Intent().apply {
                    setClass(this@MainActivity, Setting::class.java)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }

            R.id.nav_share -> {
                startActivity(Intent().apply {
                    setClass(this@MainActivity, Share::class.java)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }
            R.id.nav_help -> {
                startActivity(Intent().apply {
                    setClass(this@MainActivity, Help::class.java)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }

            else -> {

            }
        }

        drawer_layout.closeDrawer(Gravity.START)
        return true
    }

    //radio group 为radiobutton绑定监听器
    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.homepageId -> viewpager.setCurrentItem(PAGE_ONE, false)
            R.id.contactpageId -> viewpager.setCurrentItem(PAGE_TWO, false)
            R.id.personalpageId -> viewpager.setCurrentItem(PAGE_THREE, false)
            R.id.messagepageId -> viewpager.setCurrentItem(PAGE_FOUR, false)
            R.id.plusId -> viewpager.setCurrentItem(PAGE_FIVE, false)
        }
    }

    //viewpager监听事件
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    //滑动页面时按钮选中
    override fun onPageScrollStateChanged(state: Int) {
        if (state == ViewPager.SCROLL_STATE_SETTLING) {
            when (viewpager.currentItem) {
                PAGE_ONE -> homepageId.isChecked = true
                PAGE_TWO -> contactpageId.isChecked = true
                PAGE_THREE -> personalpageId.isChecked = true
                PAGE_FOUR -> messagepageId.isChecked = true
                PAGE_FIVE -> plusId.isChecked = true
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 5
        const val PAGE_ONE = 0
        const val PAGE_TWO = 1
        const val PAGE_THREE = 2
        const val PAGE_FOUR = 3
        const val PAGE_FIVE = 4
    }


}
