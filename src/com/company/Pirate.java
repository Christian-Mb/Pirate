package com.company;

import java.util.List;

public class Pirate {
    private String name;
    private List<Integer> preferences;

    public Pirate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Integer> preferences) {
        this.preferences = preferences;
    }
}
