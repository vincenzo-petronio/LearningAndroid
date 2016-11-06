package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import it.localhost.app.mobile.learningandroid.ui.fragment.ViewPagerBaseFragment;

/**
 * Adapter per il ViewPager
 */
public class CollapsingPagerAdapter extends FragmentStatePagerAdapter {

    /**
     * @param fm FragmentManager
     */
    public CollapsingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ViewPagerBaseFragment.newInstance(0);
            case 1:
                return ViewPagerBaseFragment.newInstance(1);
            case 2:
                return ViewPagerBaseFragment.newInstance(2);
            case 3:
                return ViewPagerBaseFragment.newInstance(3);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Green";
            case 1:
                return "Red";
            case 2:
                return "Blu";
            case 3:
                return "Orange";
            default:
                return null;
        }
    }
}
