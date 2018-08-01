package com.sjw.lib_common.http;


import com.sjw.lib_common.entity.Book;

import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(){
        this.mRetrofitService = RetrofitHelper.getInstance().getServer();
    }
    public  Observable<Book> getSearchBooks(String type,String page){
        return mRetrofitService.getSearchBooks(type,page);
    }
}
