package Main;

import javax.swing.*;
import java.util.Scanner;
public class Main {
    public static Graphisme fen;
    public static Graphisme fen2;
    public static Graphisme fen3;
    public static char menu() {

        System.out.println("Bienvenue dans le projet Centre Hospitalier ! \n");

        String choix;
        System.out.println("1 Pour une connexion en SSH");
        System.out.println("2 Pour une connexion en localhost");
        System.out.println("3 Pour afficher le menu en mode graphique");
        System.out.println("\n0 Quitter");

        Scanner sc = new Scanner(System.in);
        choix = sc.next();

        return choix.charAt(0);
    }

    public static void connexionSSH(){
        //Connexion serveur
        Scanner saisieClavier = new Scanner(System.in);

        System.out.println("\nVeuillez entrer votre identifiant ECE :");
        String usernameECE = saisieClavier.nextLine();

        System.out.println("\nVotre mot de passe ECE :");
        String passwordECE = saisieClavier.nextLine();

        System.out.println("\nVotre identifiant SQL:");
        String usernameSQL = saisieClavier.nextLine();

        System.out.println("\nVotre mot de passe SQL:");
        String passwordSQL = saisieClavier.nextLine();

        try {
            Connexion connectSQL = new Connexion(usernameECE, passwordECE, usernameSQL, passwordSQL);
        } catch (Exception  ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void connexionLocalhost(){

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

        fen = new Graphisme (); // instancier graphismes
       // fen2 = new Localhost (); // instancier graphismes
fen3 = new SSH();
        boolean run = true;

        fen.setVisible (true);
        fen.afficherMenu();
        fen.choixMenu();

        fen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//fen2.connectionLocalhostGraphique();
fen3.connectionSSHGraphique();

        do {
            char choice = menu();

            switch (choice) {
                case '1': // Connection en SSH
                    fen.setVisible (true);
                    fen.afficherMenu();
                    connexionSSH();
                    run = false;
                    break;

                case '2': // Connection en localhost
                    fen.setVisible (true);
                    connexionLocalhost();
                    run = false;
                    break;

                case '3': // Afficher le menu
                    fen.setVisible (true);
                    run =false;
                    break;

                case '0':
                    fen.setVisible (true);
                    run = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Erreur de choix");
            }
        } while (run);


    }
}