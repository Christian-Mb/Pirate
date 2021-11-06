package com.company;

import java.util.HashMap;
import java.util.LinkedList;

public class Graphe {
    private LinkedList<Pirate> pirates;
    /* This is how to declare HashMap */
    private HashMap<String, Pirate> hmap;


    public void addNoeud(Pirate n) {
        // recherche si l'id apparait dans un Noeud de la liste
        // Ajoute le Noeud à la liste sinon
/*		if (! (this.getNoeuds().contains(n))) {
			this.getNoeuds().add(n);
			this.getHmap().put(n.getId(),n);
		}*/
        if (this.getHmap().get(n.getId()) == null) {
            this.getNoeuds().add(n);
            this.getHmap().put(n.getId(), n);
        }
    }

    public void addNoeud(String n) {

        // recherche si un Noeud n apparait dans la liste

        if (this.getHmap().get(n) == null) {
            Pirate node = new Pirate(n);
            this.getNoeuds().add(node);
            this.getHmap().put(n, node);
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

    public Pirate getNoeud(String n) {
        //recherche l'élément dans la liste

        return (this.getHmap().get(n));
    }

    @Override
    public String toString() {
        Pirate n;

        return "Graphe [noeuds=" + getNoeuds() + "]";
    }

    public Graphe() {
        super();
        setNoeuds(new LinkedList<Pirate>());
        setHmap(new HashMap<String, Pirate>());
    }

    private String getCharForNumber(int i) {
        return i >= 0 && i < 27 ? String.valueOf((char) (i + 65)) : null;
    }

    public Graphe(int k) {
        super();
        setNoeuds(new LinkedList<Pirate>());
        setHmap(new HashMap<String, Pirate>());

        for (int i = 0; i < k; i++) {
            getNoeuds().add(new Pirate(getCharForNumber(i)));
            /*Adding elements to HashMap*/
            hmap.put(getCharForNumber(i), getNoeuds().getLast());
        }
    }

    public LinkedList<Pirate> getNoeuds() {
        return pirates;
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

}
