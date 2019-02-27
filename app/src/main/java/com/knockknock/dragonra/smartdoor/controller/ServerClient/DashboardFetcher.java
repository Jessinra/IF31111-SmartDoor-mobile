package com.knockknock.dragonra.smartdoor.controller.ServerClient;

import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;

import com.google.gson.Gson;
import com.knockknock.dragonra.smartdoor.controller.Manager.DashboardManager;
import com.knockknock.dragonra.smartdoor.model.DashboardFetchResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DashboardFetcher extends AsyncTask<String, String, DashboardFetchResult> {

    private WeakReference<View> dashboard;

    public DashboardFetcher(View dashboard) {
        this.dashboard = new WeakReference<>(dashboard);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected DashboardFetchResult doInBackground(String... params) {
        // Create POST request to server

        SmartDoorHttpURLConnection connectionManager = new SmartDoorHttpURLConnection();

        ArrayList<Pair<String, String>> postParams = new ArrayList<>();
        postParams.add(new Pair<>(params[0], params[1]));

        try {
            String fetchHistoryURL = "https://us-central1-if3111-smartdoor.cloudfunctions.net/dashboard";
            String response = connectionManager.sendPost(fetchHistoryURL, postParams);

            return new Gson().fromJson(response, DashboardFetchResult.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(DashboardFetchResult dashboardFetchResult) {
        super.onPostExecute(dashboardFetchResult);

        if (dashboard.get() != null && dashboardFetchResult != null) {
            DashboardManager.updateDashboard(dashboard.get(), dashboardFetchResult);
            DashboardManager.setCacheAsNew();
        }
    }
}



