package com.blues

import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast

import com.blues.Log.L
import com.blues.base.BaseActivity
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.UploadMainBinding
import kotlinx.android.synthetic.main.upload_main.*

import java.io.File
import java.io.IOException

import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

/**
 * Created by Administrator on 2018/3/12.
 */

class DoUploadActivity : BaseActivity<UploadMainBinding>(), View.OnClickListener {

    private var fileName: String? = null
    private lateinit var file: File
    private val mBaseUrl = "http://39.108.158.234/DemoOkHttp/"//服务器地址
    private val client = OkHttpClient()

    override fun setLayoutId(): Int {
        return R.layout.upload_main
    }

    override fun initLayout() {
    }

    override fun setListener() {
        btnBack.setOnClickListener(this)
        btnUpload.setOnClickListener(this)
        btnSelect.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnBack -> {
                startActivity(Intent().apply {
                    setClass(this@DoUploadActivity, MainActivity::class.java)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                })
            }
//            R.id.btnUpload -> doUpload()
            R.id.btnSelect -> {
                startActivityForResult(Intent.createChooser(Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "*/*"
                    addCategory(Intent.CATEGORY_OPENABLE)
                }, "Select a File to Upload"), REC_REQUEST_CODE)
            }
        }
    }

    /**
     * 文件选择
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == Activity.RESULT_OK && requestCode == REC_REQUEST_CODE && intent != null) {
            val string = intent.data!!.toString()
            //判断文件是否在sd卡中
            when {
                string.indexOf(Environment.getExternalStorageDirectory().toString()) != -1 -> {
                    //对Uri进行切割
                    val a = string.split(Environment.getExternalStorageDirectory().toString().toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    //获取到file
                    file = File(Environment.getExternalStorageDirectory(), a[1])
                    tvPathName.text = String.format("文件的路径为：%s", file.absolutePath)

                    fileName = file.absolutePath.substring(file.absolutePath.lastIndexOf("/") + 1, file.absolutePath.length)
                    tvFileName.text = String.format("文件名为：%s", fileName!!)

                }
                string.indexOf(Environment.getDataDirectory().toString()) != -1 -> { //判断文件是否在手机内存中
                    //对Uri进行切割
                    val a = string.split(Environment.getDataDirectory().toString().toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    //获取到file
                    file = File(Environment.getDataDirectory(), a[1])
                    tvPathName.text = file.absolutePath
                }
                else -> {
                    //出现其他没有考虑到的情况
                    Toast.makeText(this, "文件路径解析失败！", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            Log.i(TAG, "onActivityResult" + file.absolutePath)
        }
    }

//    private fun doUpload() {
//        /**
//         *
//         * login为struts2上的action
//         */
//        val file1 = File(file.parent, fileName)
//        if (!file1.exists()) {
//            L.e(file1.absolutePath + " not exits!")
//            return
//        }
//
//        //mime type
//        val requestBody = MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file1", fileName, RequestBody.create(MediaType.parse("application/octet-stream"), file1))
//                .build()
//
//        val request = Request.Builder()
//                .url(mBaseUrl + "uploadFile")
//                .post(requestBody)
//                .build()
//
//        val call = client.newCall(request)
//
//        call.enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//                L.e("onFailure:" + e.message)
//                runOnUiThread { Toast.makeText(this@DoUploadActivity, "上传失败!", Toast.LENGTH_SHORT).show() }
//            }
//
//            @Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                L.e("onResponse: upload success!")
//                runOnUiThread { Toast.makeText(this@DoUploadActivity, "上传成功!", Toast.LENGTH_SHORT).show() }
//            }
//        })
//    }

    companion object {
        private const val REC_REQUEST_CODE = 0x0001
        private const val TAG = "FilePicker"
    }
}
