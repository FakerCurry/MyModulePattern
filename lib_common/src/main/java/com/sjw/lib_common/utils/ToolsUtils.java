package com.sjw.lib_common.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by admin on 2018/3/7.
 */

public class ToolsUtils {
    //是否打印log
    public static boolean isPrint = true;



    /**
     * 打印log
     */

    public static void printLog(String tag, String text) {
        if (isPrint) {
            Log.i(tag, text);

        } else {


        }

    }


    public static void gotoSetNet(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("设置网络")            //
                .setMessage("当前无网络").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 跳转到系统的网络设置界面
                Intent intent = null;
                // 先判断当前系统版本
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(Settings.ACTION_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);

            }
        }).setNegativeButton("知道了", null).show();


    }




    /**
     * 资源文件的设置到imageview
     *
     * @param context
     * @param imgResouce
     * @param img
     */
    public static void setAllImageDraw(Context context, int imgResouce, View img) {
        // 设置背景图片
        Bitmap bm = null;

        // 判断是否内存溢出
        try {
            bm = BitmapFactory.decodeResource(context.getResources(), imgResouce);
        } catch (OutOfMemoryError oom) {
            printLog("getSDCard", "OutOfMemoryError");
            System.gc();
            System.runFinalization();
        } finally {

        }
        BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);
        img.setBackgroundDrawable(bd);

    }

    /**
     * 释放图片资源Linerlayout
     *
     * @param img
     */
    public static void recycleAllImg(View img) {
        // 销毁图片
        BitmapDrawable bd = (BitmapDrawable) img.getBackground();
        img.setBackgroundResource(0);// 别忘了把背景设为null，避免onDraw刷新背景时候出现used a
        // recycled bitmap错误
        if (bd != null) {
            if (bd.getBitmap() != null) {
                bd.setCallback(null);
                bd.getBitmap().recycle();
            }

        }

    }






    /**
     * 判断手机号是否符合规范 * @param phoneNo 输入的手机号 * @return
     */
    public static boolean isPhoneNumber(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo)) {
            return false;
        }
        if (phoneNo.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (!PhoneNumberUtils.isISODigit(phoneNo.charAt(i))) {
                    return false;
                }
            }
            Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" + "|(14[5,7])" + "|(15[^4,\\D])" + "|(17[3,6-8])" + "|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
        return false;
    }


    /**
     * 获取类似message code结构的json对象
     *
     * @param result
     * @return
     * @throws JSONException
     */
    public static String getobject(String result, String objectName) {
        String resultFlag = "";
        try {
            JSONObject jsonObject = new JSONObject(result);
            // JSONObject baseModel = jsonObject.getJSONObject("baseModel");
            resultFlag = jsonObject.getString(objectName);
            return resultFlag;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultFlag;
    }




    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }


}
