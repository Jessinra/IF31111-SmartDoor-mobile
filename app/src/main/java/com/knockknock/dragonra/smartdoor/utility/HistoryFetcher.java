package com.knockknock.dragonra.smartdoor.utility;


import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

public class HistoryFetcher extends AsyncTask<String, String, String> {

    public HistoryFetcher() {
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        // Create POST request to server

        HttpURLConnectionManager connectionManager = new HttpURLConnectionManager();

        ArrayList<Pair<String, String>> postParams = new ArrayList<>();
        postParams.add(new Pair<>(params[0], params[1]));

        try {
            String fetchHistoryURL = "https://us-central1-if3111-smartdoor.cloudfunctions.net/history";
            String response = connectionManager.sendPost(fetchHistoryURL, postParams);
            Log.d("HISTORY_FETCHER", response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
