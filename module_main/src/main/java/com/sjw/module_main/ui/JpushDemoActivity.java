package com.sjw.module_main.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.sjw.module_main.R;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by pc on 2018/8/1.
 */

public class JpushDemoActivity extends InstrumentedActivity implements TagAliasCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpush);


    }

    @Override
    public void gotResult(int i, String s, Set<String> set) {
        Iterator<String> is = set.iterator();
        while (is.hasNext()) {
            Log.e("Harg2", is.next());
        }
    }

    public void jpushTest(View v) {
        //设置推送
        Set<String> tags = new TreeSet<String>();
        tags.add("33158");
//                    tags.add("33158");
        // 极光推送第五步：
        JPushInterface.setAliasAndTags(JpushDemoActivity.this
                , "hah"
                , tags,
                (TagAliasCallback) JpushDemoActivity.this);
    }


}
