package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Noeud d = new Noeud("D");
        Noeud e = new Noeud("E");
        Noeud f = new Noeud("F");

        Arc de = new Arc(d, e);
        Arc ef = new Arc(e, f);

        System.out.println("Print nodes /");

        System.out.println(d);
        System.out.println(e);
        System.out.println(f);

        System.out.println("Print edges /");

        System.out.println(de);
        System.out.println(ef);

        System.out.println("Graph /");


        Graphe monGraph = new Graphe(3);
        monGraph.addNoeud(d);
        monGraph.addNoeud(e);
        monGraph.addNoeud(f);
        monGraph.addArc("A", "B");
        monGraph.addArc("B", "C");


        System.out.println(monGraph);
    }
}
