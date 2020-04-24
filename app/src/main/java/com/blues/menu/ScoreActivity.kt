package com.blues.menu

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.RatingBar
import android.widget.Toast

import com.blues.MainActivity
import com.blues.adapter.MyAdapter
import com.blues.base.BaseActivity
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.MenuMarkingSystemBinding
import kotlinx.android.synthetic.main.menu_marking_system.*

import java.util.ArrayList

/**
 * Created by Administrator on 2018/2/12.
 */

class ScoreActivity : BaseActivity<MenuMarkingSystemBinding>() {

    private var score1: Float = 0F
    private var score2: Float = 0F
    private var score3: Float = 0F
    private var score4: Float = 0F

    override fun setLayoutId(): Int {
        return R.layout.menu_marking_system
    }

    override fun initLayout() {
        ratingBar1!!.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, _, _ ->
            score1 = ratingBar1.rating
            Toast.makeText(this@ScoreActivity, "课堂质量选择的分数是：$score1", Toast.LENGTH_SHORT).show()
        }
        ratingBar2!!.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, _, _ ->
            score2 = ratingBar2!!.rating
            Toast.makeText(this@ScoreActivity, "教学态度选择的分数是：$score2", Toast.LENGTH_SHORT).show()
        }
        ratingBar3!!.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, _, _ ->
            score3 = ratingBar3!!.rating
            Toast.makeText(this@ScoreActivity, "课后作业选择的分数是：$score3", Toast.LENGTH_SHORT).show()
        }
        ratingBar4!!.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, _, _ ->
            score4 = ratingBar4!!.rating
            Toast.makeText(this@ScoreActivity, "负责程度选择的分数是：$score3", Toast.LENGTH_SHORT).show()
        }


        //配置适配器 MyAdapter为自定义适配器，继承自BaseAdapter
        val adapter = MyAdapter(this)
        spScore.adapter = adapter
        adapter.setDatas(mockData())
    }

    override fun setListener() {
        //为选项设置监听事件
        spScore.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@ScoreActivity, "您选择的老师是：" + mockData()[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        btnBack.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@ScoreActivity, MainActivity::class.java)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
            })
        }

        btnSubmit!!.setOnClickListener {
            val i = (score1 + score2 + score3 + score4) / 4
            tvScore.text = "当前的总评是：$i"
        }
    }

    private fun mockData(): ArrayList<String> {
        //添加数据
        return ArrayList<String>().apply {
            add("陈光明")
            add("陈冰川")
            add("陈光辉")
            add("陈蔼祥")
        }
    }
}
