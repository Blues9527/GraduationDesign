package com.blues.fragment

import android.content.Intent
import android.os.Bundle

import com.blues.DoDownloadActivity
import com.blues.DoUploadActivity
import com.blues.base.BaseFragment
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentPluspageBinding
import kotlinx.android.synthetic.main.fragment_pluspage.*

/**
 * Created by Administrator on 2018/1/27.
 */

class ExtraFragment : BaseFragment<FragmentPluspageBinding>() {
    override fun setLayoutResourceId(): Int {
        return R.layout.fragment_pluspage
    }

    override fun initLayout(savedInstanceState: Bundle?) {

    }

    override fun lazyFetchData() {

    }

    override fun setListener() {
        doDownload.setOnClickListener {
            startActivity(Intent(activity, DoDownloadActivity::class.java))
        }

        doUpload.setOnClickListener {
            startActivity(Intent(activity, DoUploadActivity::class.java))
        }
    }
}