package com.qianfeng.mymisafe.welcome.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by asus on 2016/7/1.
 */

public final  class MyAdapter extends PagerAdapter {
    private List<View> ds;
    private ViewPager vp;
    public MyAdapter(){

    }
    public MyAdapter(List<View> ds, ViewPager vp){
        this.ds=ds;
        this.vp=vp;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=ds.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        vp.removeView((View)object);
    }
}
