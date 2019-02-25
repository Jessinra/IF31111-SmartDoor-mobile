package com.knockknock.dragonra.smartdoor;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.knockknock.dragonra.smartdoor.utility.SignificantMovementSensorHandler;
import com.knockknock.dragonra.smartdoor.view.Adapter.DashboardPageAdapter;
import com.knockknock.dragonra.smartdoor.view.DashboardFragment.HistoryFragment;
import com.knockknock.dragonra.smartdoor.view.DashboardFragment.RegisterFragment;

public class DashboardActivity extends AppCompatActivity
        implements RegisterFragment.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener {

    private SignificantMovementSensorHandler significantMovementSensorHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onCreate DashboardActivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        // Create tab / pager handler
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new DashboardPageAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabs = findViewById(R.id.tabs);
        tabs.setShouldExpand(true);
        tabs.setViewPager(pager);

        // Setup sensor
        significantMovementSensorHandler = new SignificantMovementSensorHandler(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // This is necessary, leave it blank
    }

    @Override
    protected void onResume() {
        super.onResume();
        significantMovementSensorHandler.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        significantMovementSensorHandler.unregister();
    }
}
