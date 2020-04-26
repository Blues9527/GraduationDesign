package com.blues.main.contact.view

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.blues.base.BaseVmFragment
import com.blues.main.contact.model.Contacter
import com.blues.main.contact.viewmodel.ContactViewModel
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentContactpageBinding
import com.jude.easyrecyclerview.decoration.DividerDecoration
import kotlinx.android.synthetic.main.fragment_contactpage.*


/**
 * Created by Administrator on 2018/1/27.
 */

class ContactFragment : BaseVmFragment<FragmentContactpageBinding, ContactViewModel>() {

    private val contacter = listOf(
            Contacter(R.mipmap.ic_launcher, "蓝某建"),
            Contacter(R.mipmap.ic_launcher, "刘某良"),
            Contacter(R.mipmap.ic_launcher, "尹某俊"),
            Contacter(R.mipmap.ic_launcher, "韩某艺"),
            Contacter(R.mipmap.ic_launcher, "周某星"),
            Contacter(R.mipmap.ic_launcher, "张某林"),
            Contacter(R.mipmap.ic_launcher, "马某妍"),
            Contacter(R.mipmap.ic_launcher, "郑某铃"),
            Contacter(R.mipmap.ic_launcher, "张某祥"),
            Contacter(R.mipmap.ic_launcher, "江某杰")
    )

    override fun initView() {


        rvContact.apply {
            addItemDecoration(DividerDecoration(resources.getColor(R.color.text_color), 1))
            layoutManager = LinearLayoutManager(context)
        }

        ContacterAdapter().apply {
            bindToRecyclerView(rvContact)
            setNewData(contacter)
            addHeaderView(ContactHeaderView(context))
            setOnItemClickListener { _, _, position ->
                Toast.makeText(context, data[position].contactName, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun setLayoutResId(): Int {
        return R.layout.fragment_contactpage
    }

    override fun setViewModelClass(): Class<ContactViewModel> {
        return ContactViewModel::class.java
    }
}
