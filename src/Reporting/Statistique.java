package Reporting;

import Main.Connexion;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class Statistique extends JFrame {
    private JPanel fen; // panneau
    private JPanel gauche;
    private JPanel droite;
    private JPanel droite_haut;
    private JPanel droite_bas;
    private JLabel bgauche;
    private JLabel hdr;
    private JLabel total_malade_lab;
    private JLabel personel_lab;
    private Connexion connect;

    public Statistique(Connexion a) throws SQLException {
        connect = a;
        setTitle("Statistique de la BDD");
        setSize(600, 400);

        fen = new JPanel();
        gauche = new JPanel();
        bgauche = new JLabel("Nombre de patients par service :");
        droite = new JPanel();
        droite_bas = new JPanel();
        hdr = new JLabel("Masse salariale par service :");
        droite_haut = new JPanel();
        total_malade_lab = new JLabel("Nb malades : ");
        personel_lab = new JLabel("Nb personnel : ");

        droite_bas.setLayout(new GridLayout(3,1));
        droite.setLayout(new GridLayout(2,1));
        fen.setLayout(new GridLayout(1,2));

        droite_haut.add(hdr);
        droite_bas.add(total_malade_lab);
        droite_bas.add(personel_lab);
        droite.add(droite_haut);
        droite.add(droite_bas);
        gauche.add(bgauche);
        fen.add(gauche);
        fen.add(droite);


        RequeteStat();


        this.setContentPane(fen);
        this.setVisible(true);

    }

    public void RequeteStat() throws SQLException {
        ResultSet resultat1 = connect.getStmt().executeQuery( "SELECT COUNT(*) AS total FROM malade");
        resultat1.next();
        resultat1.getInt("total");
        total_malade_lab.setText("Nb malades : " + resultat1.getInt("total"));


        ResultSet resultat2 = connect.getStmt().executeQuery( "SELECT COUNT(*) AS total FROM employe");
        resultat2.next();
        resultat2.getInt("total");
        personel_lab.setText("Nb personnel : " + resultat2.getInt("total"));
    }



}
