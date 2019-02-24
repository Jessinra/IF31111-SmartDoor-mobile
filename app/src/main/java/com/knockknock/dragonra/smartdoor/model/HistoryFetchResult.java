package com.knockknock.dragonra.smartdoor.model;

import java.util.ArrayList;

public class HistoryFetchResult {
    private String userToken;
    private HistoryRecord[] history;

    public String getUserToken() {
        return userToken;
    }

    public HistoryRecord[] getHistory() {
        return history;
    }

    public ArrayList<String> toStringArray() {

        ArrayList<String> result = new ArrayList<>();
        for (HistoryRecord record : history) {
            result.add(record.toString());
        }
        return result;
    }
}