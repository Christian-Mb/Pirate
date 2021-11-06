package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graphe {
    private LinkedList<Pirate> pirates;
    /* This is how to declare HashMap */
    private HashMap<String, Pirate> hmap;
    private List<String> objects;


    public Graphe() {
        super();
        setNoeuds(new LinkedList<Pirate>());
        setHmap(new HashMap<String, Pirate>());
        objects = new ArrayList<>();
    }

    public Graphe(int k) {
        super();
        setNoeuds(new LinkedList<Pirate>());
        setHmap(new HashMap<String, Pirate>());

        for (int i = 0; i < k; i++) {
            getPirates().add(new Pirate(getCharForNumber(i)));
            /*Adding elements to HashMap*/
            hmap.put(getCharForNumber(i), getPirates().getLast());
        }
    }

    public void addArc(String x, String y) {
        //Verifie que x est dans le graphe
        // Verifie que y est dans le graphe
        // test si y est déja successeur de x
        // l'ajoute Sinon

        if (this.getHmap().get(x) != null && this.getHmap().get(y) != null) {
            if (!(this.getHmap().get(x).hasSuccesseur(y)))
                new Arc(this.getHmap().get(x), this.getHmap().get(y));
        }

        if (this.getHmap().get(y) != null && this.getHmap().get(x) != null) {
            if (!(this.getHmap().get(y).hasSuccesseur(x)))
                new Arc(this.getHmap().get(y), this.getHmap().get(x));
        }

    }

    public void addPirate(Pirate n) {
        // recherche si l'id apparait dans un Noeud de la liste
        // Ajoute le Noeud à la liste sinon
/*		if (! (this.getPirates().contains(n))) {
			this.getPirates().add(n);
			this.getHmap().put(n.getId(),n);
		}*/
        if (this.getHmap().get(n.getId()) == null) {
            this.getPirates().add(n);
            this.getHmap().put(n.getId(), n);
        }
    }

    public void addPirate(String n) {

        // recherche si un Noeud n apparait dans la liste

        if (this.getHmap().get(n) == null) {
            Pirate pirate = new Pirate(n);
            this.getPirates().add(pirate);
            this.getHmap().put(n, pirate);
        }

    }

    public Pirate getPirate(String n) {
        //recherche l'élément dans la liste

        return (this.getHmap().get(n));
    }

    private String getCharForNumber(int i) {
        return i >= 0 && i < 27 ? String.valueOf((char) (i + 65)) : null;
    }

    @Override
    public String toString() {
        Pirate n;

        return "Graphe [noeuds=" + getPirates() + "]";
    }

    public void setNoeuds(LinkedList<Pirate> pirates) {
        this.pirates = pirates;
    }

    public HashMap<String, Pirate> getHmap() {
        return hmap;
    }

    public void setHmap(HashMap<String, Pirate> hmap) {
        this.hmap = hmap;
    }

    public LinkedList<Pirate> getPirates() {
        return pirates;
    }

    public void setPirates(LinkedList<Pirate> pirates) {
        this.pirates = pirates;
    }

    public List<String> getObjects() {
        return objects;
    }

    public void addObject(String objectName) {
        this.objects.add(objectName);
    }
}
