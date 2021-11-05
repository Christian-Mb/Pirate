package com.company;

import java.util.*;

import java.util.Scanner;

public class Main {

    public static String toString(String txt, int index) {
        return txt.split("-")[index];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("entrer un nombre de pirate n inferieur à 26 ");
        n = sc.nextInt();

        Graphe monGraph = new Graphe(n);

        System.out.println("1) Ajouter une relation \n 2) Ajouter des préférences \n 3) fin");

        Scanner sc2 = new Scanner(System.in);

        System.out.println("Choisir 1 ou 2 ou 3");

        int choice = 0;

        while (choice != 1 && choice != 2 && choice != 3) {
            choice = sc.nextInt();

        }
        System.out.println(choice);

        switch (choice) {
            case 1:
                System.out.println("Donner le nombre de COUPLE de pirates qui ne s'aiment pas");
                Scanner sc3 = new Scanner(System.in);
                int nbr = sc3.nextInt();
                Scanner sc4 = new Scanner(System.in);
                System.out.println("Entrer le couple de pirates. Exemple: A-B");

                for (int i = 0; i < nbr; i++) {
                    String couple = sc4.nextLine();
                    monGraph.addArc(toString(couple, 0), toString(couple, 1));
                }
                System.out.println("1) Ajouter une relation \n 2) Ajouter des préférences \n 3) fin");
                //revenir au menu precédent.
                break;
            case 2:
                System.out.println("Entrer la liste de préférence des " + n + " pirates");
                while (n != 0) {
                    System.out.println("Entrer le nom du pirate");
                    Scanner sc5 = new Scanner(System.in);

                    Noeud pirate = monGraph.getNoeud(sc5.nextLine());

                    System.out.println("Entrer les préférences");

                    Scanner sc6 = new Scanner(System.in);

                    String pref = sc6.nextLine();

                    Scanner sc7 = new Scanner(pref);

                    if (pirate.getPreferences().isEmpty()) {
                        while (sc7.hasNext()) {
                            pirate.setPreferences(sc7.nextInt());
                        }
                        for (int l : pirate.getPreferences()) {
                            System.out.println(l);
                        }
                        n--;
                    }
                    break;
                }
            case 3:
                for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

                    Noeud noeud = (Noeud) mapentry.getValue();
                    if (noeud.getPreferences().isEmpty()) {
                        System.out.println("le pirate "+mapentry.getKey() + "n'a pas de liste de préférence");
                        //Renvoyer vers le menu
                    }
                }


        }


/*
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



        monGraph.addNoeud(d);
        monGraph.addNoeud(e);
        monGraph.addNoeud(f);
        monGraph.addArc("A", "B");
        monGraph.addArc("B", "C");


        System.out.println(monGraph);*/


    }
}
