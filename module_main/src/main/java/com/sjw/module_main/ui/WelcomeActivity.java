package com.sjw.module_main.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;

import com.sjw.lib_common.base.BaseActivity;
import com.sjw.lib_common.base.BaseData;
import com.sjw.lib_common.base.BaseSharedPreferences;
import com.sjw.lib_common.newtools.permission.DefaultRationale;
import com.sjw.lib_common.newtools.permission.PermissionSetting;
import com.sjw.lib_common.utils.ToolsUtils;
import com.sjw.lib_common.view.CountDownView;
import com.sjw.module_main.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.util.List;

public class WelcomeActivity extends BaseActivity {
    private Context context = WelcomeActivity.this;
    private ImageView welImage;
    CountDownView cdv;
    //andpermission
    private Rationale mRationale;
    private PermissionSetting mSetting;

    @Override
    protected void initView() {
        welImage = $(R.id.wel_image);
        cdv = $(R.id.cdv);
        //设置背景图片
        ToolsUtils.setAllImageDraw(context, R.mipmap.welcome, welImage);
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);
        //读取内存权限,手机信息
        requestPermission();
    }

    @Override
    protected void judgeNet() {

    }

    @Override
    protected void setLayoutId() {
        //全屏
        fullScreen();
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        cdv.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {

            }

            @Override
            public void onFinishCount() {

//                startActivity(HomeActivity.class, true);
                startActivity(MainActivity.class, true);

            }
        });
        cdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdv.stop();
//                startActivity(HomeActivity.class, true);
                startActivity(MainActivity.class, true);
            }
        });
    }

    @Override
    protected void beforeUnbinder() {
        ToolsUtils.recycleAllImg(welImage);
        mRationale = null;
        mSetting = null;
    }

    //请求权限
    private void requestPermission() {
        AndPermission.with(context)
                .permission(Permission.WRITE_EXTERNAL_STORAGE
                        , Permission.READ_EXTERNAL_STORAGE
                        , Permission.READ_PHONE_STATE)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        getImei();
                        //获取到权限才能够进入app
                        cdv.start();
                        cdv.setEnabled(true);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {

                        if (AndPermission.hasAlwaysDeniedPermission(context, permissions)) {
                            mSetting.showSetting(permissions);
                        }
                    }
                }).start();
    }

    private void getImei() {
        //获取imei
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        BaseSharedPreferences.put(BaseData.IMEI, telephonyManager.getDeviceId());

    }

}
