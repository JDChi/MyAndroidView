package com.ugiant.myandroidview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ugiant.myandroidview.R;
import com.ugiant.myandroidview.adapter.BaseFragmentPagerAdapter;
import com.ugiant.myandroidview.base.BaseActivity;
import com.ugiant.myandroidview.fragment.FragmentOne;
import com.ugiant.myandroidview.fragment.FragmentThree;
import com.ugiant.myandroidview.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JDNew on 12/22/2016.
 */

public class BottomTabTabLayoutActivity extends BaseActivity {
    private ViewPager viewpager;
    private TabLayout tablayout;
    private BaseFragmentPagerAdapter baseFragmentPagerAdapter;
    private String[] titles;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tablayout);
        initView();
        initData();
        setListener();

    }

    @Override
    public void initData() {
        titles = new String[]{"我的", "广场", "消息"};
        fragmentList = new ArrayList<>();
        fragmentList.add(FragmentOne.newInstance());
        fragmentList.add(FragmentTwo.newInstance());
        fragmentList.add(FragmentThree.newInstance());

        baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), titles, fragmentList);
        viewpager.setAdapter(baseFragmentPagerAdapter);
        tablayout.setupWithViewPager(viewpager);
        final TabLayout.Tab one = tablayout.getTabAt(0);
        final TabLayout.Tab two = tablayout.getTabAt(1);
        final TabLayout.Tab three = tablayout.getTabAt(2);

        one.setIcon(R.mipmap.ic_mine_orange_24dp);

        two.setIcon(R.mipmap.ic_videocam_black_24dp);
        three.setIcon(R.mipmap.ic_message_black_24dp);


        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    one.setIcon(R.mipmap.ic_mine_orange_24dp);


                } else if (tab.getPosition() == 1) {
                    two.setIcon(R.mipmap.ic_videocam_orange_24dp);


                } else if (tab.getPosition() == 2) {
                    three.setIcon(R.mipmap.ic_message_orange_24dp);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    one.setIcon(R.mipmap.ic_mine_blcak_24dp);
                } else if (tab.getPosition() == 1) {
                    two.setIcon(R.mipmap.ic_videocam_black_24dp);
                } else if (tab.getPosition() == 2) {
                    three.setIcon(R.mipmap.ic_message_black_24dp);
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void setListener() {


    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
    }
}
