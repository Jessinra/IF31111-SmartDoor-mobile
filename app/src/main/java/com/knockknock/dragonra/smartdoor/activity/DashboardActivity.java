package com.knockknock.dragonra.smartdoor.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.knockknock.dragonra.smartdoor.R;
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
    private boolean enableSensors = false;

    private int backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ACTIVITY_START", "onCreate DashboardActivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setupTab();
        setupToolbar();

        setSensorPreference();
        setupSensors(enableSensors);
    }

    private void setSensorPreference() {
        final SharedPreferences mSetting = getSharedPreferences(SettingActivity.PREFS_NAME, 0);
        enableSensors = mSetting.getBoolean("sensors", false);
    }

    private void setupTab() {
        // Create tab / pager handler
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new DashboardPageAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabs = findViewById(R.id.tabs);
        tabs.setShouldExpand(true);
        tabs.setViewPager(pager);
    }

    private void setupToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void setupSensors(boolean enableSensors) {

        // TODO: Setup sensors
        if (enableSensors) {
            significantMovementSensorHandler = new SignificantMovementSensorHandler(this);
            proximitySensorHandler = new ProximitySensorHandler(this);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // This is necessary, leave it blank
    }

    @Override
    protected void onResume() {
        super.onResume();

//        TODO: Uncomment for sensors usage
        if (enableSensors) {
            significantMovementSensorHandler.register();
            proximitySensorHandler.register();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

//        TODO: Uncomment for sensors usage
        if (enableSensors) {
            significantMovementSensorHandler.unregister();
            proximitySensorHandler.register();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_menu:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;

            case R.id.call_icon:
                Log.d("DashboardActivity", "hello: ");

                // TODO: fetch from database
                String androidDoorNumber = "+628123456789";

                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", androidDoorNumber, null));
                startActivity(callIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        // Prevent going out of apps by accidentally pushing back button

        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
}
