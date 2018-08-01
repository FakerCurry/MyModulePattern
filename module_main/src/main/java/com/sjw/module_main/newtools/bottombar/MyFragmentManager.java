package com.sjw.module_main.newtools.bottombar;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.sjw.module_main.R;

import java.util.List;

/**
 * Created by pc on 2018/6/25.
 */

public class MyFragmentManager {
    private FragmentManager mFragmentManager;
    private int mContainerViewId;

    /**
     * 构造方法
     *
     * @param fragmentManager 管理类FragmentManager
     * @param containerViewId 容器布局id containerViewId
     */
    public MyFragmentManager(@Nullable FragmentManager fragmentManager, @IdRes int containerViewId) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewId = containerViewId;

    }

    /**
     * 添加fragment
     */
    public void add(Fragment fragment) {


        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.home_container, fragment);

        fragmentTransaction.commit();


    }


    /**
     * 切换显示fragment
     */
    public void switchFragment(Fragment fragment) {
        List<Fragment> childFragments = mFragmentManager.getFragments();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        //1.隐藏所有的
        for (Fragment childFragment : childFragments) {

            fragmentTransaction.hide(childFragment);

        }
        //2.如果容器里面没有就添加，否则显示
        if (!childFragments.contains(fragment)) {
            fragmentTransaction.add(R.id.home_container, fragment);
        } else {
            fragmentTransaction.show(fragment);

        }
        fragmentTransaction.commit();

    }


}
