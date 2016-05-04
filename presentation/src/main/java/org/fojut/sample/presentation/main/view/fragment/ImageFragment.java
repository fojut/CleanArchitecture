package org.fojut.sample.presentation.main.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.base.view.fragment.BaseFragment;
import org.fojut.sample.presentation.image.internal.di.component.DaggerImageComponent;
import org.fojut.sample.presentation.image.internal.di.component.ImageComponent;
import org.fojut.sample.presentation.image.model.ImageEntity;
import org.fojut.sample.presentation.image.presenter.ImageListPresenter;
import org.fojut.sample.presentation.image.view.adapter.ImageAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by fojut on 2016/4/19.
 */
public class ImageFragment extends BaseFragment implements HasComponent<ImageComponent>,
        ImageListPresenter.View<List<ImageEntity>>, SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = ImageFragment.class.getSimpleName();

    @Inject
    ImageListPresenter imageListPresenter;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @Inject
    ImageAdapter imageAdapter;

    private LinearLayoutManager mLayoutManager;


    /**
     * Constructor
     */
    public static ImageFragment newInstance() {
        ImageFragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image;
    }

    @Override
    protected void initView() {
        getComponent().inject(this);
        imageListPresenter.setView(this);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        imageAdapter = new ImageAdapter(context());
        mRecyclerView.setAdapter(imageAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        onRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.imageListPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.imageListPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.imageListPresenter.onDestroy();
    }

    @Override
    public ImageComponent getComponent() {
        return DaggerImageComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).build();
    }

    @Override
    public void loadData(List<ImageEntity> imageEntityList) {
        imageAdapter.updateDataList(imageEntityList);
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
        Toast.makeText(context(), R.string.loading_failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        imageListPresenter.loadImageList();
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == imageAdapter.getItemCount() ) {
                //加载更多
                Toast.makeText(context(), R.string.loading_more, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
