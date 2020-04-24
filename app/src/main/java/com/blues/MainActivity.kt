package com.blues

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.blues.base.BaseActivity
import com.blues.database.course.CourseManager
import com.blues.fragment.ContactFragment
import com.blues.fragment.HomeFragment
import com.blues.fragment.MessageFragment
import com.blues.fragment.PersonFragment
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Created by Administrator on 2018/1/27.
 */

class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var picPath: String? = null
    private var mCurrentFragment: Fragment? = null
    private var fragments: ArrayList<Fragment> = ArrayList<Fragment>().apply {
        add(HomeFragment())
        add(ContactFragment())
        add(PersonFragment())
        add(MessageFragment())
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initLayout() {
        tb_title.text = "Blues"
        setSupportActionBar(toolbar)

        picPath = StringBuilder()
                .append(Environment.getExternalStorageDirectory().path)
                .append("/")
                .append(System.currentTimeMillis())
                .append(".png")
                .toString()

        header_nickname?.text = intent.getStringExtra("login_username")

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            disableShiftMode(mDataBinding.bottomView)
        }

        showFragment(null, fragments[0])
    }

    override fun setListener() {

        bottom_view.setOnNavigationItemSelectedListener(this@MainActivity)

        //设置相机监听跳转
        imbtn_camera.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    .apply {
                        putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(picPath!!)))
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
                    fis = FileInputStream(picPath!!)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) true
        else super.onOptionsItemSelected(item)
    }

    companion object {
        private const val REQUEST_CODE = 5
    }

    @SuppressLint("RestrictedApi")
    private fun disableShiftMode(bottomView: BottomNavigationView) {
        val menuView = bottomView.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftMode.isAccessible = true
            shiftMode.setBoolean(menuView, false)
            shiftMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showFragment(from: Fragment?, to: Fragment?) {
        if (to == null) return
        val transaction = (mActivity as FragmentActivity).supportFragmentManager.beginTransaction()
        val isAdded = to.isAdded
        if (!isAdded) {
            if (from != null) {
                transaction.hide(from)
                        .add(R.id.fl_main_container, to, null)
                        .show(to)
                        .commitAllowingStateLoss()
            } else {
                transaction.add(R.id.fl_main_container, to, null)
                        .show(to)
                        .commitAllowingStateLoss()
            }
        } else {
            if (from != null) {
                transaction.hide(from)
                        .show(to)
                        .commitAllowingStateLoss()
            } else {
                transaction.show(to)
                        .commitAllowingStateLoss()
            }
        }
        mCurrentFragment = to
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.i("Blues", item.itemId.toString())
        when (item.itemId) {
            R.id.menu_home -> showFragment(mCurrentFragment, fragments[0])
            R.id.menu_contacts -> showFragment(mCurrentFragment, fragments[1])
            R.id.menu_personal -> showFragment(mCurrentFragment, fragments[2])
            R.id.menu_message -> showFragment(mCurrentFragment, fragments[3])
        }
        return true
    }
}
