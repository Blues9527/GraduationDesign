package com.blues.util

import android.os.Handler
import android.os.Looper

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * 线程管理类，开启子线程和切换主线程
 */
object ThreadManager {

    //主线程
    private val mMainHandler = Handler(Looper.getMainLooper())

    private val executor = ThreadPoolExecutor(
            10,
            128,
            30L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue(),
            ThreadFactory { r -> Thread(r) })

    /**
     * 主线程执行任务
     *
     * @param runnable 任务
     */
    fun runOnMainThread(runnable: Runnable) {
        mMainHandler.post(runnable)
    }

    /**
     * 延迟执行主线程任务
     *
     * @param runnable 任务
     * @param millis   延迟时长，毫秒
     */
    fun runOnMainThreadDelayed(runnable: Runnable, millis: Long) {
        mMainHandler.postDelayed(runnable, millis)
    }

    /**
     * 使用线程池管理子线程
     *
     * @param runnable 任务
     */
    fun executeRunnable(runnable: Runnable) {
        executor.execute(runnable)
    }

}
