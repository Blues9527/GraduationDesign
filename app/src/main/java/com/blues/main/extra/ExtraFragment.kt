package com.blues.main.extra

import android.content.Intent
import android.os.Bundle

import com.blues.main.extra.download.DoDownloadActivity
import com.blues.main.extra.upload.DoUploadActivity
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