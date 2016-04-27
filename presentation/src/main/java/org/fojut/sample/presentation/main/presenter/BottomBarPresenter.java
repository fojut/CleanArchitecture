package org.fojut.sample.presentation.main.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.constant.FragmentConstants;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.base.presenter.BasePresenter;
import org.fojut.sample.presentation.main.view.fragment.ImageFragment;
import org.fojut.sample.presentation.main.view.fragment.NewsFragment;
import org.fojut.sample.presentation.main.view.fragment.SettingFragment;
import org.fojut.sample.presentation.main.view.fragment.VideoFragment;
import org.fojut.sample.presentation.base.view.render.LoadView;

import javax.inject.Inject;

/**
 * Created by fojut on 2016/4/26.
 */
@PerActivity
public class BottomBarPresenter extends BasePresenter<BottomBarPresenter.View> {

    private static final String TAG = BottomBarPresenter.class.getSimpleName();

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    public static String currentFragmentTag = null;

    @Inject
    public BottomBarPresenter() {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    /**
     * Init Activity Fragment
     * @param fragmentManager
     */
    public void initActivityFragment(FragmentManager fragmentManager){
        initSupportFragmentManager(fragmentManager);
        setDefaultFirstFragment();
    }

    /**
     * Init Support Fragment Manager
     * @param fragmentManager
     */
    private void initSupportFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    /**
     * Set default first fragment.
     */
    public void setDefaultFirstFragment(){
        Log.i(TAG, "setDefaultFirstFragment start... currentFragment = " + this.currentFragmentTag);
        setFragmentSelection(FragmentConstants.FRAGMENT_TAG_FLAG_1);
        Log.i(TAG, "setDefaultFirstFragment end...");
    }

    /**
     * Make sure of transaction with fade act.
     * @return
     */
    private FragmentTransaction ensureTransaction(){
        if(fragmentTransaction == null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        return fragmentTransaction;
    }

    /**
     * Set selected tab fragment.
     * @param tag
     */
    private void setFragmentSelection(String tag) {
        // begin a fragment transaction.
        fragmentTransaction = fragmentManager.beginTransaction();

        if(TextUtils.equals(currentFragmentTag, tag)){
            return;
        }

        if(currentFragmentTag!=null && !"".equals(currentFragmentTag)){
            ensureTransaction();
            detachFragment(getFragment(currentFragmentTag));
        }

        attachFragment(R.id.fragment_content, getFragment(tag), tag);
        commitTransactions(tag);
    }

    /**
     * Detach fragment.
     * @param fragment
     */
    private void detachFragment(Fragment fragment){

        if(fragment != null && !fragment.isDetached()){
            ensureTransaction();
            fragmentTransaction.detach(fragment);
        }
    }

    /**
     * Get fragment.
     * @param tag
     * @return
     */
    private Fragment getFragment(String tag){

        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if(fragment == null){
            switch(tag) {
                case FragmentConstants.FRAGMENT_TAG_FLAG_1:
                    fragment = NewsFragment.newInstance();
                    break;
                case FragmentConstants.FRAGMENT_TAG_FLAG_2:
                    fragment = ImageFragment.newInstance();
                    break;
                case FragmentConstants.FRAGMENT_TAG_FLAG_3:
                    fragment = VideoFragment.newInstance();
                    break;
                case FragmentConstants.FRAGMENT_TAG_FLAG_4:
                    fragment = SettingFragment.newInstance();
                    break;
                default:
                    Log.e(TAG, "Fragment not config!");
                    break;
            }
        }
        return fragment;

    }

    /**
     * Attach fragment.
     * @param layout
     * @param fragment
     * @param tag
     */
    private void attachFragment(int layout, Fragment fragment, String tag){
        if(fragment != null){
            if(fragment.isDetached()){
                ensureTransaction();
                fragmentTransaction.attach(fragment);

            }else if(!fragment.isAdded()){
                ensureTransaction();
                fragmentTransaction.add(layout, fragment, tag);
            }
        }
    }

    /**
     * Commit transactions.
     * @param tag
     */
    private void commitTransactions(String tag){
        if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
            fragmentTransaction.commit();
            currentFragmentTag = tag;
            fragmentTransaction = null;
        }
    }

    /**
     * Set Bottom selection by menu id, change the fragment by the way.
     */
    public void setBottomBarSelection(int menuItemId){
        switch (menuItemId){
            case R.id.item_news:
                setFragmentSelection(FragmentConstants.FRAGMENT_TAG_FLAG_1);
                break;
            case R.id.item_image:
                setFragmentSelection(FragmentConstants.FRAGMENT_TAG_FLAG_2);
                break;
            case R.id.item_video:
                setFragmentSelection(FragmentConstants.FRAGMENT_TAG_FLAG_3);
                break;
            case R.id.item_setting:
                setFragmentSelection(FragmentConstants.FRAGMENT_TAG_FLAG_4);
                break;
            default:
                Log.e(TAG, "Menu item id not config!");
                break;
        }
    }

    public interface View extends LoadView {

    }
}
