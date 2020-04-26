package com.blues.main.contact.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.blues.myapplication.R
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

class ContactHeaderView(context: Context?) : LinearLayout(context), RecyclerArrayAdapter.ItemView {

    init {
        initHeaderView()
    }

    private fun initHeaderView() {
        LayoutInflater.from(context).inflate(R.layout.header_contact, this)
    }


    override fun onBindView(headerView: View?) {

    }

    override fun onCreateView(parent: ViewGroup?): View {
        return this
    }

}