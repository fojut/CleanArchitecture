package org.fojut.sample.presentation.main.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.news.internal.di.component.DaggerNewsComponent;
import org.fojut.sample.presentation.news.internal.di.component.NewsComponent;
import org.fojut.sample.presentation.base.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.news.model.NewsChannelEntity;
import org.fojut.sample.presentation.news.model.NewsEntity;
import org.fojut.sample.presentation.news.presenter.NewsChannelPresenter;
import org.fojut.sample.presentation.news.presenter.NewsListPresenter;
import org.fojut.sample.presentation.news.view.adapter.NewsEntityAdapter;
import org.fojut.sample.presentation.main.view.adapter.ViewPagerAdapter;
import org.fojut.sample.presentation.base.view.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by fojut on 2016/4/19.
 */
public class NewsFragment extends BaseFragment implements HasComponent<NewsComponent>,
        NewsListPresenter.View<List<NewsEntity>>, NewsChannelPresenter.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = NewsFragment.class.getSimpleName();

    @Inject
    NewsListPresenter newsListPresenter;
    @Inject
    NewsChannelPresenter newsChannelPresenter;

    @Inject
    NewsEntityAdapter newsEntityAdapter;

    @Nullable @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Nullable @Bind(R.id.tabs)
    TabLayout tabLayout;

    private ViewPagerAdapter viewPagerAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

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
        getComponent().inject(this);
        this.newsListPresenter.setView(this);
        this.newsChannelPresenter.setView(this);

        this.newsChannelPresenter.getNewsChannels();
    }

    @Override
    public void loadData(List<NewsEntity> newsEntityList) {
        newsEntityAdapter.updateDataList(newsEntityList);
        viewPagerAdapter.updateDataAdapter();
    }

    @Override
    public void showLoading() {
        if(mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if(mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
        Toast.makeText(context(), R.string.loading_failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.newsChannelPresenter.onResume();
        this.newsListPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.newsChannelPresenter.onPause();
        this.newsListPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.newsChannelPresenter.onDestroy();
        this.newsListPresenter.onDestroy();
    }

    @Override
    public NewsComponent getComponent() {
        return DaggerNewsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).build();
    }

    @Override
    public int getViewPagerLayoutId() {
        return R.layout.viewpager_news;
    }

    @Override
    public void initViewPages(List<NewsChannelEntity> newsChannels) {
        if(viewPager.getAdapter() == null){
            this.newsListPresenter.loadNewsChannelList(newsChannels);

            viewPagerAdapter = new ViewPagerAdapter(context(), this.newsListPresenter.getNewsChannelViewList(),
                    this.newsListPresenter.getNewsChannelTitleList(), newsEntityAdapter);
            viewPager.setAdapter(viewPagerAdapter);
        }

        viewPager.setCurrentItem(this.newsListPresenter.getSelectedPosition());
    }

    @Override
    public void initTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setScrollPosition(0, 0, true);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(newsListPresenter.getSelectedPosition() != tab.getPosition()){
                    newsListPresenter.setSelectedPosition(tab.getPosition());
                    if(viewPager.getCurrentItem() != tab.getPosition()){
                        viewPager.setCurrentItem(tab.getPosition());
                    }
                    onRefresh();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void resetSwipeRefreshLayout() {
        mSwipeRefreshLayout = viewPagerAdapter.getItemSwipeRefreshLayoutByPosition(viewPager.getCurrentItem());
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void loadChannels(List<NewsChannelEntity> newsChannels) {
        initViewPages(newsChannels);
        initTabLayout();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        newsListPresenter.loadNewsList();
    }

}
