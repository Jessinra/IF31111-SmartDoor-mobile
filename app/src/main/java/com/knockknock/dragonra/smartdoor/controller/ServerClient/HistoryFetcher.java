package com.knockknock.dragonra.smartdoor.controller.ServerClient;


import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;

import com.google.gson.Gson;
import com.knockknock.dragonra.smartdoor.controller.Manager.HistoryManager;
import com.knockknock.dragonra.smartdoor.model.HistoryFetchResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class HistoryFetcher extends AsyncTask<String, String, HistoryFetchResult> {

    private WeakReference<RecyclerView> recyclerView;

    public HistoryFetcher(RecyclerView recyclerView) {
        this.recyclerView = new WeakReference<>(recyclerView);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HistoryFetchResult doInBackground(String... params) {

        SmartDoorHttpURLConnection connectionManager = new SmartDoorHttpURLConnection();

        ArrayList<Pair<String, String>> postParams = new ArrayList<>();
        postParams.add(new Pair<>(params[0], params[1]));

        try {
            String fetchHistoryURL = "https://us-central1-if3111-smartdoor.cloudfunctions.net/history";
            String response = connectionManager.sendPost(fetchHistoryURL, postParams);

            return new Gson().fromJson(response, HistoryFetchResult.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(HistoryFetchResult historyFetchResult) {
        super.onPostExecute(historyFetchResult);

        if (recyclerView.get() != null && historyFetchResult != null) {
            HistoryManager.updateHistoryPage(recyclerView.get(), historyFetchResult);
            HistoryManager.setCacheAsNew();
        }
    }
}



