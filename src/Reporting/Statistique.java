package Reporting;

import javax.swing.*;
import java.awt.*;

public class Statistique extends JFrame {
    private JPanel fen; // panneau
    private JPanel gauche;
    private JPanel droite;
    private JPanel droite_haut;
    private JPanel droite_bas;

    public Statistique(){
        setTitle("Statistique de la BDD");
        setSize(800, 300);
        fen = new JPanel();
        gauche = new JPanel();
        JButton bgauche = new JButton("gauche");
        gauche.add(bgauche);
        droite = new JPanel();
        droite_bas = new JPanel();
        JButton hdr = new JButton("haut droite");
        droite_haut = new JPanel();
        JButton bdr = new JButton("bas droite");
        droite_haut.add(hdr);
        droite_haut.add(bdr);
        droite.setLayout(new GridLayout(2,0));
        droite.add(droite_haut);
        droite.add(droite_bas);
        fen.setLayout(new GridLayout(0,2));
        fen.add(gauche);
        fen.add(droite);
        this.setVisible(true);

    }



}
