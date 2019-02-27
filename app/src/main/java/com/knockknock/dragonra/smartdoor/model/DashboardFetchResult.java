package com.knockknock.dragonra.smartdoor.model;

public class DashboardFetchResult {

    private int startIndex;

    private DashboardBuildingRecord[] buildings;

    public DashboardBuildingRecord[] getBuildings() {
        return buildings;
    }

    public void resetStart() {
        startIndex = 0;
    }

    public DashboardBuildingRecord getNext() {
        startIndex++;
        if (startIndex > buildings.length) {
            return null;
        }
        return buildings[startIndex - 1];
    }
}
