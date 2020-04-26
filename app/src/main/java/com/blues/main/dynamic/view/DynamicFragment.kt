package com.blues.main.dynamic.view

import androidx.recyclerview.widget.DividerItemDecoration

import com.blues.base.BaseVmFragment
import com.blues.main.dynamic.model.Dynamic
import com.blues.main.dynamic.viewmodel.DynamicViewModel
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentPersonalpageBinding
import kotlinx.android.synthetic.main.fragment_personalpage.*

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

/**
 * Created by Administrator on 2018/1/27.
 */

class DynamicFragment : BaseVmFragment<FragmentPersonalpageBinding, DynamicViewModel>() {
    private val date = SimpleDateFormat("HH:mm").apply {
        timeZone = TimeZone.getTimeZone("GMT+08")
    }
    private val dynamics = listOf(
            Dynamic(R.mipmap.icon1, "刘某", "惊日头条~", "人在广东已经嫖到失联~", R.mipmap.pic94, date.format(Date()), "某日假新闻"),
            Dynamic(R.mipmap.icon2, "张某", "惊日头条~", "秋名山上见~", R.mipmap.pic94, date.format(Date()), "QQ飞机"),
            Dynamic(R.mipmap.icon3, "尹某", "惊日头条~", "惊日头条~", R.mipmap.pic94, date.format(Date()), ""),
            Dynamic(R.mipmap.icon4, "蓝某", "惊日头条~", "这高铁很晃~", R.mipmap.pic94, date.format(Date()), "某扑")
    )


    override fun initView() {

        rvIteraction.apply {
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        }

        DynamicAdapter().apply {
            bindToRecyclerView(rvIteraction)
            setNewData(dynamics)
            addHeaderView(DynamicHeaderView(context))
        }
    }

    override fun setListener() {
    }


    override fun setLayoutResId(): Int {
        return R.layout.fragment_personalpage
    }

    override fun setViewModelClass(): Class<DynamicViewModel> {
        return DynamicViewModel::class.java
    }
}
