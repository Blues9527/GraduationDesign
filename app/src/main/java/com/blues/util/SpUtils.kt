package com.blues.util

import android.content.Context

class SpUtils {

    companion object {
        //用户资料SP存储文件
        private const val SP_FILE_NAME: String = "user_data"

        /**
         * save String
         */
        fun saveStr(ctx: Context, key: String, value: String?) {
            ctx.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .run {
                        putString(key, value)
                        apply()
                    }
        }

        /**
         * get String
         */
        fun getStr(ctx: Context, key: String): String? {
            return ctx.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
                    .getString(key, "")
        }

        /**
         *  save Int
         */
        fun saveInt(ctx: Context, key: String, value: Int) {
            ctx.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .run {
                        putInt(key, value)
                        apply()
                    }
        }

        /**
         * get Int
         */
        fun getInt(ctx: Context, key: String): Int {
            return ctx.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
                    .getInt(key, 0)
        }

        /**
         *  save Boolean
         */
        fun saveBoolean(ctx: Context, key: String, value: Boolean) {
            ctx.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .run {
                        putBoolean(key, value)
                        apply()
                    }
        }

        /**
         * get Boolean
         */
        fun getBoolean(ctx: Context, key: String): Boolean {
            return ctx.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
                    .getBoolean(key, false)
        }
    }
}