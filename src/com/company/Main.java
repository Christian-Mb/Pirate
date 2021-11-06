package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static String toString(String txt, int index) {
        return txt.split("-")[index];
    }

    public static void exchangeObject(Graphe monGraph, String choice) {
        Pirate A = monGraph.getNoeud(toString(choice, 0));
        Pirate B = monGraph.getNoeud(toString(choice, 1));
        int temp = A.getObjectsAttibuated();
        A.setObjectsAttibuated(B.getObjectsAttibuated());
        B.setObjectsAttibuated(temp);

        for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

            Pirate pirate = (Pirate) mapentry.getValue();

            System.out.println(pirate.getId() + " : " + pirate.getObjectsAttibuated());
        }

    }

    public static void calculateCost(Graphe monGraph) {
        int cost = 0;

        for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

            Pirate pirate = (Pirate) mapentry.getValue();

            if (pirate.getObjectsAttibuated() != pirate.getPreferences().get(0)) {
                if (!pirate.getSucc().isEmpty()) {
                    for (Arc arc : pirate.getSucc()) {
                        Pirate p = arc.getCible();
                        if (p.getObjectsAttibuated() == pirate.getPreferences().get(0)) {
                            cost++;
                        }
                    }
                }
            }


        }
        System.out.println(monGraph);
        System.out.println("Voici le cout :" + cost);
    }

    public static void attibutionObject(Graphe monGraph) {
        List<Integer> attribuatedObjects = new ArrayList<>();
        for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

            Pirate pirate = (Pirate) mapentry.getValue();

            if (attribuatedObjects.isEmpty()) {
                int objects = pirate.getPreferences().get(0);
                attribuatedObjects.add(objects);
                pirate.setObjectsAttibuated(objects);
            } else {
                List<Integer> test = pirate.getPreferences();

                for (int k : test) {

                    for (int i = 0; i < attribuatedObjects.size(); i++) {
                        if (!attribuatedObjects.contains(k) && pirate.getObjectsAttibuated() == 0) {
                            pirate.setObjectsAttibuated(k);
                            attribuatedObjects.add(k);
                        }
                    }

                }
            }

        }
    }

    public static void menu2(Graphe monGraph) {

        boolean menuu = false;

        while (menuu == false) {
            System.out.println("1) échanger objets \n 2) afficher coût \n 3) fin");

            Scanner scanner1 = new Scanner(System.in);
            int c = 0;
            while (c != 1 && c != 2 && c != 3) {
                System.out.println("Choisir 1 ou 2 ou 3");
                c = scanner1.nextInt();
            }

            switch (c) {

                case 1:
                    System.out.println("Entrez le couple de pirates dont vous souhaitez echanger les objets. Exemple: A-B");
                    Scanner sc = new Scanner(System.in);
                    String couple = sc.nextLine();
                    exchangeObject(monGraph, couple);

                    break;
                case 2:
                    //calculer le cout de la solution actuelle
                    calculateCost(monGraph);
                    for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

                        Pirate pirate = (Pirate) mapentry.getValue();

                        System.out.println(pirate.getId() + " : " + pirate.getObjectsAttibuated());


                    }
                    break;

                case 3:
                    System.out.println("Fin du jeu :)");


            }


        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Entrer un nombre de pirate n inferieur à 26 ");
        n = sc.nextInt();

        Graphe monGraph = new Graphe(n);

        boolean menu = false;

        while (menu == false) {
            System.out.println("1) Ajouter une relation \n 2) Ajouter des préférences \n 3) fin");

            Scanner sc2 = new Scanner(System.in);

            System.out.println("Choisir 1 ou 2 ou 3");

            int choice = 0;

            while (choice != 1 && choice != 2 && choice != 3) {
                choice = sc.nextInt();

            }

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
                    break;
                case 2:
                    System.out.println("Entrer la liste de préférence des " + n + " pirates");
                    while (n != 0) {
                        System.out.println("Entrer le nom du pirate");
                        Scanner sc5 = new Scanner(System.in);

                        Pirate pirate = monGraph.getNoeud(sc5.nextLine());

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

                    }
                    break;
                case 3:
                    boolean emptyPrefList = false;
                    for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

                        Pirate pirate = (Pirate) mapentry.getValue();
                        if (pirate.getPreferences().isEmpty()) {
                            System.out.println("le pirate " + mapentry.getKey() + "n'a pas de liste de préférence, veuillez remplir la liste de pref");
                            emptyPrefList = true;
                        }
                    }
                    if (!emptyPrefList) {
                        menu = true;
                    }
                    break;

            }
        }

        attibutionObject(monGraph);

        for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

            Pirate pirate = (Pirate) mapentry.getValue();

            System.out.println(pirate.getId() + " : " + pirate.getObjectsAttibuated());
        }
        menu2(monGraph);

        //System.out.println(calculateCost(monGraph));
        for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

            Pirate pirate = (Pirate) mapentry.getValue();

            System.out.println(pirate.getId() + " : " + pirate.getObjectsAttibuated());




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
}
