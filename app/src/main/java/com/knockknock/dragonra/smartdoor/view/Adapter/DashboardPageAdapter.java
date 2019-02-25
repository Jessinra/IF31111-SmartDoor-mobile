package com.knockknock.dragonra.smartdoor.view.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.knockknock.dragonra.smartdoor.activity.DashboardFragment.HistoryFragment;
import com.knockknock.dragonra.smartdoor.activity.DashboardFragment.HomeFragment;
import com.knockknock.dragonra.smartdoor.activity.DashboardFragment.RegisterFragment;

public class DashboardPageAdapter extends FragmentStatePagerAdapter {

    private String[] titles = {"Home", "User", "History"};

    public DashboardPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new HomeFragment();
            case 1:
                return new RegisterFragment();
            case 2:
                return new HistoryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
