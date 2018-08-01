package com.sjw.module_main.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjw.lib_common.base.BaseActivity;
import com.sjw.lib_common.base.BaseFragmentActivity;
import com.sjw.lib_common.view.CustomTabView;
import com.sjw.module_main.R;
import com.sjw.module_main.newtools.bottombar.DataGenerator;

public class HomeActivity extends BaseFragmentActivity implements CustomTabView.OnTabCheckListener {
    private CustomTabView mCustomTabView;
    private Fragment[] mFragmensts;


    @Override
    protected void initView() {
        mCustomTabView = $(R.id.custom_tab_container);
        CustomTabView.Tab tabHome = new CustomTabView.Tab().setText("首页")
                .setColor(getResources().getColor(R.color.unSelectTabColor))
                .setCheckedColor(getResources().getColor(R.color.selectTabColor))
                .setNormalIcon(R.mipmap.tab_assistant_gray)
                .setPressedIcon(R.mipmap.tab_assistant_light);
        mCustomTabView.addTab(tabHome);
        CustomTabView.Tab tabDis = new CustomTabView.Tab().setText("发现")
                .setColor(getResources().getColor(R.color.unSelectTabColor))
                .setCheckedColor(getResources().getColor(R.color.selectTabColor))
                .setNormalIcon(R.mipmap.tab_center_gray)
                .setPressedIcon(R.mipmap.tab_center_light);
        mCustomTabView.addTab(tabDis);
        CustomTabView.Tab tabAttention = new CustomTabView.Tab().setText("管制")
                .setColor(getResources().getColor(R.color.unSelectTabColor))
                .setCheckedColor(getResources().getColor(R.color.selectTabColor))
                .setNormalIcon(R.mipmap.tab_contest_gray)
                .setPressedIcon(R.mipmap.tab_contest_light);
        mCustomTabView.addTab(tabAttention);
        CustomTabView.Tab tabProfile = new CustomTabView.Tab().setText("我的")
                .setColor(getResources().getColor(R.color.unSelectTabColor))
                .setCheckedColor(getResources().getColor(R.color.selectTabColor))
                .setNormalIcon(R.mipmap.tab_counter_gray)
                .setPressedIcon(R.mipmap.tab_counter_light);
        mCustomTabView.addTab(tabProfile);
        //设置监听
        mCustomTabView.setOnTabCheckListener(this);
        // 默认选中tab
        mCustomTabView.setCurrentItem(0);
    }

    @Override
    protected void judgeNet() {

    }

    @Override
    protected void setLayoutId() {
        setContentView(R.layout.activity_home);
        mFragmensts = DataGenerator.getFragments("CustomTabView Tab");
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void beforeUnbinder() {

    }


    @Override
    public void onTabSelected(View v, int position) {
        Log.e("zhouwei", "position:" + position);
        onTabItemSelected(position);
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
        }
    }
}
