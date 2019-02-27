package com.knockknock.dragonra.smartdoor.controller.ServerClient;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class BuildingLockStateUpdater extends AsyncTask<String, String, Void> {

    private String userToken;
    private String buildingId;
    private String lockState;
    private Callable callback;

    public BuildingLockStateUpdater(String userToken, String buildingId, String lockState, Callable callback) {
        this.userToken = userToken;
        this.buildingId = buildingId;
        this.lockState = lockState;
        this.callback = callback;
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
            String dashboardHandlerURL = "https://us-central1-if3111-smartdoor.cloudfunctions.net/dashboardHandler";
            String response = connectionManager.sendPost(dashboardHandlerURL, postParams);

            Log.d("POST_REQUEST", "response :" + response);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void dummy) {
        super.onPostExecute(dummy);
        try {
            callback.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



