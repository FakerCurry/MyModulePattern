package com.sjw.module_list.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;


import com.sjw.lib_common.entity.Book;
import com.sjw.lib_common.utils.DensityUtil;
import com.sjw.module_list.R;

public class BookAdapter extends BaseRecyclerAdapter<BookAdapter.SimpleAdapterViewHolder> {
    private List<Book.DataBean> list;
    private int largeCardHeight, smallCardHeight;

    public BookAdapter(List<Book.DataBean> list, Context context) {
        this.list = list;
        largeCardHeight = DensityUtil.dip2px(context, 150);
        smallCardHeight = DensityUtil.dip2px(context, 100);
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        Book.DataBean book = list.get(position);
        holder.nameTv.setText(book.getBookName());
        holder.ageTv.setText("价格"+book.getBookPrice());
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            holder.rootView.getLayoutParams().height = position % 2 != 0 ? largeCardHeight : smallCardHeight;
        }
    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    public void setData(List<Book.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_recylerview, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    public void insert(Book.DataBean book, int position) {
        insert(list, book, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public TextView nameTv;
        public TextView ageTv;
        public int position;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                nameTv = (TextView) itemView
                        .findViewById(R.id.recycler_view_test_item_person_name_tv);
                ageTv = (TextView) itemView
                        .findViewById(R.id.recycler_view_test_item_person_age_tv);
                rootView = itemView
                        .findViewById(R.id.card_view);
            }

        }
    }

    public Book.DataBean getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

}
