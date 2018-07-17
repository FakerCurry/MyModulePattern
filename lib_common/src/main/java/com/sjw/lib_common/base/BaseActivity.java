package com.sjw.lib_common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by admin on 2018/4/14.
 */

public abstract class BaseActivity extends Activity {
//    //定义并且解除绑定控件
//    private Unbinder unbinder;
    //获取登录的标志
    //0是用户登录 1是个人登录
    private String flag;

//    private MyActionBar actionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewManager.getInstance().addActivity(this);
        //1.设置布局
        setLayoutId();
////        绑定butterknife
//        unbinder = ButterKnife.bind(this);

//        //注册EventBus
//        EventBus.getDefault().register(this);
        //初始化控件
        initView();
        //初始化数据
        initData();
        //判断是否有网络
        judgeNet();
        //设置监听
        setListener();

    }


    /**
     * 封装的findViewByID方法
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }


    /**
     * 判断网络
     *
     * @return
     */
    protected abstract void initView();

    /**
     * 判断网络
     *
     * @return
     */
    protected abstract void judgeNet();

    /**
     * 设置布局
     */
    protected abstract void setLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 监听方法
     */
    protected abstract void setListener();


    /**
     * 界面销毁的时候
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁控件之前的操作
        beforeUnbinder();
//        unbinder.unbind();
//        //取消EventBus
//        EventBus.getDefault().unregister(this);
    }

    protected abstract void beforeUnbinder();

    /**
     * 启动一个Activity
     * * @param activity 需要启动的Activity的Class
     * * @param needFinish true表示有finish
     */
    public void startActivity(Class<? extends Activity> activity, boolean needFinish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (needFinish) {
            finish();
        } else {

        }
    }

    /**
     * 启动一个Activity
     *
     * @param activity   需要启动的Activity的Class
     * @param bundle     需要穿的值
     * @param needFinish true表示有finish
     */
    public void startActivity(Class<? extends Activity> activity, Bundle bundle, boolean needFinish) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);
        startActivity(intent);
        if (needFinish) {
            finish();
        } else {

        }
    }

    /**
     * 启动一个Activity,并且有返回
     * * @param activity 需要启动的Activity的Class
     * * @param requestCode 请求code
     *
     * @param needFinish true表示有finish
     */
    public void startActivity(Class<? extends Activity> activity, int requestCode, boolean needFinish) {
        Intent intent = new Intent(this, activity);
        startActivityForResult(intent, requestCode);
        if (needFinish) {
            finish();
        } else {

        }
    }

    /**
     * 启动一个Activity,并且有返回
     * * @param activity 需要启动的Activity的Class
     * * @param bundle 需要传的值
     * * @param requestCode 请求code
     *
     * @param needFinish true表示有finish
     */
    public void startActivity(Class<? extends Activity> activity, Bundle bundle, int requestCode, boolean needFinish) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
        if (needFinish) {
            finish();
        } else {

        }
    }

    /**
     * 全屏
     */
    public void fullScreen() {

        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

//    /**
//     * //初始化loading
//     */
//    public void initLoading(SpinKitView spinKit) {
//
//        //初始化loading
//        Sprite drawable = new Circle();
//        drawable.setColor(ContextCompat.getColor(this, R.color.app_main_color));
//        spinKit.setIndeterminateDrawable(drawable);
//    }


//    /**
//     * 显示或者隐藏
//     *
//     * @param showType
//     */
//
//    //0显示loading 1是没有网络 2没有数据 3是成功显示数据recycleview  4.都不显示
//    public void showWhatView(int showType, View loading, StateLayout stateLayout, View hasDataview) {
//        if (showType == 0) {
//            loading.setVisibility(View.VISIBLE);
//            stateLayout.setVisibility(View.GONE);
//            hasDataview.setVisibility(View.GONE);
//        } else if (showType == 1) {
//            loading.setVisibility(View.GONE);
//            hasDataview.setVisibility(View.GONE);
//            stateLayout.setVisibility(View.VISIBLE);
//            stateLayout.showNoNetworkView();
//        } else if (showType == 2) {
//            loading.setVisibility(View.GONE);
//            hasDataview.setVisibility(View.GONE);
//            stateLayout.setVisibility(View.VISIBLE);
//            stateLayout.showEmptyView();
//        } else if (showType == 3) {
//            loading.setVisibility(View.GONE);
//            hasDataview.setVisibility(View.VISIBLE);
//            stateLayout.setVisibility(View.GONE);
//        } else if (showType == 4) {
//            loading.setVisibility(View.GONE);
//            hasDataview.setVisibility(View.GONE);
//            stateLayout.setVisibility(View.GONE);
//        }
//    }


//    /**
//     * 设置ActionBar
//     *
//     * @param strTitle
//     * @param resIdLeft
//     * @param strLeft
//     * @param resIdRight
//     * @param strRight
//     * @param listener
//     */
//    protected void setMyActionBar(String strTitle, int resIdLeft, String strLeft, int resIdRight, String strRight, final ActionBarClickListener listener) {
//        actionBar = (MyActionBar) findViewById(R.id.appbar);
//        actionBar.setData(strTitle, resIdLeft, strLeft, resIdRight, strRight, listener);
//    }


}
