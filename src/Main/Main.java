package Main;

import Console.SSHConsole;
import Console.localhostConsole;
import Graphisms.*;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static Graphisme mainWindow;
    public static SSHConsole sshConsole;
    public static Console.localhostConsole localhostConsole;

    public static char menu() {//pour console

        System.out.println("\nBienvenue dans le projet Centre Hospitalier ! \n");

        String choix;
        System.out.println("1 Pour une connexion en SSH");
        System.out.println("2 Pour une connexion en localhost");
        System.out.println("3 Pour afficher le menu en mode graphique");
        System.out.println("\n0 Quitter\n");

        Scanner sc = new Scanner(System.in);
        choix = sc.next();

        return choix.charAt(0);
    }


    public static void connexionLocalhost(){//pour console

        Scanner saisieClavier = new Scanner(System.in);

        System.out.println("\nVeuilliez entrer le nom de votre base de donnée :");
        String nameDatabase = saisieClavier.nextLine();

        System.out.println("\nVotre identifiant SQL :");
        String loginDatabase = saisieClavier.nextLine();

        System.out.println("\nVotre mot de passe SQL:");
        String passwordDatabase = saisieClavier.nextLine();

        try {
            Connexion connectSQL = new Connexion(nameDatabase, loginDatabase, passwordDatabase);
        } catch (Exception  ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {

        boolean run = true;

        do {
            char choice = menu();

            switch (choice) {
                case '1': // Connection en SSH
                    sshConsole = new SSHConsole();
                    //Clean
                    sshConsole.connexionSSH();
                    //clean
                    sshConsole.choix();
                    //Clean
                    run = false;
                    break;

                case '2': // Connection en localhost
                    localhostConsole = new localhostConsole();
                    //Clean
                    localhostConsole.connexionLocalhost();
                    //clean
                    localhostConsole.choix();
                    //Clean
                    //connexionLocalhost();
                    run = false;
                    break;

                case '3': // Afficher le menu en Graphique
                    mainWindow = new Graphisme (); // instancier graphismes
                    mainWindow.afficherMenu(true);
                    //TypeRequete lol = new TypeRequete();
                    //lol.afficher(true);
                    mainWindow.choixMenu();

                    mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                    run =false;
                    break;

                case '0':
                    run = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Erreur de choix");
            }
        } while (run);
    }
}