package Main;


import java.sql.*;
import java.util.Scanner;

public class SSHConsole extends Main {

    public static void choix()

    {
        boolean run2 = true;

        do {
            char choice = menu2();

            switch (choice) {
                case '1':
                 /*   try {
                        /* Exécution d'une requête de lecture */
                      //  ResultSet resultat = stmt.executeQuery( "SELECT COLUMN_NAME FROM chambre;");
                        /* Récupération des données du résultat de la requête de lecture */

                   /*     while ( resultat.next() ) {
                            int numero = resultat.getInt( "numero" );
                            String specialite = resultat.getString( "specialite" );

                            System.out.println("Numéro : " + numero);
                            System.out.println("Spécialité : " + specialite);
                        }
                    } catch (Exception  ex){
                        System.out.println(ex.getMessage());
                    } */
                    run2 = false;
                    break;

                case '2':
                    run2 = false;
                    break;

                case '3':

                    run2 = false;
                    break;
                case '4':

                    run2 = false;
                    break;

                case '5':
                    run2 = false;
                    break;

                case '6':

                    run2 = false;
                    break;
                case '7':

                    run2 = false;
                    break;

                case '8':
                    run2 = false;
                    break;

                case '9':

                    run2 = false;
                    break;
                case 'a':

                    run2 = false;
                    break;

                case 'b':
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
        System.out.println("1 Chambre");
        System.out.println("2 Departement");
        System.out.println("3 Docteur");
        System.out.println("4 Employé");
        System.out.println("5 Employé bis");
        System.out.println("6 Hospitalisation");
        System.out.println("7 Infirmier");
        System.out.println("8 Malade");
        System.out.println("9 Mission");
        System.out.println("a Service");
        System.out.println("b Soigné");

        System.out.println("\n0 Quitter");

        Scanner sc = new Scanner(System.in);
        choix = sc.next();

        return choix.charAt(0);
    }
}