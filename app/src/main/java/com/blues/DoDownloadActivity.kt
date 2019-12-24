package com.blues

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

import com.blues.Log.L
import com.blues.base.BaseActivity
import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.download_main.*

import java.io.IOException
import java.io.InputStream

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Administrator on 2018/3/12.
 */

class DoDownloadActivity : BaseActivity(), View.OnClickListener {

    private val mBaseUrl = "http://39.108.158.234/DemoOkHttp/"//服务器地址
    private var client = OkHttpClient()


    override fun setLayoutId(): Int {
        return R.layout.download_main
    }

    override fun initLayout() {
    }

    override fun setListener() {
        btnBack.setOnClickListener(this)
        btnDownload.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnBack -> {
                startActivity(Intent().apply {
                    setClass(this@DoDownloadActivity, MainActivity::class.java)
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }
            R.id.btnDownload -> doDownload()
        }

    }

    private fun doDownload() {
        /**
         * 1.new 一个okhttp
         * 2.构造request
         * 3.将request封装为call
         * 4.执行call
         *
         * get方法 url = mBaseUrl + "login?username=blues&password=000000"
         * post方法 url = mBaseUrl
         */
        val builder = Request.Builder()
        val request = builder
                .get()
                .url(mBaseUrl + "files/blues.jpg")
                .build()

        val call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                L.e("onFailure:" + e.message)
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                L.e("onResponse:")
                //在App下载并动态加载图片
                val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                runOnUiThread { download_image!!.setImageBitmap(bitmap) }

                //将图片下载到本地文件夹中
                /*int len = 0;
                File file = new File(Environment.getExternalStorageDirectory(), "blues9526.jpg");
                byte[] buf = new byte[128];
                FileOutputStream fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                fos.close();
                is.close();*/

                L.e("download success !")
            }
        })
    }
}
