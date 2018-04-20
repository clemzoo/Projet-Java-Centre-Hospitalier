package Reporting;

import Main.Connexion;

import javax.swing.*;
import java.awt.*;

public class Statistique extends JFrame {
    private JPanel fen; // panneau
    private JPanel gauche;
    private JPanel droite;
    private JPanel droite_haut;
    private JPanel droite_bas;
    private JLabel bgauche;
    private JLabel bdr;
    private JLabel hdr;
    private JLabel total_malade_lab;
    private JLabel personel_lab;
    //private Connexion connect;

    public Statistique(){
       // connect = a;
        setTitle("Statistique de la BDD");
        setSize(600, 400);

        fen = new JPanel();
        gauche = new JPanel();
        bgauche = new JLabel("Nombre de patients par service :");
        droite = new JPanel();
        droite_bas = new JPanel();
        hdr = new JLabel("Masse salariale par service :");
        droite_haut = new JPanel();
        bdr = new JLabel("Statistiques :");
        total_malade_lab = new JLabel("Nb malades : ");
        personel_lab = new JLabel("Nb personnel : ");

        droite_bas.setLayout(new GridLayout(3,1));
        droite.setLayout(new GridLayout(2,1));
        fen.setLayout(new GridLayout(1,2));

        droite_haut.add(hdr);
        droite_bas.add(bdr);
        droite_bas.add(total_malade_lab);
        droite_bas.add(personel_lab);
        droite.add(droite_haut);
        droite.add(droite_bas);
        gauche.add(bgauche);
        fen.add(gauche);
        fen.add(droite);
        this.setContentPane(fen);
        this.setVisible(true);

    }



}
