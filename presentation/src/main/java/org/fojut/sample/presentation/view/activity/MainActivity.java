package org.fojut.sample.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.internal.di.component.ActivityComponent;
import org.fojut.sample.presentation.internal.di.component.DaggerActivityComponent;
import org.fojut.sample.presentation.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.presenter.BottomBarPresenter;
import org.fojut.sample.presentation.view.activity.base.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent>,
        BottomBarPresenter.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    protected BottomBar mBottomBar;

    @Nullable @Bind(R.id.root_layout)
    RelativeLayout rootView;

    @Inject
    BottomBarPresenter bottomBarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBottomBar = BottomBar.attach(this, savedInstanceState);

        getComponent().inject(this);
        this.bottomBarPresenter.setView(this);
        this.bottomBarPresenter.initActivityFragment(getSupportFragmentManager());

        // Disable the left bar on tablets and behave exactly the same on mobile and tablets instead.
        mBottomBar.noTabletGoodness();
        // Show all titles even when there's more than three tabs.
        mBottomBar.useFixedMode();
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                bottomBarPresenter.setBottomBarSelection(menuItemId);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.bottomBarPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.bottomBarPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.bottomBarPresenter.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).build();
    }
}
