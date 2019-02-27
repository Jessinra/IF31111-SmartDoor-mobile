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
import java.util.concurrent.Callable;

public class HistoryManager {

    private static WeakReference<RecyclerView> recyclerView;
    private static String userToken;

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
        new HistoryFetcher(recyclerView.get()).execute("userToken", userToken);
    }

    // Similar to onFinishFetchNewData
    public static void updateHistoryPage(RecyclerView recyclerView, HistoryFetchResult historyFetchResult) {
        Log.d("HISTORY", "updateHistoryPage");

        HistoryPageAdapter historyViewAdapter = new HistoryPageAdapter(historyFetchResult.toStringArray());
        recyclerView.setAdapter(historyViewAdapter);
    }

    public static void logHistory(String userToken, String buildingId, String lockState, Callable callback) {
        Log.d("DASHBOARD_MANAGER", "changeLockState");
        new HistoryLogger(userToken, buildingId, lockState, callback).execute();
    }
}
