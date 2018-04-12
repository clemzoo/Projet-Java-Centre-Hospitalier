package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SSH extends JFrame {

    private JPanel panSSH;
    private JLabel usernameECE, passwordECE, usernameSQL, passwordSQL, affich, image;
    private JFormattedTextField textuserECE, textuserSQL;
    private JPasswordField textpwECE, textpwSQL;
    private JButton valider, annuler;
    private String DBUSERECE,DBUSERSQL,DBPWECE, DBPWSQL;
    private boolean onAfficheouPas;
    private SuccesfullConnexion bienOuej;

    public SSH() {

        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSSH = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        //Instanciation des JLabel
        usernameECE = new JLabel();
        passwordECE = new JLabel();
        usernameSQL = new JLabel();
        passwordSQL = new JLabel();
        affich = new JLabel();

        //Instanciation des Textfield
        textuserECE = new JFormattedTextField();
        textpwECE = new JPasswordField();
        textuserSQL = new JFormattedTextField();
        textpwSQL = new JPasswordField();

        //Instanciation des Boutons
        valider = new JButton();
        annuler = new JButton();

        //Users ECE
        usernameECE.setLocation(200, 150);
        usernameECE.setText("Username ECE :");
        usernameECE.setSize(100, 35);

        textuserECE.setSize(200, 35);
        textuserECE.setLocation(320, 150);

        //Password ECE
        passwordECE.setLocation(200, 225);
        passwordECE.setText("Password ECE :");
        passwordECE.setSize(120, 35);

        textpwECE.setSize(200, 35);
        textpwECE.setLocation(320, 225);

        //User SQL
        usernameSQL.setLocation(200, 300);
        usernameSQL.setText("Username SQL :");
        usernameSQL.setSize(100, 35);

        textuserSQL.setSize(200, 35);
        textuserSQL.setLocation(320, 300);

        //Password SQL
        passwordSQL.setLocation(200, 375);
        passwordSQL.setText("Password SQL :");
        passwordSQL.setSize(100, 35);

        textpwSQL.setSize(200, 35);
        textpwSQL.setLocation(320, 375);

        //Bouton Validation
        valider.setLocation(450, 500);
        valider.setText("Valider");
        valider.setSize(100, 35);

        //Bouton Annuler
        annuler.setLocation(250, 500);
        annuler.setText("Annuler");
        annuler.setSize(100, 35);


        affich.setLocation(330, 50);
        affich.setSize(200, 35);
        affich.setText("Connexion via SSH");

    }

    public JPanel connectionSSHGraphique(boolean onAfficheouPas) {

        this.add(affich);

        this.add(usernameECE);
        this.add(textuserECE);

        this.add(passwordECE);
        this.add(textpwECE);

        this.add(usernameSQL);
        this.add(textuserSQL);

        this.add(passwordSQL);
        this.add(textpwSQL);

        this.add(valider);
        this.add(annuler);

        this.add(image);
        image.setVisible(false);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSSH;
    }

    public void getFields(){

        class ConnexionThread extends Thread {
            public void run()
            {
                try {
                    Connexion connectSQL = new Connexion(DBUSERECE, DBPWECE, DBUSERSQL, DBPWSQL);
                    if(connectSQL.coco()){
                        panSSH = connectionSSHGraphique(false);
                        bienOuej = new SuccesfullConnexion(connectSQL);
                        bienOuej.setCheckbox(connectSQL);
                    }
                }
                catch (Exception  ex){
                    System.out.println(ex.getMessage());
                }
            }
        }

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textuserSQL.setText(textuserECE.getText() + "-rw");


                DBUSERECE= textuserECE.getText();
                DBUSERSQL = textuserSQL.getText();
                DBPWECE = new String(textpwECE.getPassword());
                DBPWSQL = new String(textpwSQL.getPassword());

                new ConnexionThread().start();
            }
        });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panSSH = connectionSSHGraphique(false);
            }
        });

        textuserECE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textuserSQL.setText(textuserECE.getText() + "-rw");
                panSSH = connectionSSHGraphique(true);
            }
        });

    }
}