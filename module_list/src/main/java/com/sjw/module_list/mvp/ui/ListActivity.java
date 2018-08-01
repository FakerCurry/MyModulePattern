package com.sjw.module_list.mvp.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.andview.refreshview.XRefreshView;
import com.bumptech.glide.Glide;
import com.sjw.lib_common.base.BaseActivity;
import com.sjw.lib_common.base.BaseIntData;
import com.sjw.lib_common.entity.Book;
import com.sjw.lib_common.glide.ImageUtils;
import com.sjw.lib_common.utils.ToastUtils;
import com.sjw.module_list.R;
import com.sjw.module_list.adapter.BookAdapter;
import com.sjw.module_list.mvp.presenter.BookPresenter;
import com.sjw.module_list.mvp.view.BookView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

@Route(path = "/app/module_list")
public class ListActivity extends BaseActivity {
    private Context context = ListActivity.this;
    RecyclerView recyclerView;
    BookAdapter adapter;
    List<Book.DataBean> bookList = new ArrayList<Book.DataBean>();
    XRefreshView xRefreshView;
    int lastVisibleItem = 0;
    GridLayoutManager layoutManager;
    private boolean isBottom = false;
    private int mLoadCount = 0;

    private View headerView;
    private int page_size=10;

    private int currentPage = 1;
    private BookPresenter mBookPresenter = new BookPresenter();

    private BGABanner mContentBanner;

    private List<String> imgList;
    private List<String> titlelist;
    @Override
    protected void initView() {
        xRefreshView = (XRefreshView) findViewById(R.id.xrefreshview);
        xRefreshView.setPullLoadEnable(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_test_rv);
        recyclerView.setHasFixedSize(true);
        adapter = new BookAdapter(bookList, this);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        headerView = adapter.setHeaderView(R.layout.bannerview, recyclerView);
        recyclerView.setAdapter(adapter);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        // 设置静默加载模式
        xRefreshView.setSilenceLoadMore(true);
//设置静默加载时提前加载的item个数
        xRefreshView.setPreLoadCount(4);


        //轮播图
        mContentBanner=(BGABanner)headerView.findViewById(R.id.banner_guide_content);

    }


    @Override
    protected void judgeNet() {

    }

    @Override
    protected void setLayoutId() {
        setContentView(R.layout.activity_list);
        EventBus.getDefault().register(this);
        mBookPresenter=new BookPresenter();
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
    }

    @Override
    protected void initData() {
        //第一次加载数据
        currentPage = 1;
        mBookPresenter.getSearchBooks("0", currentPage + "");
        //初始化轮播图
        initBanner();
    }

    private void initBanner() {

        imgList = new ArrayList<>();
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        titlelist= new ArrayList<>();
        titlelist.add("十大星级品牌联盟，全场2折起");
        titlelist.add("51巅峰钜惠");
        titlelist.add("生命不是要超越别人，而是要超越自己。");
        titlelist.add("己所不欲，勿施于人。——孔子");
        titlelist.add("嗨购5折不要停");


        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(context)
                        .load(model)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });

        mContentBanner.setData(imgList, titlelist);
    }

    private void initRecyclerView() {
    }

    @Override
    protected void setListener() {


        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        currentPage = 1;
                        mBookPresenter.getSearchBooks("0", currentPage + "");
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                currentPage ++;
                mBookPresenter.getSearchBooks("0", currentPage + "");
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            if (currentPage == 1) {
                bookList = mBook.getData();
                xRefreshView.stopRefresh();
                adapter.setData(bookList);
            } else {
                if (mBook.getData() != null) {
                    if (mBook.getData().size() != 0) {
                        if (mBook.getData().size()<page_size){
                            bookList.addAll(mBook.getData());
                            adapter.setData(bookList);
                            xRefreshView.setLoadComplete(true);
                        }else {
                            bookList.addAll(mBook.getData());
                            adapter.setData(bookList);
                            xRefreshView.stopLoadMore();

                        }

                    }
                }


            }

        }

        @Override
        public void onError(String result) {
            ToastUtils.showLongToast(result);
        }
    };

    @Override
    protected void beforeUnbinder() {

    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Message message) {
        if (message.what == BaseIntData.TEST) {
            ToastUtils.showLongToast("list页面" + (String) message.obj);
        }
    }

}
