package com.sjw.module_list.mvp.view;

import com.sjw.lib_common.entity.Book;

/**
 * Created by pc on 2018/7/18.
 */

public interface BookView extends View{
    //请求成功
    void onSuccess(Book mBook);
    //请求失败
    void onError(String result);



}
