package com.knockknock.dragonra.smartdoor.model.dummy;

import java.util.ArrayList;

public class DummyBuildingData {

    private String name;
    private boolean locked;

    public DummyBuildingData(String name, boolean locked) {
        this.name = name;
        this.locked = locked;
    }

    public static ArrayList<DummyBuildingData> createMockBuildings(int count) {

        ArrayList<DummyBuildingData> buildings = new ArrayList<>();
        boolean lockSwitch = true;

        for (int i = 0; i < count; i++) {
            buildings.add(new DummyBuildingData("DummyBuildingData" + Integer.toString(i), lockSwitch));
            lockSwitch = !lockSwitch;
        }

        return buildings;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }
}
