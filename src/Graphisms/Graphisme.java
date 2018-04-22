package Graphisms;

//<<<<<<< HEAD
//import oracle.jvm.hotspot.jfr.JFROptions;

//=======
//>>>>>>> 4a994440d41a3209f5863599272a76151f241354

import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphisme extends JFrame {

    private JPanel pan; // panneau
    private Graphisms.SSH SSH;
    private Graphisms.Localhost Localhost;
    private JButton buttonSSH, buttonLocalhost;
    private JLabel welcomeL1, welcomeL2, image;

    public Graphisme()
    {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        pan = new JPanel(); // instancier le panneau
        image = new JLabel(new ImageIcon("garde.jpg"));


        welcomeL1 = new JLabel();
        welcomeL1.setLocation(130,150);
        welcomeL1.setText("Bienvenue dans la gestion de votre Centre Hospitalier");
        welcomeL1.setSize(600,20);
        welcomeL1.setForeground(Color.DARK_GRAY);
        welcomeL1.setFont(new Font("Century",Font.PLAIN,18));

        welcomeL2 = new JLabel();
        welcomeL2.setLocation(130,430);
        welcomeL2.setText("Veuillez choisir le type de connexion souhaitée :");
        welcomeL2.setSize(600,20);
        welcomeL2.setForeground(Color.DARK_GRAY);
        welcomeL2.setFont(new Font("Century",Font.PLAIN,24));
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
        repaint();
    }

    public void choixMenu() {
        buttonSSH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SSH = new SSH();
                Main.mainWindow.afficherMenu(false);

                pan.add(SSH.connectionSSHGraphique(true));
                SSH.getFields();

            }
        });

        buttonLocalhost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Localhost = new Localhost();
                Main.mainWindow.afficherMenu(false);

                pan.add(Localhost.connectionLocalhostGraphique(true));
                pan.setVisible(false);
                Localhost.getFields();

                }
        });

    }
}