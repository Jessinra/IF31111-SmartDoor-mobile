package com.knockknock.dragonra.smartdoor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.knockknock.dragonra.smartdoor.ui.dashboard.DashboardFragment;

public class DashboardActivity extends AppCompatActivity {

//    TODO: create more dashboard content & fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        Log.d("ACTIVITY_START", "onCreate DashboardActivity");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DashboardFragment.newInstance())
                    .commitNow();
        }
    }
}
