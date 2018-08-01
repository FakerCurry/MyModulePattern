package com.sjw.module_main.newtools.bottombar;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjw.module_main.R;
import com.sjw.module_main.ui.F1Fragment;
import com.sjw.module_main.ui.F2Fragment;
import com.sjw.module_main.ui.F3Fragment;
import com.sjw.module_main.ui.F4Fragment;


/**
 * Created by Administrator on 2018/6/24.
 */

public class DataGenerator {

    public static final int []mTabRes = new int[]{R.mipmap.tab_assistant_gray,R.mipmap.tab_center_gray,R.mipmap.tab_contest_gray,R.mipmap.tab_counter_gray};
    public static final int []mTabResPressed = new int[]{R.mipmap.tab_assistant_light,R.mipmap.tab_center_light,R.mipmap.tab_contest_light,R.mipmap.tab_counter_light};
    public static final String []mTabTitle = new String[]{"首页","发现","关注","我的"};

    public static Fragment[] getFragments(String from){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = new F1Fragment();
        fragments[1] = new F2Fragment();
        fragments[2] = new F3Fragment();
        fragments[3] = new F4Fragment();
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
