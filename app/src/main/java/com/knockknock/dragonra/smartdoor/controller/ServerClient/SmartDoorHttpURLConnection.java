package com.knockknock.dragonra.smartdoor.controller.ServerClient;

import android.support.annotation.NonNull;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class SmartDoorHttpURLConnection {

    private final String USER_AGENT = "Mozilla/5.0";

    public String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        return parseResponse(con);
    }

    String sendPost(String url, ArrayList<Pair<String, String>> params) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String postParams = serializePostParams(params);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        // Parse response
        return parseResponse(con);
    }

    @NonNull
    private String serializePostParams(ArrayList<Pair<String, String>> params) {

        StringBuilder postParamsBuilder = new StringBuilder();
        for (Pair<String, String> param : params) {
            postParamsBuilder.append(param.first).append("=").append(param.second).append("&");
        }
        postParamsBuilder.setLength(postParamsBuilder.length() - 1);  // remove last "&"
        return postParamsBuilder.toString();
    }

    private String parseResponse(HttpsURLConnection con) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}