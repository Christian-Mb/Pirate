package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Noeud {
    private String id;
    private LinkedList<Arc> succ;
    private List<Integer> preferences;


    public Noeud(String id) {
        super();
        this.id = id;
        this.succ = new LinkedList<Arc>();
        this.preferences = new ArrayList<>();

    }

    public void setPreferences(int pref) {
        preferences.add(pref);
    }

    public List<Integer> getPreferences() {
        return preferences;
    }

    @Override
    public String toString() {
        String descriptionNoeud = "";
        descriptionNoeud = "Noeud [id=" + id + ",successeurs :";
        for (Arc arc : succ)
            descriptionNoeud += "->" + arc.getCible().getId();
        descriptionNoeud += "]";
        return descriptionNoeud;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Noeud other = (Noeud) obj;
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
}
