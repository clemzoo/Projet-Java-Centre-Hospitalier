package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphisme extends JFrame {

    private JPanel pan; // panneau
    private JFormattedTextField usernameECE, passwordECE, usernameSQL, passwordSQL;
    private JButton buttonSSH, buttonLocalhost;
    private JLabel welcomeL1, welcomeL2, userECE;
    private ImageIcon hopital;
    private boolean created;



private JLabel image;

  //  private JLabel image3;


    public Graphisme()
    {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        pan = new JPanel(); // instancier le panneau
        image = new JLabel(new ImageIcon("hopital.jpg"));

        welcomeL1 = new JLabel();
        welcomeL1.setLocation(200,60);
        welcomeL1.setText("Bienvenue dans le système de gestion de votre Centre Hospitalier");
        welcomeL1.setSize(500,20);

        welcomeL2 = new JLabel();
        welcomeL2.setLocation(260,80);
        welcomeL2.setText("Veuilliez choisir le type de connexion souhaitée :");
        welcomeL2.setSize(500,20);

        buttonSSH = new JButton();
        buttonSSH.setLocation(250, 500);
        buttonSSH.setText("SSH");
        buttonSSH.setSize(80,20);

        buttonLocalhost = new JButton();
        buttonLocalhost.setLocation(450, 500);
        buttonLocalhost.setText("Localhost");
        buttonLocalhost.setSize(80,20);

        usernameECE = new JFormattedTextField();
        userECE = new JLabel();
        passwordECE = new JFormattedTextField();
        usernameSQL = new JFormattedTextField();
        passwordSQL = new JFormattedTextField();

    }

    public void afficherMenu(){
        this.add(welcomeL1);
        this.add(welcomeL2);
        this.add(buttonSSH);
        this.add(buttonLocalhost);
        this.add(image);

        this.setVisible(true);
    }

    public void choixMenu(){
        buttonSSH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(welcomeL1);
                remove(welcomeL2);
                //remove(buttonSSH);
                remove(buttonLocalhost);
                remove(image);

                connectionSSHGraphique();

                this
            }
        });

        buttonLocalhost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionLocalhostGraphique();
            }
        });
    }

    public void connectionSSHGraphique() {
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 16b5a4450077faeae946b02364da642eb20d6fc3
        /*
        remove(pan);
        pan = new JPanel(); // instancier le panneau
>>>>>>> 16b5a4450077faeae946b02364da642eb20d6fc3

        userECE.setLocation(260, 130);
        userECE.setText("Indentifiant ECE :");
        userECE.setSize(100, 20);

        usernameECE.setLocation(380, 130);
        usernameECE.setSize(100, 20);

        add(userECE);
        add(usernameECE);

<<<<<<< HEAD
        //this.setVisible(true);
=======
        usernameECE.setLocation(30, 34);
        usernameECE.setSize(35, 78);
        */
<<<<<<< HEAD
>>>>>>> 16b5a4450077faeae946b02364da642eb20d6fc3
=======
>>>>>>> 16b5a4450077faeae946b02364da642eb20d6fc3
    }

    public void connectionLocalhostGraphique(){



    }
}
