package com.company;


public class Arc {
    private Noeud	source;
    private	Noeud	cible;


    public Arc(Noeud source, Noeud cible) {
        super();
        this.source = source;
        this.cible = cible;
        this.source.getSucc().add(this);

    }

    @Override
    public String toString() {
        return "Arc [source=" + source.getId() + ", cible=" + cible.getId() + "]";
    }

    public Noeud getSource() {
        return source;
    }

    public void setSource(Noeud source) {
        this.source = source;
    }

    public Noeud getCible() {
        return cible;
    }

    public void setCible(Noeud cible) {
        this.cible = cible;
    }

}

