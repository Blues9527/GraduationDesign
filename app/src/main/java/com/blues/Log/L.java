package com.blues.Log;

import android.util.Log;

/**
 * Created by Administrator on 2018/3/12.
 */

public class L {

    private static final String TAG = "okhttp";

    private static boolean debug = true;

    public static void e(String msg) {
        if (debug)
            Log.e(TAG, msg);
    }
}
