package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pirate {
    private String id;
    private LinkedList<Arc> succ;
    private List<Integer> preferences;
    private int objectsAttibuated;


    public Pirate(String id) {
        super();
        this.id = id;
        this.succ = new LinkedList<Arc>();
        this.preferences = new ArrayList<>();
        this.objectsAttibuated = 0;

    }

    public void setPreferences(int pref) {
        preferences.add(pref);
    }

    public List<Integer> getPreferences() {
        return preferences;
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
        if (id != other.id)
            return false;
        return true;
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

    public int getObjectsAttibuated() {
        return objectsAttibuated;
    }

    public void setObjectsAttibuated(int objectsAttibuated) {
        this.objectsAttibuated = objectsAttibuated;
    }
}
