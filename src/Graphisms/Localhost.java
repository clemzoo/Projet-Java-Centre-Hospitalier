package Graphisms;

import ActionTypes.BrowseElements;
import Main.Connexion;
import Reporting.Statistique;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Localhost extends Graphisme {

    private JPanel panLocalhost;
    private JLabel nomDatabase,  loginDatabase, passwordDatabase, affich, image;
    private JFormattedTextField textDB, textuserDB;
    private JPasswordField textpwDB;
    private JButton valider, annuler;
    private String DBNAME, DBUSER, DBPW;
    private BrowseElements bienOuej;
    private TypeRequete rech;
    private boolean onAfficheouPas;

    public Localhost()
    {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panLocalhost = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        //Instanciation des JLabel
        nomDatabase = new JLabel();
        loginDatabase = new JLabel();
        passwordDatabase = new JLabel();
        affich = new JLabel();

        //Instanciation des Textfield
        textDB = new JFormattedTextField();
        textuserDB = new JFormattedTextField();
        textpwDB = new JPasswordField();

        //Instanciation des Boutons
        valider = new JButton();
        annuler = new JButton();

        //Users ECE
        nomDatabase.setLocation(200, 150);
        nomDatabase.setText("Nom Database :");
        nomDatabase.setSize(100, 35);

        textDB.setSize(200, 35);
        textDB.setLocation(340, 150);

        //Password ECE
        loginDatabase.setLocation(200, 225);
        loginDatabase.setText("Login Database :");
        loginDatabase.setSize(120, 35);

        textuserDB.setSize(200, 35);
        textuserDB.setLocation(340, 225);

        //User SQL
        passwordDatabase.setLocation(200, 300);
        passwordDatabase.setText("Password Database :");
        passwordDatabase.setSize(150, 35);

        textpwDB.setSize(200, 35);
        textpwDB.setLocation(340, 300);

        //Bouton Validation
        valider.setLocation(450, 500);
        valider.setText("Valider");
        valider.setSize(100, 35);

        //Bouton Annuler
        annuler.setLocation(250, 500);
        annuler.setText("Annuler");
        annuler.setSize(100, 35);


        affich.setLocation(320, 50);
        affich.setSize(200, 35);
        affich.setText("Connexion via Localhost");
    }

    public JPanel connectionLocalhostGraphique(boolean onAfficheouPas) {

        this.add(affich);

        this.add(nomDatabase);
        this.add(textDB);

        this.add(loginDatabase);
        this.add(textuserDB);

        this.add(passwordDatabase);
        this.add(textpwDB);

        this.add(valider);
        this.add(annuler);

        this.add(image);
        image.setVisible(false);

        this.setVisible(onAfficheouPas);
        repaint();

        return panLocalhost;
    }

    public void getFields(){

        class ConnexionThread extends Thread {
            public void run()
            {
                try {
                    Connexion connectSQL = new Connexion(DBNAME,DBUSER,DBPW);
                    if(connectSQL.coco()){
                        rech = new TypeRequete(connectSQL);
                        rech.buttonAction();
                        /*
                        bienOuej = new SuccesfullConnexion(connectSQL);
                        panLocalhost = connectionLocalhostGraphique(false);
                        bienOuej.setCheckbox(connectSQL);

                        */
                        Statistique stat = new Statistique(connectSQL);
                    }
                }
                catch (Exception  ex){
                    System.out.println(ex.getMessage());
                }
            }
        }

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBNAME = "hopital";//textDB.getText();
                DBUSER = "root";//textuserDB.getText();
                DBPW = "root"; //new String(textpwDB.getPassword());

                new ConnexionThread().start();
            }
            });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panLocalhost = connectionLocalhostGraphique(false);
            }
        });

    }

}