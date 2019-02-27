package com.knockknock.dragonra.smartdoor.controller.ServerClient;

import android.os.AsyncTask;
import android.util.Pair;

import java.util.ArrayList;


public class HistoryLogger extends AsyncTask<String, String, Void> {

    private String userToken;
    private String buildingId;
    private String lockState;

    public HistoryLogger(String userToken, String buildingId, String lockState) {
        this.userToken = userToken;
        this.buildingId = buildingId;
        this.lockState = lockState;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        // Create POST request to server

        SmartDoorHttpURLConnection connectionManager = new SmartDoorHttpURLConnection();

        ArrayList<Pair<String, String>> postParams = new ArrayList<>();
        postParams.add(new Pair<>("userToken", userToken));
        postParams.add(new Pair<>("buildingId", buildingId));
        postParams.add(new Pair<>("buildingLockState", lockState));


        try {
            String historyLoggerURL = "https://us-central1-if3111-smartdoor.cloudfunctions.net/historyLogger";
            String response = connectionManager.sendPost(historyLoggerURL, postParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void dummy) {
        super.onPostExecute(dummy);
    }
}



