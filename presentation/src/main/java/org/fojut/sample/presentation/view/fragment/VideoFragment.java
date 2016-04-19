package org.fojut.sample.presentation.view.fragment;

import android.os.Bundle;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.view.fragment.base.BaseFragment;

/**
 * Created by fojut on 2016/4/19.
 */
public class VideoFragment extends BaseFragment {

    /**
     * Constructor
     */
    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {

    }
}
