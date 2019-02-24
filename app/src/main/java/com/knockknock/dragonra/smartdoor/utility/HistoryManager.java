package com.knockknock.dragonra.smartdoor.utility;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.model.HistoryFetchResult;
import com.knockknock.dragonra.smartdoor.view.Adapter.HistoryViewAdapter;

public class HistoryManager {

    private final RecyclerView recyclerView;
    private static HistoryViewAdapter historyViewAdapter;

    private static String userToken;

    public HistoryManager(RecyclerView recyclerView, String userToken) {
        this.recyclerView = recyclerView;
        HistoryManager.userToken = userToken;
    }

    public HistoryManager(View view, String userToken) {
        recyclerView = view.findViewById(R.id.history_recycle_view);
        HistoryManager.userToken = userToken;
    }

    public HistoryManager(View view, int recyclerViewId, String userToken) {
        recyclerView = view.findViewById(recyclerViewId);
        HistoryManager.userToken = userToken;
    }

    public void setupHistoryPage(Context context) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        fetchNewData(userToken);
    }

    public void fetchNewData(String userToken) {
        // Create new to keep update the recyclerView
        new HistoryFetcher(recyclerView).execute("userToken", userToken);
    }

    public static void updateHistoryPage(RecyclerView recyclerView, HistoryFetchResult historyFetchResult) {

        Log.d("HISTORY", "updateHistoryPage");

        historyViewAdapter = new HistoryViewAdapter(historyFetchResult.toStringArray());
        recyclerView.setAdapter(historyViewAdapter);
    }
}
