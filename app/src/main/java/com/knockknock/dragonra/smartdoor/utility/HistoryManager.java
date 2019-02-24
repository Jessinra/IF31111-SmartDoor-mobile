package com.knockknock.dragonra.smartdoor.utility;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.view.Adapter.HistoryViewAdapter;

import java.util.ArrayList;

public class HistoryManager {

    private final RecyclerView recyclerView;
    private final HistoryFetcher historyFetcher;

    public HistoryManager(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.historyFetcher = new HistoryFetcher();
    }

    public HistoryManager(View view) {
        recyclerView = view.findViewById(R.id.history_recycle_view);
        this.historyFetcher = new HistoryFetcher();
    }

    public HistoryManager(View view, int recyclerViewId) {
        recyclerView = view.findViewById(recyclerViewId);
        this.historyFetcher = new HistoryFetcher();
    }

    public ArrayList<String> fetchNewData() {

        historyFetcher.execute("userToken", "1234512345");
        return null;
    }

    public void setupHistoryPage(Context context, ArrayList<String> data) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new HistoryViewAdapter(data));
    }

    public void modifyHistoryPage() {

    }
}
