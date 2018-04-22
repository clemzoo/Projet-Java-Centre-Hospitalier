package ActionTypes;

import Graphisms.displaySQLQuery;
import Main.Connexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchElements extends JFrame {

    private final JButton valider, annuler;
    private JPanel panSuccess;
    private JLabel bravo,image, searchSucces;
    private JComboBox chooseTab;
    private JLabel [] chooseColonne, chooseColonneOld;
    private JFormattedTextField newEl, oldEl ;
    private String [] myColumns;
    private String choiceTab, finalColonne, colonne;
    private int oldLength = 0;
    private displaySQLQuery sql;
    protected String lol ;
protected String  mdr;

    public SearchElements(Connexion connexion){
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSuccess = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        newEl = new JFormattedTextField();
    /*    chooseTab = new JComboBox(connexion.getTablesNames());
        chooseTab.setLocation(270,130);
        chooseTab.setSize(250,30);*/
        newEl.setSize(200, 35);
        newEl.setLocation(340, 150);

        bravo = new JLabel();
        bravo.setLocation(270, 80);
        bravo.setText("Veuillez saisir ce que vous recherchez :");
        bravo.setSize(500, 35);

        searchSucces = new JLabel();
        searchSucces.setLocation(260, 450);
        searchSucces.setText("Voici les résultats de la recherche!");
        searchSucces.setSize(500, 35);

        //Instanciation des Boutons
        valider = new JButton();
        annuler = new JButton();

        //Bouton Validation
        valider.setLocation(450, 500);
        valider.setText("Valider");
        valider.setSize(100, 35);

        //Bouton Annuler
        annuler.setLocation(250, 500);
        annuler.setText("Annuler");
        annuler.setSize(100, 35);

        panSuccess = succesfullConnexion(true,false, false);
    }
    public void setCheckbox(Connexion connexion) {

        newEl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                } catch (Exception ex) {

                    System.out.println(ex.getMessage());
                }
            }
        });

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               

            }});

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panSuccess = succesfullConnexion(false,false, false);
            }
        });

    }

    public JPanel succesfullConnexion(boolean onAfficheouPas, boolean checkboxes, boolean succes) {


        this.add(bravo);

        this.add(newEl);
        this.add(searchSucces);
        searchSucces.setVisible(succes);

        this.add(valider);
        this.add(annuler);

        this.add(image);
        image.setVisible(false);

        this.setVisible(true);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSuccess;
    }

    }
