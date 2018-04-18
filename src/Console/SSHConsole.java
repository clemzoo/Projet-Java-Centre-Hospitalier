package Console;


import Main.Connexion;

import java.sql.*;
import java.util.Scanner;

public class SSHConsole {

    private Connexion connectSQL;
    private String myChoice;

    public void choix()

    {
        boolean run2 = true;

        do {
            char choice = menu2();

            switch (choice) {
                case 'a':
                    myChoice = new String("chambre");
                    String [] myColumns = new String[4];
                    myColumns = connectSQL.getColumnValues(myChoice);

                    for (int i = 0; i<myColumns.length;i++)
                    System.out.println(myColumns[i]);
                    //afficherColonnes(myColumns);
                    //run2 = false;
                    break;

                case 'b':
                    myChoice = new String("Dept");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'c':
                    myChoice = new String("docteur");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'd':
                    myChoice = new String("Emp");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'e':
                    myChoice = new String("employe");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'f':
                    myChoice = new String("hospitalisation");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'g':
                    myChoice = new String("infirmier");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'h':
                    myChoice = new String("malade");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'i':
                    myChoice = new String("Mission");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'j':
                    myChoice = new String("service");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case 'k':
                    myChoice = new String("soigne");
                    connectSQL.getColumnValues(myChoice);
                    run2 = false;
                    break;

                case '0':
                    run2 = false;
                    System.exit(0);
                    break;

                default:
                    System.out.println("Erreur de choix");
            }
        } while (run2);
    }

    public static char menu2() {//pour console

        String choix;
        System.out.println("a Chambre");
        System.out.println("b Departement");
        System.out.println("c Docteur");
        System.out.println("d Employé");
        System.out.println("e Employé bis");
        System.out.println("f Hospitalisation");
        System.out.println("g Infirmier");
        System.out.println("h Malade");
        System.out.println("i Mission");
        System.out.println("j Service");
        System.out.println("k Soigné");

        System.out.println("\n0 Quitter\n");

        Scanner sc = new Scanner(System.in);
        choix = sc.next();

        System.out.println("\n");

        return choix.charAt(0);
    }

    public void connexionSSH(){//pour console
        //Connexion serveur
        Scanner saisieClavier = new Scanner(System.in);

        System.out.println("\nVeuillez entrer votre identifiant ECE :");
        String usernameECE = saisieClavier.nextLine();

        System.out.println("\nVotre mot de passe ECE :");
        String passwordECE = saisieClavier.nextLine();

        System.out.println("\nVotre identifiant SQL:");
        String usernameSQL = usernameECE + "-rw";
        System.out.println(usernameSQL);

        System.out.println("\nVotre mot de passe SQL:");
        String passwordSQL = saisieClavier.nextLine();

        try {
            connectSQL = new Connexion(usernameECE, passwordECE, usernameSQL, passwordSQL);
        } catch (Exception  ex){
            System.out.println(ex.getMessage());
        }
    }

    public void afficherColonnes(String [] myColumns){
        Scanner saisieClavier = new Scanner(System.in);

        System.out.println("\nVeuillez choisir les colonnes a traiter :");

        for(int i = 0; i<myColumns.length; i++){
            System.out.println("\n" + (i+1) + myColumns[i]);
        }

        String choiceOfColonnes = saisieClavier.nextLine();

        System.out.println( choiceOfColonnes );
    }
}