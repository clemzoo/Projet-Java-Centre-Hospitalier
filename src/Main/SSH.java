package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SSH extends Graphisme {

    private JPanel pan;
    public JLabel usernameECE, passwordECE, usernameSQL, passwordSQL, affich;
    private JTextField textuserECE, textpwECE, textuserSQL, textpwSQL;
    private JButton valider;

    public SSH() {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 800);
        setLocation(425, 200);
        pan = new JPanel(); // instancier le panneau
        //pan.setLayout(null);
        usernameECE = new JLabel();
        passwordECE = new JLabel();
        usernameSQL = new JLabel();
        passwordSQL = new JLabel();
        affich = new JLabel();
        textuserECE = new JTextField();
        textpwECE = new JTextField();
        textuserSQL = new JTextField();
        textpwSQL = new JTextField();
        valider = new JButton();

        usernameECE.setLocation(100, 100);
        usernameECE.setText("Username ECE :");
        usernameECE.setSize(100, 35);


        passwordECE.setLocation(100, 200);
        passwordECE.setText("Password ECE :");
        passwordECE.setSize(120, 35);

        usernameSQL.setLocation(100, 300);
        usernameSQL.setText("Username SQL :");
        usernameSQL.setSize(100, 35);

        passwordSQL.setLocation(100, 400);
        passwordSQL.setText("Password SQL :");
        passwordSQL.setSize(100, 35);

        textuserECE.setPreferredSize(new Dimension(100, 35));
        textuserECE.setLocation(400, 100);
        textuserECE.setForeground(Color.BLUE);


        textpwECE.setPreferredSize(new Dimension(100, 35));
        textpwECE.setLocation(200, 200);
        textpwECE.setForeground(Color.RED);

        textuserSQL.setPreferredSize(new Dimension(100, 35));
        textuserSQL.setLocation(200, 300);
        textuserSQL.setForeground(Color.GREEN);

        textpwSQL.setPreferredSize(new Dimension(100, 35));
        textpwSQL.setLocation(200, 400);
        textpwSQL.setForeground(Color.BLACK);


        valider.setLocation(100, 600);
        valider.setText("Valider");
        valider.setSize(100, 35);

        affich.setLocation(400, 400);
        affich.setSize(100, 35);

    }

    public void connectionSSHGraphique() {
//        remove(pan);


        pan.add(usernameECE);
        pan.add(passwordECE);
        pan.add(usernameSQL);
        pan.add(passwordSQL);
        pan.add(textpwECE);
        pan.add(textpwSQL);
        pan.add(textuserECE);
        pan.add(textuserSQL);
        pan.add(valider);
        pan.add(affich);

        this.setContentPane(pan);
        this.setVisible(true);

        String DBUSERECE = textuserECE.getText();
        String DBUSERSQL = textuserSQL.getText();
        String DBPWECE = textpwECE.getText();
        String DBPWSQL = textpwSQL.getText();


        class ConnexionThread extends Thread
        {
            public void run()
            {
                try {

                    affich.setText("error");
                    Connexion connectSQL = new Connexion(DBUSERECE, DBPWECE, DBUSERSQL, DBPWSQL);
                }
                catch (Exception  ex){
                    System.out.println(ex.getMessage());
                }
            }
        }

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConnexionThread().start();
            }
        });



    }
}