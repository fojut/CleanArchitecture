package org.fojut.sample.presentation.view.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.internal.di.component.DaggerNewsComponent;
import org.fojut.sample.presentation.internal.di.component.NewsComponent;
import org.fojut.sample.presentation.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.model.NewsEntity;
import org.fojut.sample.presentation.presenter.NewsListPresenter;
import org.fojut.sample.presentation.view.adapter.NewsEntityAdapter;
import org.fojut.sample.presentation.view.adapter.ViewPagerAdapter;
import org.fojut.sample.presentation.view.fragment.base.BaseFragment;
import org.fojut.sample.presentation.view.render.RenderView;
import org.fojut.sample.presentation.view.widget.ProgressHUD;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by fojut on 2016/4/19.
 */
public class NewsFragment extends BaseFragment implements HasComponent<NewsComponent>,
        RenderView<List<NewsEntity>> {

    private static final String TAG = NewsFragment.class.getSimpleName();

    @Inject
    NewsListPresenter newsListPresenter;

    @Inject
    NewsEntityAdapter newsEntityAdapter;

    @Nullable @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Nullable @Bind(R.id.tabs)
    TabLayout tabLayout;

    private int selectedPosition = 0;

    private ProgressHUD mProgressHUD;

    private List<View> viewList;
    private List<String> titleList;
    private ViewPagerAdapter viewPagerAdapter;
    private int newsNum = 20;
    private int newsPage = 1;

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
        this.newsListPresenter.setRenderView(this);

        if(viewPager.getAdapter() == null){
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_news, null);
            View view2 = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_news, null);
            View view3 = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_news, null);
            View view4 = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_news, null);

            viewList = new ArrayList<>();
            viewList.add(view1);
            viewList.add(view2);
            viewList.add(view3);
            viewList.add(view4);

            titleList = new ArrayList<>();
            titleList.add("社会");
            titleList.add("体育");
            titleList.add("娱乐");
            titleList.add("科技");

            viewPagerAdapter = new ViewPagerAdapter(getContext(), viewList, titleList, newsEntityAdapter);
            viewPager.setAdapter(viewPagerAdapter);
        }

        viewPager.setCurrentItem(selectedPosition);
        loadDataByPosition(selectedPosition);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setScrollPosition(0, 0, true);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(selectedPosition != tab.getPosition()){
                    selectedPosition = tab.getPosition();
                    if(viewPager.getCurrentItem() != tab.getPosition()){
                        viewPager.setCurrentItem(tab.getPosition());
                    }
                }
                loadDataByPosition(selectedPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void initData(String type, String path, int num, int page){
        this.newsListPresenter.loadNewsList(type, path, num, page);
    }

    @Override
    public void renderView(List<NewsEntity> newsEntityList) {
        newsEntityAdapter.updateDataList(newsEntityList);
        viewPagerAdapter.updateDataAdapter();
    }

    @Override
    public void showLoading() {
        mProgressHUD = ProgressHUD.show(getContext(), getString(R.string.loading_text), true, true, new DialogInterface.OnCancelListener(){
            @Override
            public void onCancel(DialogInterface dialog) {
                mProgressHUD.dismiss();
            }
        });
    }

    @Override
    public void hideLoading() {
        mProgressHUD.dismiss();
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

    /**
     * Load data by selected position
     * @param position
     */
    private void loadDataByPosition(int position){
        switch (position){
            case 0:     //社会
                initData("social", "social", newsNum, newsPage);
                break;
            case 1:     //体育
                initData("tiyu", "tiyu", newsNum, newsPage);
                break;
            case 2:     //娱乐
                initData("huabian", "newtop", newsNum, newsPage);
                break;
            case 3:     //科技
                initData("keji", "keji", newsNum, newsPage);
                break;
        }
    }
}
