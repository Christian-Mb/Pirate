package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static String toString(String txt, int index) {
        return txt.split("-")[index];
    }

    public static String splitString(String txt, int index, String separator) {
        return txt.split(separator)[index];
    }

    public static Graphe exchangeObject(Graphe monGraph, String choice) {

        System.out.println("Choice" + choice);
        System.out.println("A" + monGraph);

        Pirate A = monGraph.getPirate(toString(choice, 0));
        Pirate B = monGraph.getPirate(toString(choice, 1));
        String temp = A.getObjectsAttibuated();
        A.setObjectsAttibuated(B.getObjectsAttibuated());
        B.setObjectsAttibuated(temp);


        for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

            Pirate pirate = (Pirate) mapentry.getValue();

            System.out.println(pirate.getId() + " : " + pirate.getObjectsAttibuated());
        }
        System.out.println("B" + monGraph);
        return monGraph;
    }


    public static int calculateCost(Graphe monGraph) {
        int cost = 0;

        for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

            Pirate pirate = (Pirate) mapentry.getValue();

            if (!Objects.equals(pirate.getObjectsAttibuated(), pirate.getPreferences().get(0))) {
                if (!pirate.getSucc().isEmpty()) {
                    for (Arc arc : pirate.getSucc()) {
                        Pirate p = arc.getCible();
                        if (Objects.equals(p.getObjectsAttibuated(), pirate.getPreferences().get(0))) {
                            cost++;
                        }
                    }
                }
            }


        }

        return cost;
    }

    public static Graphe automatisation(Graphe monGraph) {

        int i = 0;
        int k = monGraph.getPirates().size();

        Graphe A = solutionNaive(monGraph);
        int a = calculateCost(A);

        System.out.println("naive solution : " + A);

        while (i < k) {

            Pirate pirate = monGraph.getPirates().get(new Random().nextInt(k));

            if (!pirate.getSucc().isEmpty()) {
                Pirate neighbor = pirate.getSucc().get(0).getCible();

                StringBuilder sb = new StringBuilder();
                sb.append(pirate.getId()).append("-").append(pirate.getSucc().get(0).getCible().getId());


                Graphe C = exchangeObject(A, sb.toString());

                int b = calculateCost(C);

                System.out.println("cost : " + a + " - " + b);

                if (calculateCost(A) > calculateCost(C)) {
                    A = C;
                }
                i++;
            }
        }
        return A;
    }


    public static Graphe solutionNaive(Graphe monGraph) {
        List<String> attribuatedObjects = new ArrayList<>();

        for (Map.Entry mapentry : monGraph.getHmap().entrySet()) {

            Pirate pirate = (Pirate) mapentry.getValue();

            if (attribuatedObjects.isEmpty()) {
                String objects = pirate.getPreferences().get(0);
                attribuatedObjects.add(objects);
                pirate.setObjectsAttibuated(objects);

            } else {
                List<String> test = pirate.getPreferences();

                for (String k : test) {

                    for (int i = 0; i < attribuatedObjects.size(); i++) {
                        if (!attribuatedObjects.contains(k) && pirate.getObjectsAttibuated().isEmpty()) {
                            pirate.setObjectsAttibuated(k);
                            attribuatedObjects.add(k);
                        }
                    }

                }
            }

        }
        return monGraph;
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
                    System.out.println("Le coût est de : " + calculateCost(monGraph));
                    break;

                case 3:

                    menuu = true;
                    break;
            }
        }

    }

    public static String separator(String line) {
        String split = splitString(line, 1, "\\(");
        return splitString(split, 0, "\\)");
    }

    public static Graphe generateGraph(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            Graphe monGraph = new Graphe();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.startsWith("pirate")) {
                    monGraph.addPirate(separator(line));

                } else if (line.startsWith("objet")) {
                    monGraph.addObject(separator(line));
                } else if (line.startsWith("deteste")) {
                    monGraph.addArc(splitString(separator(line), 0, ","), splitString(separator(line), 1, ","));
                } else {
                    //preférences
                    String lineToSerialize = separator(line);
                    String[] prefs = lineToSerialize.split(",");
                    Pirate a = monGraph.getPirate(prefs[0]);
                    for (int i = 1; i < prefs.length; i++) {
                        a.setPreferences(prefs[i]);
                    }
                }
            }

            return monGraph;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void menu(Graphe graphe) {

        automatisation(graphe);
        System.out.println("\nLa solution automatique a été appliquée \n Pour récupérer les données sélectionner l'option sauvegarde");
    }


    public static void saveResult(Graphe graphe) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer un nom de fichier\n");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(sc.nextLine() + ".txt"));

            for (Pirate pirate : graphe.getPirates()) {
                writer.write(pirate.getId() + " : " + pirate.getObjectsAttibuated() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        System.out.println("Initialisation du graphe ...\n");

        Graphe monGraph = generateGraph("test.txt");

        System.out.println("Graphe initialisé\n");

        boolean menu = false;

        while (menu == false) {
            System.out.println("1) Résolution automatique \n2) Résolution manuelle \n3)Sauvegarde \n4) fin");

            Scanner sc = new Scanner(System.in);

            System.out.println("\nChoisir 1, 2, 3 ou 4");

            int choice = 0;

            while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                choice = sc.nextInt();

            }

            switch (choice) {
                case 1:
                    menu(monGraph);
                    break;
                case 2:
                    System.out.println("Résolution manuelle");

                    solutionNaive(monGraph);

                    for (Pirate pirate : monGraph.getPirates()) {
                        System.out.println(pirate.getId() + " : " + pirate.getObjectsAttibuated() + "\n");
                    }
                    menu2(monGraph);
                    break;
                case 3:
                    saveResult(monGraph);
                    break;
                case 4:
                    System.out.println("\nFin du jeu, merci pour votre participation");
                    menu = true;
                    break;

            }
        }
    }
}
