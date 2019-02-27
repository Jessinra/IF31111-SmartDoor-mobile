package com.knockknock.dragonra.smartdoor.controller.ServerClient;

import android.os.AsyncTask;
import android.util.Pair;

import java.util.ArrayList;


public class HistoryLogger extends AsyncTask<String, String, Void> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        // Create POST request to server

        SmartDoorHttpURLConnection connectionManager = new SmartDoorHttpURLConnection();

        ArrayList<Pair<String, String>> postParams = new ArrayList<>();
        postParams.add(new Pair<>(params[0], params[1]));
        postParams.add(new Pair<>(params[2], params[3]));
        postParams.add(new Pair<>(params[4], params[5]));

        try {
            String fetchHistoryURL = "https://us-central1-if3111-smartdoor.cloudfunctions.net/historyLogger";
            String response = connectionManager.sendPost(fetchHistoryURL, postParams);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}



