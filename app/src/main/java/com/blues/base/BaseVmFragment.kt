package com.blues.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseVmFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected lateinit var mViewModel: VM
    lateinit var mDataBinding: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //绑定DataBinding
        mDataBinding = DataBindingUtil.inflate(inflater, setLayoutResId(), container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置ViewModel
        mViewModel = ViewModelProvider(this).get(setViewModelClass())

        //初始化控件相关
        initView()

        setListener()

        observe()
    }

    abstract fun setLayoutResId(): Int

    open fun initView() {

    }

    open fun observe() {

    }

    open fun setListener() {

    }

    abstract fun setViewModelClass(): Class<VM>
}