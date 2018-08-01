package com.sjw.module_main.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sjw.lib_common.base.BaseFragment;
import com.sjw.module_main.R;


/**
 * Created by donglinghao on 2016-01-28.
 */
public class F4Fragment extends BaseFragment {

    private View mRootView;


    @Override
    protected View setLayoutId(LayoutInflater inflater, ViewGroup container) {
        if (mRootView == null) {
            Logger.e("F1Fragment");
            mRootView = inflater.inflate(R.layout.fragment_f4, container, false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        Logger.i("f4");
    }

    @Override
    protected void judgeNet() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyLoad() {

    }

}
