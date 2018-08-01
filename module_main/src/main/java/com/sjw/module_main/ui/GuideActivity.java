package com.sjw.module_main.ui;

import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.sjw.lib_common.base.BaseActivity;
import com.sjw.module_main.R;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import cn.bingoogolapple.bgabanner.transformer.TransitionEffect;

public class GuideActivity extends BaseActivity {
    private BGABanner mBackgroundBanner;


    @Override
    protected void initView() {
        mBackgroundBanner = $(R.id.banner_guide_background);
    }

    @Override
    protected void judgeNet() {

    }

    @Override
    protected void setLayoutId() {
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_guide);
    }

    @Override
    protected void initData() {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth m  inHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        // 设置数据源
        mBackgroundBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.uoko_guide_background_1,
                R.mipmap.uoko_guide_background_2,
                R.mipmap.uoko_guide_background_3);
        mBackgroundBanner.setTransitionEffect(TransitionEffect.Cube);
        //网络图片
//        mBackgroundBanner.setAdapter(GuideActivity.this);

    }

    @Override
    protected void setListener() {
/**
 * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
 * 如果进入按钮和跳过按钮有一个不存在的话就传 0
 * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
 * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
 */
        mBackgroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
//                //bugly测试
//                CrashReport.testJavaCrash();
                startActivity(new Intent(GuideActivity.this, WelcomeActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void beforeUnbinder() {

    }


}
