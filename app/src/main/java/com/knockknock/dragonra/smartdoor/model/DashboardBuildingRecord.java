package com.knockknock.dragonra.smartdoor.model;

public class DashboardBuildingRecord {

    private int buildingId;
    private int buildingLocation;
    private String buildingName;
    private String buildingLockState;

    public int getBuildingId() {
        return buildingId;
    }

    public int getBuildingLocation() {
        return buildingLocation;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Boolean getBuildingLockState() {
        return (buildingLockState.toLowerCase().equals("locked"));
    }
}
