package com.knockknock.dragonra.smartdoor.controller.Manager;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.controller.ServerClient.BuildingLockStateUpdater;
import com.knockknock.dragonra.smartdoor.controller.ServerClient.DashboardFetcher;
import com.knockknock.dragonra.smartdoor.model.DashboardBuildingRecord;
import com.knockknock.dragonra.smartdoor.model.DashboardFetchResult;
import com.knockknock.dragonra.smartdoor.view.Adapter.CardViewAdapter;

import java.util.concurrent.Callable;

public class DashboardManager {

    private static DashboardFetchResult dashboardFetchResult;
    private static int cacheUsageBeforeExpire = 0;

    public static void fetchDashboard(View view, String userToken) {
        Log.d("DASHBOARD_MANAGER", "fetchDashboard");

        if (isCacheExpired()) {
            new DashboardFetcher(view).execute("userToken", userToken);
        } else {
            reduceCacheUsageBeforeExpire();
            updateDashboard(view, dashboardFetchResult);
        }
    }

    public static void updateDashboard(@NonNull View view, DashboardFetchResult dashboardFetchResult) {
        Log.d("DASHBOARD_MANAGER", "updateDashboard");

        DashboardManager.dashboardFetchResult = dashboardFetchResult;

        ViewGroup dashboardCard = view.findViewById(R.id.dashboard_home);
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

    public static void changeLockState(String userToken, String buildingId, String lockState, Callable callback) {
        Log.d("DASHBOARD_MANAGER", "changeLockState");
        invalidateCache();
        new BuildingLockStateUpdater(userToken, buildingId, lockState, callback).execute();
    }

    private static void invalidateCache() {
        DashboardManager.cacheUsageBeforeExpire = 0;
    }

    public static void setCacheAsNew() {
        DashboardManager.cacheUsageBeforeExpire = 5;
    }

    private static boolean isCacheExpired() {
        return DashboardManager.cacheUsageBeforeExpire == 0;
    }

    private static void reduceCacheUsageBeforeExpire() {
        DashboardManager.cacheUsageBeforeExpire--;
    }
}
