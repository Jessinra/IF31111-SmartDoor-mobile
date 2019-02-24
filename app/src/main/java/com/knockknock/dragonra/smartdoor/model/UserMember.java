package com.knockknock.dragonra.smartdoor.model;

public class UserMember {
    private String name;
    private String pattern;

    // TODO: use this as record container when fetching from DB

    public UserMember() {

    }

    public UserMember(String name, String pattern) {
        this.name = name;
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
