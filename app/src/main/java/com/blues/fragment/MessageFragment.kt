package com.blues.fragment

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.blues.adapter.MultipleItemAdapter
import com.blues.base.BaseFragment
import com.blues.been.ChatEntity
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentMessagepageBinding

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.TimeZone

/**
 * Created by Administrator on 2018/1/27.
 */

class MessageFragment : BaseFragment<FragmentMessagepageBinding>() {
    private var recyclerView: RecyclerView? = null

    override fun setLayoutResourceId(): Int {
        return R.layout.fragment_messagepage
    }

    override fun initLayout(savedInstanceState: Bundle?) {

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val adapter = MultipleItemAdapter(activity, mockData())

        recyclerView?.layoutManager = layoutManager
        recyclerView?.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
        recyclerView?.adapter = adapter
    }

    override fun lazyFetchData() {
    }

    override fun setListener() {
    }

    private fun mockData(): List<ChatEntity> {
        /**
         * chat1\chat2\chat3为测试数据，实际应用时数据来源为数据库或其他存储工具。
         */

        val date = SimpleDateFormat("HH:mm").apply {
            timeZone = TimeZone.getTimeZone("GMT+08")
        }
        val currentTime = date.format(Date())

        val chat1 = ChatEntity().apply {
            pic_chat = R.mipmap.icon1
            title_chat = "刘某"
            context_chat = "你好！很高兴见到你！"
            time_chat = currentTime
        }

        val chat2 = ChatEntity().apply {
            pic_chat = R.mipmap.icon2
            title_chat = "张某"
            context_chat = "你好！难过见到你！"
            time_chat = currentTime
        }

        val chat3 = ChatEntity().apply {
            pic_chat = R.mipmap.icon3
            title_chat = "尹某"
            context_chat = "你好！很不想见到你！"
            time_chat = currentTime
        }

        return ArrayList<ChatEntity>().apply {
            add(chat1)
            add(chat2)
            add(chat3)
        }
    }
}
