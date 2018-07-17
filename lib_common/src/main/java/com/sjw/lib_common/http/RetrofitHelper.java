package com.sjw.lib_common.http;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.sjw.lib_common.base.BaseData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class RetrofitHelper {


    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public static RetrofitHelper getInstance(){
        if (instance == null){
            instance = new RetrofitHelper();
        }
        return instance;
    }
    private RetrofitHelper(){
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
//        http://www.tp5.com/admin/testandroid/book?type=0
                .baseUrl(BaseData.MvpURL)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(ResponseConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }



    private static OkHttpClient getOkHttpClient() {

        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
//                AllCanuseUtils.showLog("sjw", message);
                Log.i("loggingInterceptorurl", message);
            }
        });
        loggingInterceptor.setLevel(level);

        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder().connectTimeout(20, TimeUnit.SECONDS);

//        //设置cookie
//        httpClientBuilder.interceptors().add(new ReadCookiesInterceptor());
//        httpClientBuilder.interceptors().add(new SaveCookiesInterceptor());
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();


    }
}
