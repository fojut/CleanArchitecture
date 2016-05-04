package org.fojut.sample.presentation.main.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.image.view.adapter.ImageAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fojut on 2016/4/19.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private static final String TAG = ViewPagerAdapter.class.getSimpleName();

    private Context context;
    private List<View> viewList;
    private List<String> titleList;
    private RecyclerView.Adapter dataAdapter;

    public ViewPagerAdapter(Context context, List<View> viewList, List<String> titleList, RecyclerView.Adapter dataAdapter) {
        this.context = context;
        this.viewList = viewList;
        this.titleList = titleList;
        this.dataAdapter = dataAdapter;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
//        viewHolder.mSwipeRefreshLayout.setOnRefreshListener(this);

        viewHolder.mRecyclerView.setHasFixedSize(true);
        viewHolder.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        viewHolder.mRecyclerView.setAdapter(dataAdapter);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public SwipeRefreshLayout getItemSwipeRefreshLayoutByPosition(int position){
        View view = viewList.get(position);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder.mSwipeRefreshLayout;
    }

    public void updateDataAdapter(){
        dataAdapter.notifyDataSetChanged();
    }

    public class ViewHolder{
        @Bind(R.id.swipe_refresh_layout)
        SwipeRefreshLayout mSwipeRefreshLayout;
        @Bind(R.id.rv_news)
        RecyclerView mRecyclerView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
