package com.company;

import java.util.HashMap;
import java.util.LinkedList;

public class Graphe {
    private LinkedList<Noeud> noeuds;
    /* This is how to declare HashMap */
    private HashMap<String, Noeud> hmap;


    public void addNoeud(Noeud n) {
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
            Noeud node = new Noeud(n);
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

    }

    public Noeud getNoeud(int n) {
        //recherche l'élément dans la liste

        return (this.getHmap().get(n));
    }

    @Override
    public String toString() {
        Noeud n;

        return "Graphe [noeuds=" + getNoeuds() + "]";
    }

    public Graphe() {
        super();
        setNoeuds(new LinkedList<Noeud>());
        setHmap(new HashMap<String, Noeud>());
    }

    private String getCharForNumber(int i) {
        return i >= 0 && i < 27 ? String.valueOf((char) (i + 65)) : null;
    }

    public Graphe(int k) {
        super();
        setNoeuds(new LinkedList<Noeud>());
        setHmap(new HashMap<String, Noeud>());

        for (int i = 0; i < k; i++) {
            getNoeuds().add(new Noeud(getCharForNumber(i)));
            /*Adding elements to HashMap*/
            hmap.put(getCharForNumber(i), getNoeuds().getLast());
        }
    }


    public LinkedList<Noeud> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(LinkedList<Noeud> noeuds) {
        this.noeuds = noeuds;
    }

    public HashMap<String, Noeud> getHmap() {
        return hmap;
    }

    public void setHmap(HashMap<String, Noeud> hmap) {
        this.hmap = hmap;
    }

}