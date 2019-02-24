package com.knockknock.dragonra.smartdoor.utility;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.knockknock.dragonra.smartdoor.model.HistoryFetchResult;
import com.knockknock.dragonra.smartdoor.view.Adapter.HistoryViewAdapter;

import java.lang.ref.WeakReference;

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
        fetchNewData(userToken);
    }

    public static void fetchNewData(String userToken) {
        Log.d("HISTORY", "fetchNewData");
        if (recyclerView.get() == null) {
            return;
        }

        // Create new to keep update the recyclerView
        new HistoryFetcher(recyclerView.get()).execute("userToken", userToken);
    }

    // Similar to onFinishFetchNewData
    public static void updateHistoryPage(RecyclerView recyclerView, HistoryFetchResult historyFetchResult) {
        Log.d("HISTORY", "updateHistoryPage");

        HistoryViewAdapter historyViewAdapter = new HistoryViewAdapter(historyFetchResult.toStringArray());
        recyclerView.setAdapter(historyViewAdapter);
    }
}
