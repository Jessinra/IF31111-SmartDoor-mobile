package com.knockknock.dragonra.smartdoor.model.dummy;

import java.util.ArrayList;

public class DummyBuilding {

    private String name;
    private boolean locked;

    public DummyBuilding(String name, boolean locked) {
        this.name = name;
        this.locked = locked;
    }

    public static ArrayList<DummyBuilding> createMockBuildings(int count) {

        ArrayList<DummyBuilding> buildings = new ArrayList<>();
        boolean lockSwitch = true;

        for (int i = 0; i < count; i++) {
            buildings.add(new DummyBuilding("DummyBuilding" + Integer.toString(i), lockSwitch));
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
