package Console;

import Main.Connexion;

import java.util.Scanner;

public class localhostConsole {
    private static Connexion connectLocalhost;
    private static String choix;
    private String myChoice, request, finalRequest, exChoice = "";
    private static String [] myTables;
    private String [] myColumns;
    private int nbElements = 0;

    public void choix()

    {
        boolean run2 = true;

        do {
            menu2();

            if(Integer.parseInt(choix) == 0){
                System.exit(0);
                break;
            }
            else {
                try {
                    System.out.println(myTables[Integer.parseInt(choix) - 1]);

                    myChoice = new String(myTables[Integer.parseInt(choix) - 1]);

                    myColumns = new String[connectLocalhost.getColumnValues(myChoice).length];
                    myColumns = connectLocalhost.getColumnValues(myChoice);
                    afficherColonnes(myColumns);
                    connectLocalhost.readDTB(myChoice, finalRequest, nbElements, false);

                    run2 = false;
                    break;
                } catch (Exception ex) {
                    System.out.println("Erreur de saisie : " + ex.getMessage());
                }
            }
        } while (run2);
    }

    public static char menu2() {//pour console

        myTables = new String[12];//jusqu'a 12 tables (arbitraire)
        myTables = connectLocalhost.getTablesNames();

        System.out.println("\n0 Quitter\n");

        Scanner sc = new Scanner(System.in);
        choix = sc.next();

        System.out.println("\n");

        return choix.charAt(0);
    }

    public void connexionLocalhost(){//pour console
        //Connexion serveur
        Scanner saisieClavier = new Scanner(System.in);

        System.out.println("\nVeuilliez entrer le nom de votre base de donnée :");
        String DBNAME = "hopital"; //saisieClavier.nextLine();
        System.out.println(DBNAME);

        System.out.println("\nVotre identifiant SQL :");
        String DBUSER = "root"; //saisieClavier.nextLine();
        System.out.println(DBUSER);

        System.out.println("\nVotre mot de passe SQL:");
        String DBPW = saisieClavier.nextLine();
        System.out.println("\n");

        try {
            connectLocalhost = new Connexion(DBNAME,DBUSER,DBPW);
        } catch (Exception  ex){
            System.out.println(ex.getMessage());
        }
    }

    public void afficherColonnes(String [] myColumns) {
        Scanner saisieClavier = new Scanner(System.in);
        int choice =1;

       while (choice != 0) {
           System.out.println("\nVeuillez choisir les colonnes a traiter :");

           for (int i = 0; i < myColumns.length; i++) {
               System.out.println("\n" + (i + 1) + " " + myColumns[i]);
           }
           System.out.println("\n");

           System.out.println("0 Pour finir la saisie");

           System.out.println("\n");

           //String choiceOfColonnes = saisieClavier.next();
           String choiceOfColonnes = saisieClavier.nextLine();

           choice = 0;
           try {
               choice = Integer.parseInt(choiceOfColonnes);
           } catch (Exception ex) {
               System.out.println("Erreur de saisie");
           }

           if (choice != 0 && !exChoice.contains(choiceOfColonnes)) {

               request = myColumns[choice - 1];
               System.out.println(request);
               finalRequest += request + ", ";
               nbElements += 1;
               exChoice += choice;

           } else if (choice == 0){

               finalRequest = finalRequest.substring(0,finalRequest.length()-2);
               finalRequest = finalRequest.substring(4,finalRequest.length());
               System.out.println("\n" + finalRequest);

               System.out.println("Fin de la requete\n");
           }

       }

    }

}