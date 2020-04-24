package com.blues.fragment

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.blues.adapter.MyRecyclerAdapter
import com.blues.base.BaseFragment
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentContactpageBinding
import kotlinx.android.synthetic.main.fragment_contactpage.*

import java.util.ArrayList

/**
 * Created by Administrator on 2018/1/27.
 */

class ContactFragment : BaseFragment<FragmentContactpageBinding>() {

    private val data: ArrayList<String>
        get() {
            val data = ArrayList<String>()
            val a = arrayOf("蓝某建", "张某祥", "刘某良", "尹某俊", "韩某艺", "周某星", "张某林")
            for (i in a.indices) {
                data.add(a[i])
            }
            return data
        }

    override fun setLayoutResourceId(): Int {
        return R.layout.fragment_contactpage
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val adapter = MyRecyclerAdapter(data)

        rvContact.layoutManager = layoutManager
        rvContact.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rvContact.adapter = adapter
    }

    override fun lazyFetchData() {
    }

    override fun setListener() {
    }


}
