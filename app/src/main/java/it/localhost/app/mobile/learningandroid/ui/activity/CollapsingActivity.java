package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.ui.adapter.CollapsingPagerAdapter;

/**
 * CollapsingToolbarLayout with CoordinatorLayout example
 */
public class CollapsingActivity extends BaseActivity {

    private static final String TAG = CollapsingActivity.class.getSimpleName();

    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tl)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initViewPager();
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_collapsing;
    }

    @Override
    public int getIdToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int getIdFab() {
        return R.id.fab;
    }

    private void initViewPager() {
        CollapsingPagerAdapter adapter = new CollapsingPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
