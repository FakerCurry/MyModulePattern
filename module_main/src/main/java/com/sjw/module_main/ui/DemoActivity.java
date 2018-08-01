package com.sjw.module_main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.luoshihai.xxdialog.DialogViewHolder;
import com.luoshihai.xxdialog.XXDialog;
import com.sjw.lib_common.entity.User;
import com.sjw.lib_common.greendaoopr.DaoManager;
import com.sjw.lib_common.utils.ToastUtils;
import com.sjw.module_main.R;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by pc on 2018/8/1.
 */

public class DemoActivity extends InstrumentedActivity implements TagAliasCallback {

    private Intent getIn;
    private String needValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpush);
        getIn = getIntent();
        if (getIn != null) {
            needValue = getIn.getStringExtra("needValue");
            ToastUtils.showLongToast(needValue);
        }
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
        JPushInterface.setAliasAndTags(DemoActivity.this
                , "hah"
                , tags,
                (TagAliasCallback) DemoActivity.this);
    }

    public void xxDialog(View v) {

        XXDialog xxDialog = new XXDialog(this, R.layout.demo_dialog) {
            @Override
            public void convert(DialogViewHolder holder) {
                holder.setOnClick(R.id.tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });


            }
        }.fromTop().fullWidth().showDialog().setCanceledOnTouchOutside(true);

    }


    public void greendao(View v) {
        DaoManager.getInstance().deleteAllUser();
        DaoManager.getInstance().insertUser(new User(null, "我好", 11, 99, "22"));
        List<User> users = DaoManager.getInstance().searchAllUser();
        String rst = "";
        for (int i = 0; i < users.size(); i++) {
            rst += users.toString();

        }
        ToastUtils.showLongToast(rst);
    }


}
