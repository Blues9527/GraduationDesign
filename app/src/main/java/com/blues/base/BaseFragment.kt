package com.blues.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    protected var mContext: BaseActivity<V>? = null
    protected lateinit var mDataBinding: V
    private var hasFetchData: Boolean = false
    private var isViewPrepared: Boolean = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed) {
            onResume()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, setLayoutResourceId(), container, false)
        return inflater.inflate(setLayoutResourceId(), container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (mContext != null) {
            this.mContext = context as BaseActivity<V>?
        } else {
            this.mContext = activity as BaseActivity<V>?
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLayout(savedInstanceState)
        setListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepared = true
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (userVisibleHint && !hasFetchData && isViewPrepared) {
            hasFetchData = true
            lazyFetchData()
        }
    }

    override fun onDestroyView() {
        // view被销毁后，将可以重新触发数据懒加载，因为在viewpager下，fragment不会再次新建并走onCreate的生命周期流程，将从onCreateView开始
        hasFetchData = false
        isViewPrepared = false
        super.onDestroyView()
    }

    fun getmContext(): BaseActivity<V>? {
        return mContext
    }

    abstract fun setLayoutResourceId(): Int

    abstract fun initLayout(savedInstanceState: Bundle?)

    abstract fun lazyFetchData()

    abstract fun setListener()
}