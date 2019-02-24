package com.knockknock.dragonra.smartdoor.model;

import android.support.annotation.NonNull;

public class HistoryRecord {
    private String date;
    private String time;
    private String building;

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getBuilding() {
        return building;
    }

    @NonNull
    public String toString() {
        return getDate() + " " + getTime() + " " + getBuilding();
    }
}
