package com.knockknock.dragonra.smartdoor.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.SettingActivity;
import com.knockknock.dragonra.smartdoor.activity.DashboardFragment.HistoryFragment;
import com.knockknock.dragonra.smartdoor.activity.DashboardFragment.RegisterFragment;
import com.knockknock.dragonra.smartdoor.controller.SensorHandler.ProximitySensorHandler;
import com.knockknock.dragonra.smartdoor.controller.SensorHandler.SignificantMovementSensorHandler;
import com.knockknock.dragonra.smartdoor.view.Adapter.DashboardPageAdapter;

public class DashboardActivity extends AppCompatActivity
        implements RegisterFragment.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener {

    private SignificantMovementSensorHandler significantMovementSensorHandler;
    private ProximitySensorHandler proximitySensorHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onCreate DashboardActivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Create tab / pager handler
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new DashboardPageAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabs = findViewById(R.id.tabs);
        tabs.setShouldExpand(true);
        tabs.setViewPager(pager);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Setup sensors
//        TODO: Setup sensors
//        significantMovementSensorHandler = new SignificantMovementSensorHandler(this);
//        proximitySensorHandler = new ProximitySensorHandler(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // This is necessary, leave it blank
    }

    @Override
    protected void onResume() {
        super.onResume();

//        TODO: Uncomment for sensors usage
//        significantMovementSensorHandler.register();
//        proximitySensorHandler.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        TODO: Uncomment for sensors usage
//        significantMovementSensorHandler.unregister();
//        proximitySensorHandler.register();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
}
