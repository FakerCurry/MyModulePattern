package com.sjw.lib_common.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by admin on 2018/4/14.
 */

public class CommonUtils {

    //是否打印log
    public static boolean isPrint = true;

    /**
     * 吐司，时间长
     */
    public static void toastLong(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();

    }

    /**
     * 吐司，时间短
     */
    public static void toastShort(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }

    /**
     * 打印log
     */

    public static void printLog(String tag, String text) {
        if (isPrint) {
            Log.i(tag, text);

        } else {


        }

    }

}
