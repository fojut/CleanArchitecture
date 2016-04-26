package org.fojut.sample.presentation.view.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.internal.di.component.DaggerNewsComponent;
import org.fojut.sample.presentation.internal.di.component.NewsComponent;
import org.fojut.sample.presentation.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.model.NewsEntity;
import org.fojut.sample.presentation.presenter.NewsListPresenter;
import org.fojut.sample.presentation.view.adapter.NewsEntityAdapter;
import org.fojut.sample.presentation.view.adapter.ViewPagerAdapter;
import org.fojut.sample.presentation.view.fragment.base.BaseFragment;
import org.fojut.sample.presentation.view.widget.ProgressHUD;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by fojut on 2016/4/19.
 */
public class NewsFragment extends BaseFragment implements HasComponent<NewsComponent>,
        NewsListPresenter.View<List<NewsEntity>> {

    private static final String TAG = NewsFragment.class.getSimpleName();

    @Inject
    NewsListPresenter newsListPresenter;

    @Inject
    NewsEntityAdapter newsEntityAdapter;

    @Nullable @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Nullable @Bind(R.id.tabs)
    TabLayout tabLayout;

    private ProgressHUD mProgressHUD;

    private ViewPagerAdapter viewPagerAdapter;

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

        initViewPages();
        initTabLayout();
    }

    @Override
    public void loadData(List<NewsEntity> newsEntityList) {
        newsEntityAdapter.updateDataList(newsEntityList);
        viewPagerAdapter.updateDataAdapter();
    }

    @Override
    public void showLoading() {
        if(!ProgressHUD.isShowing(mProgressHUD)){
            mProgressHUD = ProgressHUD.show(getContext(), getString(R.string.loading_text), true, true, new DialogInterface.OnCancelListener(){
                @Override
                public void onCancel(DialogInterface dialog) {
                    mProgressHUD.dismiss();
                }
            });
        }
    }

    @Override
    public void hideLoading() {
        if(ProgressHUD.isShowing(mProgressHUD)) {
            mProgressHUD.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
        mProgressHUD.setMessage(getString(R.string.loading_failure));
    }

    @Override
    public void onResume() {
        super.onResume();
        this.newsListPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.newsListPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.newsListPresenter.onDestroy();
    }

    @Override
    public NewsComponent getComponent() {
        return DaggerNewsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).build();
    }

    @Override
    public void initViewPages() {
        if(viewPager.getAdapter() == null){
            this.newsListPresenter.loadNewsChannelList();

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
                    newsListPresenter.loadNewsList();
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
}
