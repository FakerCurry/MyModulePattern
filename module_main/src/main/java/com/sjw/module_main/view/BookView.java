package com.sjw.module_main.view;


import com.sjw.lib_common.entity.Book;

/**
 * Created by win764-1 on 2016/12/12.
 */

public interface BookView extends View {
    void onSuccess(Book mBook);
    void onError(String result);
}
