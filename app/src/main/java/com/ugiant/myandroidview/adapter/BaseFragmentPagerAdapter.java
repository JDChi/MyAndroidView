package com.ugiant.myandroidview.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by JDNew on 12/22/2016.
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String tabTitles[];

    public BaseFragmentPagerAdapter(FragmentManager fm , String tabTitles[] , List<Fragment> fragmentList) {
        super(fm);
        this.tabTitles = tabTitles;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
