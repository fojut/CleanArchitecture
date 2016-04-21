package org.fojut.sample.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.constant.FragmentConstant;
import org.fojut.sample.presentation.view.activity.base.BaseActivity;
import org.fojut.sample.presentation.view.fragment.ImageFragment;
import org.fojut.sample.presentation.view.fragment.NewsFragment;
import org.fojut.sample.presentation.view.fragment.SettingFragment;
import org.fojut.sample.presentation.view.fragment.VideoFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    public static String currentFragmentTag = null;
    protected BottomBar mBottomBar;

    @Nullable @Bind(R.id.root_layout)
    RelativeLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBottomBar = BottomBar.attach(this, savedInstanceState);

        fragmentManager = getSupportFragmentManager();
        setDefaultFirstFragment(FragmentConstant.FRAGMENT_TAG_FLAG_1);

        // Disable the left bar on tablets and behave exactly the same on mobile and tablets instead.
        mBottomBar.noTabletGoodness();
        // Show all titles even when there's more than three tabs.
        mBottomBar.useFixedMode();
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId){
                    case R.id.item_news:
                        setBottomBarSelection(FragmentConstant.FRAGMENT_TAG_FLAG_1);
                        break;
                    case R.id.item_image:
                        setBottomBarSelection(FragmentConstant.FRAGMENT_TAG_FLAG_2);
                        break;
                    case R.id.item_video:
                        setBottomBarSelection(FragmentConstant.FRAGMENT_TAG_FLAG_3);
                        break;
                    case R.id.item_setting:
                        setBottomBarSelection(FragmentConstant.FRAGMENT_TAG_FLAG_4);
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        getToolbar().setLogo(R.mipmap.ic_launcher);
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    @Override
    protected boolean showIndicator() {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_login:
                Log.d(TAG, "MenuItem: login action is selected!");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set default first fragment.
     * @param tag
     */
    private void setDefaultFirstFragment(String tag){
        Log.i(TAG, "setDefaultFirstFragment start... currentFragment = " + this.currentFragmentTag);
        setBottomBarSelection(tag);
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
    public void setBottomBarSelection(String tag) {
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
                case FragmentConstant.FRAGMENT_TAG_FLAG_1:
                    fragment = NewsFragment.newInstance();
                    break;
                case FragmentConstant.FRAGMENT_TAG_FLAG_2:
                    fragment = ImageFragment.newInstance();
                    break;
                case FragmentConstant.FRAGMENT_TAG_FLAG_3:
                    fragment = VideoFragment.newInstance();
                    break;
                case FragmentConstant.FRAGMENT_TAG_FLAG_4:
                    fragment = SettingFragment.newInstance();
                    break;
                default:
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

}
