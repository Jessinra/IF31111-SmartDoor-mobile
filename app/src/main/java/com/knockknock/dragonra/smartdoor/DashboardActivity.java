package com.knockknock.dragonra.smartdoor;

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
import com.knockknock.dragonra.smartdoor.view.Adapter.DashboardPageAdapter;
import com.knockknock.dragonra.smartdoor.view.DashboardFragment.HistoryFragment;
import com.knockknock.dragonra.smartdoor.view.DashboardFragment.RegisterFragment;

public class DashboardActivity extends AppCompatActivity
        implements RegisterFragment.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener {

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
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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

    @Override
    public void onFragmentInteraction(Uri uri) {
        // This is necessary, leave it blank
    }

}
