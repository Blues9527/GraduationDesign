package com.blues.main

import android.annotation.SuppressLint
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.blues.base.BaseVmActivity
import com.blues.main.contact.view.ContactFragment
import com.blues.main.home.view.HomeFragment
import com.blues.main.message.view.MessageFragment
import com.blues.main.dynamic.view.DynamicFragment
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.*

/**
 * Created by Administrator on 2018/1/27.
 */

class MainActivity : BaseVmActivity<ActivityMainBinding, MainViewModel>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var mCurrentFragment: Fragment? = null
    private var fragments: ArrayList<Fragment> = ArrayList<Fragment>().apply {
        add(HomeFragment())
        add(ContactFragment())
        add(DynamicFragment())
        add(MessageFragment())
    }

    override fun setLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun setViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun initView() {


        header_nickname?.text = intent.getStringExtra("login_username")

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            disableShiftMode(mDataBinding.bottomView)
        }

        showFragment(null, fragments[0])
    }

    override fun setListener() {

        bottom_view.setOnNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) true
        else super.onOptionsItemSelected(item)
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
        val transaction = (mContext as FragmentActivity).supportFragmentManager.beginTransaction()
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
        when (item.itemId) {
            R.id.menu_home -> showFragment(mCurrentFragment, fragments[0])
            R.id.menu_contacts -> showFragment(mCurrentFragment, fragments[1])
            R.id.menu_personal -> showFragment(mCurrentFragment, fragments[2])
            R.id.menu_message -> showFragment(mCurrentFragment, fragments[3])
        }
        return true
    }
}
