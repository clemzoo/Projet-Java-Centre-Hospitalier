package ActionTypes;

import Main.Connexion;
import Graphisms.displaySQLQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddElements extends JFrame {

        private final JButton valider, annuler;
        private JPanel panSuccess;
        private JLabel bravo,image, ajoutSucces, metier, doc, inf, rot, sal, mala, chambr, li, numdoc;
        private JComboBox chooseTab;
        private JLabel [] chooseColonne, chooseColonneOld;
        private JTextField [] newEl, oldEl;
        private JTextField docteur, infi, rota, sala, malad, chamb, lit, code_service, numdocteur;
        private JRadioButton [] profess;
        private String [] myColumns , tab;
        private String choiceTab, finalColonne, colonne, sqlP2;
        private int oldLength = 0;
        private displaySQLQuery sql;
        private Connexion conne;
        private boolean oldEmp=false, oldCham=false;
        private ButtonGroup buttonGroup;
        private JLabel etat;
        private JRadioButton[] eta;
        private boolean oldMalade=false, doctor=false, nurse=false, heald = false, hospitalized =false;

    public AddElements(Connexion connexion){
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSuccess = new JPanel(); // instancier le panneau

        conne = connexion;

        image = new JLabel(new ImageIcon("hopital.jpg"));

        tab = new String[] {"None","service", "chambre", "employe", "malade"};

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
                        finalColonne += "'" + newEl[i].getText() + "', ";
                        colonne += chooseColonne[i].getText().substring(0, chooseColonne[i].getText().length()-2) + ", ";

                        nbElem++;
                    }
                }

                if(finalColonne != ""){
                    finalColonne = finalColonne.substring(0,finalColonne.length()-2);
                    colonne = colonne.substring(0,colonne.length()-2);
                    connexion.ajoutDTBsimple(choiceTab, colonne, finalColonne);

                    if(choiceTab.equals("service"))
                        panSuccess = succesfullConnexion (true, true, true,true,false,false,false);

                    if(choiceTab.equals("chambre"))
                        panSuccess = succesfullConnexion (true, true, true,false,true,false,false);

                    if(choiceTab.equals("employe")) {
                        panSuccess = succesfullConnexion(true, true, true, false, false, true, false);
                        if (doctor){
                            sqlP2 = "INSERT INTO docteur VALUES ('" + newEl[4].getText() + "', '" + docteur.getText() + "');";
                            connexion.ajoutDTBALaMano(sqlP2);
                        }
                        if (nurse){
                            sqlP2 = "INSERT INTO infirmier VALUES ('" + newEl[4].getText() + "', '" + infi.getText()+"', '"+ rota.getText() + "', '" + sala.getText() + "');";
                            connexion.ajoutDTBALaMano(sqlP2);
                        }
                    }

                    if(choiceTab.equals("malade")){
                        panSuccess = succesfullConnexion (true, true, true,false,false,false,true);
                        if (heald){
                            sqlP2 = "INSERT INTO soigne VALUES ('" + numdocteur.getText() + "', '" + newEl[5].getText() + "');";
                            connexion.ajoutDTBALaMano(sqlP2);
                        }
                        if (hospitalized){
                            sqlP2 = "INSERT INTO hospitalisation VALUES ('" + newEl[5].getText() + "', '" + malad.getText()+"', '"+ chamb.getText() + "', '" + lit.getText() + "');";
                            connexion.ajoutDTBALaMano(sqlP2);
                        }
                    }
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

        try {
            inf.setVisible(false);
            infi.setVisible(false);
            rot.setVisible(false);
            rota.setVisible(false);
            sal.setVisible(false);
            sala.setVisible(false);
            docteur.setVisible(false);
            doc.setVisible(false);
            mala.setVisible(false);
            malad.setVisible(false);
            chambr.setVisible(false);
            chamb.setVisible(false);
            li.setVisible(false);
            lit.setVisible(false);
            numdoc.setVisible(false);
            numdocteur.setVisible(false);
        } catch (Exception e1) {
            System.out.println(e1);
        }

        if(oldEmp){
            profess[0].setVisible(false);
            profess[1].setVisible(false);
            metier.setVisible(false);
        }
        if(oldMalade){
            eta[0].setVisible(false);
            eta[1].setVisible(false);
            etat.setVisible(false);
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
            code_service = new JTextField();

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

            profess[0] = new JRadioButton ("docteur",false);
            profess[1] = new JRadioButton ("infirmier",false);
            profess[0].setLocation(190,365);
            profess[0].setSize(90,35);
            profess[1].setLocation(190+90,365);
            profess[1].setSize(90,35);
////
            profess[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                            nurse = false;
                        try {
                            inf.setVisible(false);
                            infi.setVisible(false);
                            rot.setVisible(false);
                            rota.setVisible(false);
                            sal.setVisible(false);
                            sala.setVisible(false);
                        } catch (Exception e1) {
                            System.out.println(e1);
                        }

                        if (profess[0].isSelected()) {

                            doc = new JLabel("spécialité :");
                            doc.setLocation(425, 258);
                            doc.setSize(100, 35);

                            docteur = new JTextField();
                            docteur.setLocation(525, 258);
                            docteur.setSize(180, 35);

                            doc.setVisible(true);
                            docteur.setVisible(true);
                            image.setVisible(false);
                            add(doc);
                            add(docteur);
                            add(image);

                            revalidate();
                            repaint();

                            doctor = true;
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
                            doctor =false ;

                            try {
                                docteur.setVisible(false);
                                doc.setVisible(false);
                            } catch (Exception e1) {
                                System.out.println(e1);
                            }

                            inf = new JLabel("code du service :");
                            inf.setLocation(425, 223);
                            inf.setSize(120, 35);
                            inf.setVisible(true);
                            infi = new JTextField();
                            infi.setLocation(525 + 15, 223);
                            infi.setSize(180, 35);
                            infi.setVisible(true);

                            rot = new JLabel("rotation :");
                            rot.setLocation(425, 223 + 35);
                            rot.setSize(100, 35);
                            rot.setVisible(true);
                            rota = new JTextField();
                            rota.setLocation(525 + 15, 223 + 35);
                            rota.setSize(180, 35);
                            rota.setVisible(true);

                            sal = new JLabel("salaire :");
                            sal.setLocation(425, 223 + 70);
                            sal.setSize(100, 35);
                            sal.setVisible(true);
                            sala = new JTextField();
                            sala.setLocation(525 + 15, 223 + 70);
                            sala.setSize(180, 35);
                            sala.setVisible(true);

                            image.setVisible(false);
                            add(inf);
                            add(infi);
                            add(rot);
                            add(rota);
                            add(sal);
                            add(sala);
                            add(image);

                            revalidate();
                            repaint();

                            nurse=true;
                        }
                    } catch (Exception ex) {

                        System.out.println(ex.getMessage());
                    }
                }
            });

///////////////////

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

        if (malade){
            etat = new JLabel("état :");
            etat.setLocation(80,400);
            etat.setSize(150,35);

            eta = new JRadioButton[2];

            eta[0] = new JRadioButton ("soigné",false);
            eta[1] = new JRadioButton ("hospitalisé",false);
            eta[0].setLocation(190,400);
            eta[0].setSize(90,35);
            eta[1].setLocation(190+90,400);
            eta[1].setSize(120,35);

            eta[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        hospitalized = false;

                        if (eta[0].isSelected()) {

                            try {
                                mala.setVisible(false);
                                malad.setVisible(false);
                                chambr.setVisible(false);
                                chamb.setVisible(false);
                                li.setVisible(false);
                                lit.setVisible(false);
                            } catch (Exception e1) {
                                System.out.println(e1);
                            }

                            numdoc = new JLabel("numero docteur :");
                            numdoc.setLocation(425, 258);
                            numdoc.setSize(140, 35);

                            numdocteur = new JTextField();
                            numdocteur.setLocation(525+40, 258);
                            numdocteur.setSize(180, 35);

                            numdoc.setVisible(true);
                            numdocteur.setVisible(true);
                            image.setVisible(false);
                            add(numdoc);
                            add(numdocteur);
                            add(image);

                            revalidate();
                            repaint();

                            heald =true;
                        }


                    } catch (Exception ex) {

                        System.out.println(ex.getMessage());

                    }
                }
            });

            eta[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        heald = false;

                        if (eta[1].isSelected()) {

                            try {
                                numdoc.setVisible(false);
                                numdocteur.setVisible(false);
                            } catch (Exception e1) {
                                System.out.println(e1);
                            }

                            mala = new JLabel("code du service :");
                            mala.setLocation(425, 223 + 35);
                            mala.setSize(130, 35);
                            mala.setVisible(true);
                            malad = new JTextField();
                            malad.setLocation(525 + 40, 223 +35);
                            malad.setSize(180, 35);
                            malad.setVisible(true);

                            chambr = new JLabel("numero de chambre :");
                            chambr.setLocation(425, 223 + 70);
                            chambr.setSize(140, 35);
                            chambr.setVisible(true);
                            chamb = new JTextField();
                            chamb.setLocation(525 + 40, 223 + 70);
                            chamb.setSize(180, 35);
                            chamb.setVisible(true);

                            li = new JLabel("lit :");
                            li.setLocation(425, 223 + 105);
                            li.setSize(100, 35);
                            li.setVisible(true);
                            lit = new JTextField();
                            lit.setLocation(525 + 40, 223 + 105);
                            lit.setSize(180, 35);
                            lit.setVisible(true);

                            image.setVisible(false);
                            add(mala);
                            add(malad);
                            add(chambr);
                            add(chamb);
                            add(li);
                            add(lit);
                            add(image);

                            revalidate();
                            repaint();

                            hospitalized = true;
                        }
                    } catch (Exception ex) {

                        System.out.println(ex.getMessage());
                    }
                }
            });

            buttonGroup = new ButtonGroup();

            buttonGroup.add(eta[0]);
            buttonGroup.add(eta[1]);

            this.add(etat);
            this.add(eta[0]);
            this.add(eta[1]);

            eta[0].setVisible(malade);
            eta[1].setVisible(malade);
            etat.setVisible(malade);

            oldMalade = true;
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
