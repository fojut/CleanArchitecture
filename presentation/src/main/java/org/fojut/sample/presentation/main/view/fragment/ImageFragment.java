package org.fojut.sample.presentation.main.view.fragment;

import android.os.Bundle;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.view.fragment.BaseFragment;

/**
 * Created by fojut on 2016/4/19.
 */
public class ImageFragment extends BaseFragment {

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

    }
}
