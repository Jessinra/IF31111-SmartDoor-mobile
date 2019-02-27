package com.knockknock.dragonra.smartdoor.model;

import android.support.annotation.NonNull;

public class HistoryRecord {
    private String buildingLockState;
    private String buildingName;
    private String timeStamp;

    public String getBuildingLockState() {
        return buildingLockState;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @NonNull
    public String toString() {
        return getTimeStamp() + "    " + getBuildingName() + "    " + getBuildingLockState();
    }
}
