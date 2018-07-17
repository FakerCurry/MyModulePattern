package com.sjw.lib_common.entity;

import java.util.List;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class Book {

    /**
     * code : 0
     * msg : 成功
     * data : [{"bookName":"将夜","bookPrice":"20","booknum":50},{"bookName":"遮天","bookPrice":"300","booknum":10},{"bookName":"完美世界","bookPrice":"120","booknum":70}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "Book{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bookName : 将夜
         * bookPrice : 20
         * booknum : 50
         */

        private String bookName;
        private String bookPrice;
        private int booknum;

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getBookPrice() {
            return bookPrice;
        }

        public void setBookPrice(String bookPrice) {
            this.bookPrice = bookPrice;
        }

        public int getBooknum() {
            return booknum;
        }

        public void setBooknum(int booknum) {
            this.booknum = booknum;
        }
    }
}
