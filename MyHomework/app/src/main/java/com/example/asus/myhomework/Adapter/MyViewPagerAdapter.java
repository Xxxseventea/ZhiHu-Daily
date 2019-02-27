package com.example.asus.myhomework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    List<String> titles;
    public MyViewPagerAdapter(FragmentManager fm,List<Fragment>fragments,List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
