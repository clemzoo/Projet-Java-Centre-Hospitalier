package Main;

import oracle.jvm.hotspot.jfr.JFROptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphisme extends JFrame {

    private JPanel pan; // panneau
    private SSH SSH;
    private Localhost Localhost;
    private JButton buttonSSH, buttonLocalhost;
    private JLabel welcomeL1, welcomeL2, image;

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
        buttonSSH.setSize(100, 35);

        buttonLocalhost = new JButton();
        buttonLocalhost.setLocation(450, 500);
        buttonLocalhost.setText("Localhost");
        buttonLocalhost.setSize(100, 35);
    }

    public void afficherMenu(boolean onAfficheouPas){
        this.add(welcomeL1);
        this.add(welcomeL2);
        this.add(buttonSSH);
        this.add(buttonLocalhost);
        this.add(image);

        this.setVisible(onAfficheouPas);
    }

    public void choixMenu() {
        buttonSSH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SSH = new SSH();
                pan.add(SSH.connectionSSHGraphique(true));
                SSH.getFields();
            }
        });

        buttonLocalhost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Localhost = new Localhost();
                pan.add(Localhost.connectionLocalhostGraphique(true));
                Localhost.getFields();
            }
        });
    }
}