package com.blues.main.message.view

import androidx.recyclerview.widget.DividerItemDecoration

import com.blues.base.BaseVmFragment
import com.blues.main.message.model.MessageEntity
import com.blues.main.message.viewmodel.MessageViewModel
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentMessagepageBinding
import kotlinx.android.synthetic.main.fragment_messagepage.*

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

/**
 * Created by Administrator on 2018/1/27.
 */

class MessageFragment : BaseVmFragment<FragmentMessagepageBinding, MessageViewModel>() {

    private val date = SimpleDateFormat("HH:mm").apply {
        timeZone = TimeZone.getTimeZone("GMT+08")
    }
    private val messages = listOf(
            MessageEntity(R.mipmap.icon1, "丢雷1", "你好！很高兴见到你！\n [图片]", date.format(Date())),
            MessageEntity(R.mipmap.icon2, "楼某1", "你好！很高兴见到你！\n [图片]", date.format(Date())),
            MessageEntity(R.mipmap.icon3, "丢雷2", "你好！很高兴见到你！\n [图片]", date.format(Date())),
            MessageEntity(R.mipmap.icon1, "楼某2", "你好！很高兴见到你！\n [图片]", date.format(Date()))
    )

    override fun setLayoutResId(): Int {
        return R.layout.fragment_messagepage
    }

    override fun setViewModelClass(): Class<MessageViewModel> {
        return MessageViewModel::class.java
    }

    override fun initView() {
        rvMessage.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        MessageAdapter().apply {
            bindToRecyclerView(rvMessage)
            setNewData(messages)
        }
    }
}
