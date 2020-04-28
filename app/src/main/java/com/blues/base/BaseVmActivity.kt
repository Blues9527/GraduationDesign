package com.blues.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.blues.application.App
import com.blues.framework.widget.ProgressDialogFragment

abstract class BaseVmActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mDataBinding: V
    lateinit var mViewModel: VM
    lateinit var mContext: BaseVmActivity<V, VM>

    private lateinit var progressDialogFragment: ProgressDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //赋值context
        mContext = this
        //绑定DataBinding
        mDataBinding = DataBindingUtil.setContentView(mContext, setLayoutResId())

        //设置ViewModel
        mViewModel = ViewModelProvider(mContext).get(setViewModelClass())

        mDataBinding.lifecycleOwner = this

        initView()

        setListener()

        observe()
    }


    //覆写设置layout id
    abstract fun setLayoutResId(): Int

    //覆写设置对应的ViewModel类
    abstract fun setViewModelClass(): Class<VM>

    open fun initView() {

    }

    open fun observe() {

    }

    open fun setListener() {

    }

    fun showProgressDialog(@StringRes message: Int) {

        if (!this::progressDialogFragment.isInitialized) {
            progressDialogFragment = ProgressDialogFragment.newInstance()
        }
        progressDialogFragment.show(supportFragmentManager, message, false)
    }

    fun hideProgressDialog() {
        if (this::progressDialogFragment.isInitialized && progressDialogFragment.isVisible) {
            progressDialogFragment.dismiss()
        }
    }

    open fun showToast(msg: String) {
        Toast.makeText(App.instance, msg, Toast.LENGTH_SHORT).show()
    }
}