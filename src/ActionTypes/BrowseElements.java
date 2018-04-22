package ActionTypes;

//import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;

import Graphisms.TypeRequete;
import Graphisms.displaySQLQuery;
import Main.Connexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseElements extends JFrame {

    private final JButton valider, annuler;
    private JPanel panSuccess;
    private JLabel bravo,image;
    private JComboBox chooseTab;
    private JCheckBox [] chooseColonne, chooseColonneOld;
    private String [] myColumns;
    private String choiceTab, finalColonne;
    private int oldLength = 0;
    private displaySQLQuery sql;

    public BrowseElements(Connexion connexion){
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSuccess = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("consulter.jpg"));

        chooseTab = new JComboBox(connexion.getTablesNames());
        chooseTab.setLocation(270,130);
        chooseTab.setSize(250,30);

        bravo = new JLabel();
        bravo.setLocation(280, 80);
        bravo.setText("Veuillez composer votre recherche :");
        bravo.setSize(500, 35);

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

        panSuccess = succesfullConnexion(true,false);
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
                    panSuccess = succesfullConnexion (true, true);

                } catch (Exception ex) {

                    System.out.println(ex.getMessage());
                }
            }
        });

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalColonne = "";
                int nbElem = 0;

                for (int i = 0; i<myColumns.length; i++){
                    if (chooseColonne[i].isSelected()) {
                        finalColonne += chooseColonne[i].getText() + ", ";
                        nbElem++;
                    }
                }

                if(finalColonne != ""){
                    finalColonne = finalColonne.substring(0,finalColonne.length()-2);

                    sql = new displaySQLQuery(connexion, choiceTab,finalColonne, nbElem);
                    panSuccess = succesfullConnexion(false,false);

                }
            }
        });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panSuccess = succesfullConnexion(false,false);
            TypeRequete re = new TypeRequete(connexion);

            }
        });
    }

    private void afficherColonnes() {
        chooseColonne = new JCheckBox[myColumns.length];

        for (int i = 0; i < myColumns.length; i++) {
            chooseColonne[i] = new JCheckBox(myColumns[i]);
        }
    }

    public JPanel succesfullConnexion(boolean onAfficheouPas, boolean checkboxes) {


        this.add(bravo);
        this.add(chooseTab);

        if(checkboxes){

            if(oldLength != 0){

                for(int i = 0; i < oldLength; i++){
                    this.remove(chooseColonneOld[i]);
                }
            }

            oldLength = myColumns.length;
            chooseColonneOld = new JCheckBox[oldLength];

            for (int i = 0; i < myColumns.length; i++){
                chooseColonne[i].setLocation(330,180+(i*35));
                chooseColonne[i].setSize(150,35);

                this.add(chooseColonne[i]);
                chooseColonne[i].setVisible(true);
                chooseColonneOld[i] = chooseColonne[i];
            }
        }

        this.add(valider);
        this.add(annuler);

        this.add(image);
        image.setVisible(true);

        this.setVisible(true);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSuccess;
    }
}