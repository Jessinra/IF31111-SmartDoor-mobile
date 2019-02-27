package com.knockknock.dragonra.smartdoor.controller.Manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.knockknock.dragonra.smartdoor.controller.ServerClient.HistoryFetcher;
import com.knockknock.dragonra.smartdoor.controller.ServerClient.HistoryLogger;
import com.knockknock.dragonra.smartdoor.model.HistoryFetchResult;
import com.knockknock.dragonra.smartdoor.view.Adapter.HistoryPageAdapter;

import java.lang.ref.WeakReference;

public class HistoryManager {

    private static WeakReference<RecyclerView> recyclerView;
    private static String userToken;

    private static int cacheUsageBeforeExpire = 0;
    private static HistoryFetchResult historyFetchResult;

    public HistoryManager(RecyclerView recyclerView, String userToken) {
        HistoryManager.recyclerView = new WeakReference<>(recyclerView);
        HistoryManager.userToken = userToken;
    }

    public void setupHistoryPage(Context context) {
        Log.d("HISTORY", "setupHistoryPage");
        if (recyclerView.get() == null) {
            return;
        }
        recyclerView.get().setHasFixedSize(true);
        recyclerView.get().setLayoutManager(new LinearLayoutManager(context));
        fetchHistoryData(userToken);
    }

    public static void fetchHistoryData(String userToken) {
        Log.d("HISTORY", "fetchHistoryData");
        if (recyclerView.get() == null) {
            return;
        }

        // Create new to keep update the recyclerView
        if (isCacheExpired()) {
            new HistoryFetcher(recyclerView.get()).execute("userToken", userToken);
        } else {
            reduceCacheUsageBeforeExpire();
            updateHistoryPage(recyclerView.get(), historyFetchResult);
        }
    }

    // Similar to onFinishFetchNewData
    public static void updateHistoryPage(RecyclerView recyclerView, HistoryFetchResult historyFetchResult) {
        Log.d("HISTORY", "updateHistoryPage");

        HistoryManager.historyFetchResult = historyFetchResult;

        HistoryPageAdapter historyViewAdapter = new HistoryPageAdapter(historyFetchResult.toStringArray());
        recyclerView.setAdapter(historyViewAdapter);
    }

    public static void logHistory(String userToken, String buildingId, String lockState) {
        Log.d("DASHBOARD_MANAGER", "changeLockState");

        invalidateCache();
        new HistoryLogger(userToken, buildingId, lockState).execute();
    }

    private static void invalidateCache() {
        HistoryManager.cacheUsageBeforeExpire = 0;
    }

    public static void setCacheAsNew() {
        HistoryManager.cacheUsageBeforeExpire = 5;
    }

    private static boolean isCacheExpired() {
        return HistoryManager.cacheUsageBeforeExpire == 0;
    }

    private static void reduceCacheUsageBeforeExpire() {
        HistoryManager.cacheUsageBeforeExpire--;
    }
}
