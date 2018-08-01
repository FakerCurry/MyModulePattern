package com.sjw.mymodulepattern;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sjw.lib_common.base.BaseApplication;
import com.sjw.lib_common.utils.Utils;



/**
 * Created by pc on 2018/7/16.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (Utils.isAppDebug()) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            //无语了
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);



    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // dex突破65535的限制
        MultiDex.install(this);
    }


}
