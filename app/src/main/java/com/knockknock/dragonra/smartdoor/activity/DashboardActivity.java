package com.knockknock.dragonra.smartdoor.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.activity.SettingActivity;
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
    private static final String TAG = "DashboardActivity";
    private static final String androidDoorNumber = "+628123456789";
    private static final int REQUEST_WRITE_SETTINGS=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settingPermission();
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
//        significantMovementSensorHandler = new SignificantMovementSensorHandler(this);
//        proximitySensorHandler = new ProximitySensorHandler(this);
    }

    public void settingPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 200);

            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // This is necessary, leave it blank
    }

    @Override
    protected void onResume() {
        super.onResume();
//        significantMovementSensorHandler.register();
//        proximitySensorHandler.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        significantMovementSensorHandler.unregister();
//        proximitySensorHandler.register();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings_menu:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.call_icon:
                Log.wtf(TAG, "hello: ");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", androidDoorNumber, null));
                startActivity(callIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
}
