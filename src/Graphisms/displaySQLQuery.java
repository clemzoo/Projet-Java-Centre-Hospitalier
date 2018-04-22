package Graphisms;

import ActionTypes.BrowseElements;
import ActionTypes.SearchElements;
import Main.Connexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class displaySQLQuery extends JFrame {

    private final JLabel image;
    private final JLabel bravo;
    private JButton annuler;
    private JPanel panSQL;
    private JTable maTable;
    private BrowseElements bienOuej;
    private SearchElements chercher;

    public displaySQLQuery(Connexion connexion, String table, String colonne, int nbElem) {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSQL = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        bravo = new JLabel();
        bravo.setLocation(320, 80);
        bravo.setText("Resultat de votre recherche :");
        bravo.setSize(500, 35);

        //Bouton Annuler
        annuler = new JButton();
        annuler.setLocation(350, 500);
        annuler.setText("Retour");
        annuler.setSize(100, 35);

        panSQL = displayQueries(true, table, colonne, nbElem, connexion);

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                bienOuej = new BrowseElements(connexion);
                bienOuej.setCheckbox(connexion);

            }
        });
    }

    public displaySQLQuery(Connexion connexion, String choiceTab, String finalColonne, int nbElem, String affinage) {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSQL = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        bravo = new JLabel();
        bravo.setLocation(320, 80);
        bravo.setText("Resultat de votre recherche :");
        bravo.setSize(500, 35);

        //Bouton Annuler
        annuler = new JButton();
        annuler.setLocation(350, 500);
        annuler.setText("Retour");
        annuler.setSize(100, 35);

        panSQL = displayQueriesSearch(true, choiceTab, finalColonne, nbElem, connexion, affinage);

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                chercher = new SearchElements(connexion);
                chercher.setCheckbox(connexion);

            }
        });
    }

    public JPanel displayQueriesSearch(boolean onAfficheouPas, String table, String colonne, int nbElem, Connexion connexion, String affinage) {

        this.add(bravo);

        maTable = connexion.readDTBSearch(table, colonne, 5, true, affinage);
        JScrollPane scrollPane = new JScrollPane(maTable);
        scrollPane.setLocation(80,130);
        scrollPane.setSize(640,350);
        this.add(scrollPane);
        this.add(annuler);

        this.add(image);
        image.setVisible(false);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSQL;
    }

    public JPanel displayQueries(boolean onAfficheouPas, String table, String colonne, int nbElem, Connexion connexion) {

        this.add(bravo);

        maTable = connexion.readDTB(table, colonne, nbElem, true);
        JScrollPane scrollPane = new JScrollPane(maTable);
        scrollPane.setLocation(80,130);
        scrollPane.setSize(640,350);
        this.add(scrollPane);
        this.add(annuler);


        this.add(image);
        image.setVisible(false);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSQL;
    }

}