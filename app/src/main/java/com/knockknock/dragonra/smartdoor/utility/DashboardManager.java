package com.knockknock.dragonra.smartdoor.utility;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.model.DashboardBuildingRecord;
import com.knockknock.dragonra.smartdoor.model.DashboardFetchResult;
import com.knockknock.dragonra.smartdoor.view.Adapter.CardViewAdapter;

public class DashboardManager {

    public static void fetchDashboard(View view, String userToken) {
        Log.d("DashboardManager", "fetchDashboard");
        new DashboardFetcher(view).execute("userToken", userToken);
    }

    static void updateDashboard(@NonNull View view, DashboardFetchResult dashboardFetchResult) {

        ViewGroup dashboardCard = view.findViewById(R.id.dashboard);

        dashboardFetchResult.resetStart();

        View dashboardChildView;
        DashboardBuildingRecord buildingRecord;

        // Iterate through all cardView
        for (int i = 0; i < dashboardCard.getChildCount(); i++) {
            dashboardChildView = dashboardCard.getChildAt(i);
            if (dashboardChildView instanceof CardView) {

                buildingRecord = dashboardFetchResult.getNext();

                if (buildingRecord == null) {
                    CardViewAdapter.setInvisible(dashboardChildView);
                } else {
                    CardViewAdapter.setText(dashboardChildView, buildingRecord.getBuildingName());
                    CardViewAdapter.setLockState(dashboardChildView, buildingRecord.getBuildingLockState());
                }
            }
        }
    }
}
