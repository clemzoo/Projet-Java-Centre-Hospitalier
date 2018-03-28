package Main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Connexion serveur
        Scanner saisieClavier = new Scanner(System.in);

        System.out.println("Bienvenu dans le projet Centre Hospitalier ! \n\nVeuillez entrer votre identifiant :");
        String username = saisieClavier.nextLine();

        System.out.println("\n\nVotre mot de passe :");
        String password = saisieClavier.nextLine();

        SSHTunnel TestConnection = new SSHTunnel(username, password);

        //Test des données récupérées
        System.out.println("\n\n" + TestConnection.getUsername() + "\n\n" + TestConnection.getFirstHostPort() + "\n\n" + TestConnection.getSecondHostPort() );

    }
}

