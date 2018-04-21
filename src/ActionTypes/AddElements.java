package ActionTypes;

import Main.Connexion;
import Graphisms.displaySQLQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddElements extends JFrame {

        private final JButton valider, annuler;
        private JPanel panSuccess;
        private JLabel bravo,image, ajoutSucces, metier, doc, inf;
        private JComboBox chooseTab, code_service;
        private JLabel [] chooseColonne, chooseColonneOld;
        private JTextField [] newEl, oldEl ;
        private JRadioButton [] profess;
        private String [] myColumns , tab;
        private String choiceTab, finalColonne, colonne;
        private int oldLength = 0;
        private displaySQLQuery sql;
        private Connexion conne;
        private boolean oldEmp=false, oldCham=false;
        private ButtonGroup buttonGroup;

    public AddElements(Connexion connexion){
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSuccess = new JPanel(); // instancier le panneau

        conne = connexion;

        image = new JLabel(new ImageIcon("hopital.jpg"));

        tab = new String[] {"service", "chambre", "employe", "malade"};

        chooseTab = new JComboBox(tab);
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

        panSuccess = succesfullConnexion(true,false, false, false,false,false,false);
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

                    if(choiceTab.equals("service"))
                        panSuccess = succesfullConnexion (true, true, false,true,false,false,false);
                    if(choiceTab.equals("chambre"))
                        panSuccess = succesfullConnexion (true, true, false,false,true,false,false);
                    if(choiceTab.equals("employe"))
                        panSuccess = succesfullConnexion (true, true, false,false,false,true,false);
                    if(choiceTab.equals("malade"))
                        panSuccess = succesfullConnexion (true, true, false,false,false,false,true);

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

                    if(choiceTab.equals("service"))
                        panSuccess = succesfullConnexion (true, true, true,true,false,false,false);
                    if(choiceTab.equals("chambre"))
                        panSuccess = succesfullConnexion (true, true, true,false,true,false,false);
                    if(choiceTab.equals("employe"))
                        panSuccess = succesfullConnexion (true, true, true,false,false,true,false);
                    if(choiceTab.equals("malade"))
                        panSuccess = succesfullConnexion (true, true, true,false,false,false,true);
                }
            }
        });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panSuccess = succesfullConnexion(false,false, false, false, false, false,false);
            }
        });

        try {

            profess[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        if (buttonGroup.isSelected(profess[0].getModel())) {
                            doc = new JLabel("spécialité :");
                            doc.setLocation(550, 300);
                            doc.setSize(100, 35);
                            doc.setVisible(true);
                            panSuccess.add(doc);
                            panSuccess = panSuccess;
                            panSuccess.setVisible(true);
                            repaint();
                        }

                    } catch (Exception ex) {

                        System.out.println(ex.getMessage());

                    }
                }
            });

        profess[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (profess[1].isSelected()) {

                        inf = new JLabel("spécialité :");
                        inf.setLocation(400, 300);
                        inf.setSize(100, 35);
                        inf.setVisible(true);
                        panSuccess.add(inf);
                        panSuccess.repaint();
                    }
                } catch (Exception ex) {

                    System.out.println(ex.getMessage());
                }
            }
        });
    } catch (Exception ex) {

        System.out.println(ex.getMessage());
    }
}

        private void afficherColonnes() {
        chooseColonne = new JLabel [myColumns.length];
        newEl = new JTextField [100];

        for (int i = 0; i < myColumns.length; i++) {
            chooseColonne[i] = new JLabel (myColumns[i]+ " :");
            newEl[i] = new  JTextField ("");
        }
    }

        public JPanel succesfullConnexion(boolean onAfficheouPas, boolean checkboxes, boolean succes, boolean service, boolean chambre, boolean employe, boolean malade) {


        this.add(bravo);
        this.add(chooseTab);

        if(oldEmp){
            profess[0].setVisible(false);
            profess[1].setVisible(false);
            metier.setVisible(false);
        }
        if(oldCham){
            code_service.setVisible(false);
        }

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
                chooseColonne[i].setLocation(80,190+(i*35));
                chooseColonne[i].setSize(150,35);

                newEl[i].setLocation(190,190+(i*35));
                newEl[i].setSize(180,35);

                this.add(chooseColonne[i]);
                this.add(newEl[i]);

                chooseColonne[i].setVisible(true);
                newEl[i].setVisible(true);

                oldEl[i] = newEl[i];
                chooseColonneOld[i] = chooseColonne[i];
            }
        }

        if (chambre){

            //code_service = new JComboBox(conne.getSpecificElem("code_service","chambre"));
            code_service = new JComboBox(new String []{"CAR", "CHG", "REA"});

            code_service.setLocation(190,295);
            code_service.setSize(180,35);
            newEl[3].setVisible(false);
            this.add(code_service);
            code_service.setVisible(chambre);

            oldCham = true;
        }

        if (employe){


            metier = new JLabel("métier :");
            metier.setLocation(80,365);
            metier.setSize(150,35);

            profess = new JRadioButton[2];

            profess[0] = new JRadioButton ("docteur",true);
            profess[1] = new JRadioButton ("infirmier",false);
            profess[0].setLocation(190,365);
            profess[0].setSize(90,35);
            profess[1].setLocation(190+90,365);
            profess[1].setSize(90,35);

            buttonGroup = new ButtonGroup();

            buttonGroup.add(profess[0]);
            buttonGroup.add(profess[1]);

            this.add(metier);
            this.add(profess[0]);
            this.add(profess[1]);

            profess[0].setVisible(employe);
            profess[1].setVisible(employe);
            metier.setVisible(employe);

            oldEmp =true;
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
