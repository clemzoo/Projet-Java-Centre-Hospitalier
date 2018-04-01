package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuccesfullConnexion extends JFrame {

    private JPanel panSuccess;
    private JLabel bravo,image;
    private JComboBox chooseTab;
    private String[] elements;
    private String choiceTab, choiceTabReal;

    public SuccesfullConnexion(){
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSuccess = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        //My tabs
        String[] elements = new String[]{"Chambre", "Département", "Docteur", "Employé", "Employé bis","Hospitalisation", "Infirmier", "Malade", "Mission", "Service", "Soigné"};

        chooseTab = new JComboBox(elements);
        chooseTab.setLocation(260,150);
        chooseTab.setSize(250,200);

        bravo = new JLabel();
        bravo.setLocation(200, 120);
        bravo.setText("Bravo ! Tu as réussi à te connecter a la DTB de l'ECE Paris");
        bravo.setSize(500, 35);

        this.add(bravo);
        this.add(chooseTab);
        this.add(image);
        image.setVisible(false);

        this.setVisible(true);

    }

    public void setCheckbox() {

        chooseTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choiceTab = new String((String) chooseTab.getSelectedItem());//Convert to string
                System.out.println("Selected: " + choiceTab);
    ////Quelques problèmes de reconnaissance ici
                //Faire Matcher les noms avec ceux de la bdd
                if (choiceTab == "Département") {
                    choiceTabReal = new String("Dept");
                }

                if (choiceTab == "Docteur") {
                    choiceTab = "docteur";
                }

                if (choiceTab == "Employé") {
                    choiceTab = "Emp";
                }

                if (choiceTab == "Employé bis") {
                    choiceTab = "employe";
                }

                if (choiceTab == "Hospitalisation") {
                    choiceTab = "hospitalisation";
                }

                if (choiceTab == "Infirmier") {
                    choiceTab = "infirmier";
                }

                if (choiceTab == "Malade"){
                    choiceTab = "malade";
                }

                if(choiceTab=="Service") {
                    choiceTab = "service";
                }

                if(choiceTab=="Soigné"){
                    choiceTab = "soigne";
                }

                System.out.println("Selected: " + choiceTabReal);
            }
        });



    }

    public void SQLQueries (Connexion connection){
        connection.readDTB();
        //panSuccess = refreshWindow();
    }

   /**
    public JPanel refreshWindow(){
        bravo.setText();
        return panSuccess;
    }
    */
}