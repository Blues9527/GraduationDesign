package com.blues.fragment

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.blues.adapter.MulAdapter
import com.blues.base.BaseFragment
import com.blues.been.IteractionEntity
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentPersonalpageBinding
import kotlinx.android.synthetic.main.fragment_personalpage.*

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.TimeZone

/**
 * Created by Administrator on 2018/1/27.
 */

class PersonFragment : BaseFragment<FragmentPersonalpageBinding>() {
    override fun setLayoutResourceId(): Int {
        return R.layout.fragment_personalpage
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        val lm = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val ma = MulAdapter(activity, mockData())

        rvIteraction.apply {
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
            adapter = ma
        }
    }

    override fun lazyFetchData() {
    }

    override fun setListener() {
    }


    private fun mockData(): List<IteractionEntity> {
        val date = SimpleDateFormat("HH:mm").apply {
            timeZone = TimeZone.getTimeZone("GMT+08")
        }
        val currentTime = date.format(Date())

        val iteraction1 = IteractionEntity().apply {
            pic_iteraction = R.mipmap.icon3
            title_iteraction = "尹某"
            context_iteraction = "人在广东已经嫖到失联~"
            view_iteraction = R.mipmap.pic97
            time_iteraction = currentTime
            source_iteraction = null
        }

        val iteraction2 = IteractionEntity().apply {
            pic_iteraction = R.mipmap.icon1
            title_iteraction = "刘某"
            context_iteraction = "惊日头条~"
            view_iteraction = R.mipmap.pic94
            time_iteraction = currentTime
            source_iteraction = "某日假新闻"
        }

        val iteraction3 = IteractionEntity().apply {
            pic_iteraction = R.mipmap.icon2
            title_iteraction = "张某"
            context_iteraction = "秋名山上见~"
            view_iteraction = R.mipmap.pic96
            time_iteraction = currentTime
            source_iteraction = "QQ飞车"
        }

        val iteraction4 = IteractionEntity().apply {
            pic_iteraction = R.mipmap.icon4
            title_iteraction = "蓝某"
            context_iteraction = "这高铁很晃~"
            view_iteraction = R.mipmap.pic91
            time_iteraction = currentTime
            source_iteraction = "某扑"
        }

        return ArrayList<IteractionEntity>().apply {
            add(iteraction1)
            add(iteraction2)
            add(iteraction3)
            add(iteraction4)
        }
    }
}
