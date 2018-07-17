package com.sjw.module_main.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sjw.lib_common.base.BaseActivity;
import com.sjw.lib_common.base.BaseIntData;
import com.sjw.lib_common.entity.Book;
import com.sjw.lib_common.utils.ToastUtils;
import com.sjw.module_main.R;
import com.sjw.module_main.presenter.BookPresenter;
import com.sjw.module_main.view.BookView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends BaseActivity {

    private TextView text;
    private Button button;
    private BookPresenter mBookPresenter = new BookPresenter();


    @Override
    protected void initView() {
        text = $(R.id.text);
        button = $(R.id.button);
    }

    @Override
    protected void judgeNet() {

    }

    @Override
    protected void setLayoutId() {
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
    }

    @Override
    protected void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookPresenter.getSearchBooks("0");
            }
        });

    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            text.setText(mBook.toString());
            Message message = new Message();
            message.what = BaseIntData.TEST;
            message.obj = mBook.getData().get(0).getBookName();
            EventBus.getDefault().post(message);
//            startActivity(ListActivity.class,false);
            //跳转到ListActivity
            ARouter.getInstance().build("/app/module_list").navigation();
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookPresenter.onStop();
    }

    @Override
    protected void beforeUnbinder() {
        EventBus.getDefault().unregister(this);
    }


    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Message message) {
//        if (message.what == BaseIntData.TEST) {
//            ToastUtils.showLongToast((String) message.obj);
//        }
    }

}
