package ActionTypes;

import Main.Connexion;
import Graphisms.displaySQLQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddElements extends JFrame {

        private final JButton valider, annuler;
        private JPanel panSuccess;
        private JLabel bravo,image, ajoutSucces;
        private JComboBox chooseTab;
        private JLabel [] chooseColonne, chooseColonneOld;
        private JTextField [] newEl, oldEl ;
        private String [] myColumns;
        private String choiceTab, finalColonne, colonne;
        private int oldLength = 0;
        private displaySQLQuery sql;

    public AddElements(Connexion connexion){
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSuccess = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        chooseTab = new JComboBox(connexion.getTablesNames());
        chooseTab.setLocation(270,130);
        chooseTab.setSize(250,30);

        bravo = new JLabel();
        bravo.setLocation(270, 80);
        bravo.setText("Veuillez composer votre nouvel élément :");
        bravo.setSize(500, 35);

        ajoutSucces = new JLabel();
        ajoutSucces.setLocation(260, 450);
        ajoutSucces.setText("L'élément vient d'être ajouté avec succès !");
        ajoutSucces.setSize(500, 35);

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

        chooseTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choiceTab = new String((String) chooseTab.getSelectedItem());//Convert to string
                System.out.println("Selected: " + choiceTab);

                try{

                    myColumns = new String[connexion.getColumnValues(choiceTab).length];
                    myColumns = connexion.getColumnValues(choiceTab);
                    afficherColonnes();
                    panSuccess = succesfullConnexion (true, true, false);

                } catch (Exception ex) {

                    System.out.println(ex.getMessage());
                }
            }
        });

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalColonne = "";
                colonne = "";
                int nbElem = 0;

                for (int i = 0; i<myColumns.length; i++){
                    if (newEl[i].getText() != "") {
                        finalColonne += newEl[i].getText() + ", ";
                        colonne += chooseColonne[i].getText().substring(0, chooseColonne[i].getText().length()-2) + ", ";

                        nbElem++;
                    }
                }

                if(finalColonne != ""){
                    finalColonne = finalColonne.substring(0,finalColonne.length()-2);
                    colonne = colonne.substring(0,colonne.length()-2);

                    connexion.ajoutDTB(choiceTab, colonne, finalColonne);

                    panSuccess = succesfullConnexion(true,true,true);

                }
            }
        });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panSuccess = succesfullConnexion(false,false, false);
            }
        });
    }

        private void afficherColonnes() {
        chooseColonne = new JLabel [myColumns.length];
        newEl = new JTextField [100];

        for (int i = 0; i < myColumns.length; i++) {
            chooseColonne[i] = new JLabel (myColumns[i]+ " :");
            newEl[i] = new  JTextField ("");
        }
    }

        public JPanel succesfullConnexion(boolean onAfficheouPas, boolean checkboxes, boolean succes) {


        this.add(bravo);
        this.add(chooseTab);

        if(checkboxes){

            if(oldLength != 0){

                for(int i = 0; i < oldLength; i++){
                    this.remove(chooseColonneOld[i]);
                    this.remove(oldEl[i]);
                }
            }

            oldLength = myColumns.length;
            chooseColonneOld = new JLabel [oldLength];
            oldEl = new JTextField [100];

            for (int i = 0; i < myColumns.length; i++){
                chooseColonne[i].setLocation(250,180+(i*35));
                chooseColonne[i].setSize(150,35);

                newEl[i].setLocation(360,180+(i*35));
                newEl[i].setSize(180,35);

                this.add(chooseColonne[i]);
                this.add(newEl[i]);

                chooseColonne[i].setVisible(true);
                newEl[i].setVisible(true);

                oldEl[i] = newEl[i];
                chooseColonneOld[i] = chooseColonne[i];
            }
        }

        this.add(ajoutSucces);
        ajoutSucces.setVisible(succes);

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
