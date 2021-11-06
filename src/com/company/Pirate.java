package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pirate {
    private String id;
    private LinkedList<Arc> succ;
    private final List<String> preferences;
    private String objectsAttibuated;

    public Pirate(String id) {
        super();
        this.id = id;
        this.succ = new LinkedList<Arc>();
        this.preferences = new ArrayList<>();
        this.objectsAttibuated = "";

    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(String pref) {
        preferences.add(pref);
    }

    @Override
    public String toString() {
        return "Pirate{" +
                "id='" + id + '\'' +
                ", succ=" + succ +
                ", preferences=" + preferences +
                ", objectsAttibuated=" + objectsAttibuated +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pirate other = (Pirate) obj;
        return id == other.id;
    }

    public boolean hasSuccesseur(String j) {

        for (Arc e : this.succ) if (e.getCible().id.equals(j)) return (true);

        return (false);
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public LinkedList<Arc> getSucc() {
        return succ;
    }


    public void setSucc(LinkedList<Arc> succ) {
        this.succ = succ;
    }

    public String getObjectsAttibuated() {
        return objectsAttibuated;
    }

    public void setObjectsAttibuated(String objectsAttibuated) {
        this.objectsAttibuated = objectsAttibuated;
    }
}
