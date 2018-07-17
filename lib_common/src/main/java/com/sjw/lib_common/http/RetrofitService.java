package com.sjw.lib_common.http;




import com.sjw.lib_common.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public interface RetrofitService {
    @GET("book")
    Observable<Book> getSearchBooks(@Query("type") String type);
}
