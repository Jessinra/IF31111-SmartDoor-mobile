package com.knockknock.dragonra.smartdoor.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.knockknock.dragonra.smartdoor.view.DashboardFragment.DashboardFragment;
import com.knockknock.dragonra.smartdoor.view.DashboardFragment.HistoryFragment;
import com.knockknock.dragonra.smartdoor.view.DashboardFragment.RegisterFragment;

public class DashboardPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles = {"Register", "Home", "History"};

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new RegisterFragment();
            case 1:
                return new DashboardFragment();
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
