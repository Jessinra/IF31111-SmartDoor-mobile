package com.knockknock.dragonra.smartdoor.controller.Manager;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.controller.DataFetcher.DashboardFetcher;
import com.knockknock.dragonra.smartdoor.model.DashboardBuildingRecord;
import com.knockknock.dragonra.smartdoor.model.DashboardFetchResult;
import com.knockknock.dragonra.smartdoor.view.Adapter.CardViewAdapter;

public class DashboardManager {

    public static void fetchDashboard(View view, String userToken) {
        Log.d("DASHBOARD_MANAGER", "fetchDashboard");
        new DashboardFetcher(view).execute("userToken", userToken);
    }

    public static void updateDashboard(@NonNull View view, DashboardFetchResult dashboardFetchResult) {
        Log.d("DASHBOARD_MANAGER", "updateDashboard");

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
