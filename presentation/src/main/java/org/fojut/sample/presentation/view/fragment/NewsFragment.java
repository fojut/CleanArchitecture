package org.fojut.sample.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.view.adapter.ViewPagerAdapter;
import org.fojut.sample.presentation.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by fojut on 2016/4/19.
 */
public class NewsFragment extends BaseFragment {

    private static final String TAG = NewsFragment.class.getSimpleName();

    @Nullable
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Nullable
    @Bind(R.id.tabs)
    TabLayout tabLayout;

    /**
     * Constructor
     */
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        if(viewPager.getAdapter() == null){
            View view1 = LayoutInflater.from(getApplicationComponent().context()).inflate(R.layout.viewpager_news, null);
            View view2 = LayoutInflater.from(getApplicationComponent().context()).inflate(R.layout.viewpager_news, null);
            View view3 = LayoutInflater.from(getApplicationComponent().context()).inflate(R.layout.viewpager_news, null);
            View view4 = LayoutInflater.from(getApplicationComponent().context()).inflate(R.layout.viewpager_news, null);

            List<View> viewList = new ArrayList<>();
            viewList.add(view1);
            viewList.add(view2);
            viewList.add(view3);
            viewList.add(view4);

            List<String> titleList = new ArrayList<>();
            titleList.add("热点");
            titleList.add("体育");
            titleList.add("娱乐");
            titleList.add("科技");

            viewPager.setAdapter(new ViewPagerAdapter(viewList, titleList));
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "--------changed:" + arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d(TAG, "-------scrolled arg0:" + arg0);
                Log.d(TAG, "-------scrolled arg1:" + arg1);
                Log.d(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.d(TAG, "------selected:" + arg0);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setScrollPosition(0, 0, true);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
