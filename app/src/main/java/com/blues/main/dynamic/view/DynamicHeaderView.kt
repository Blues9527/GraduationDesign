package com.blues.main.dynamic.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.blues.myapplication.R
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

class DynamicHeaderView(context: Context?) : ConstraintLayout(context), RecyclerArrayAdapter.ItemView {

    init {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.header_dynamic, this)
    }

    override fun onBindView(headerView: View?) {

    }

    override fun onCreateView(parent: ViewGroup?): View {
        return this
    }

}