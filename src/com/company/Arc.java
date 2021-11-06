package com.company;


public class Arc {
    private Pirate source;
    private Pirate cible;


    public Arc(Pirate source, Pirate cible) {
        super();
        this.source = source;
        this.cible = cible;
        this.source.getSucc().add(this);

    }

    @Override
    public String toString() {
        return "Arc [source=" + source.getId() + ", cible=" + cible.getId() + "]";
    }

    public Pirate getSource() {
        return source;
    }

    public void setSource(Pirate source) {
        this.source = source;
    }

    public Pirate getCible() {
        return cible;
    }

    public void setCible(Pirate cible) {
        this.cible = cible;
    }

}

