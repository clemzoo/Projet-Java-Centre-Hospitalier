package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Localhost extends Graphisme {

    private JPanel pan;
    public JLabel nomDatabase,  loginDatabase, passwordDatabase, affich;
    private JTextField textDB, textuserDB,textpwDB;
    private  JButton valider;

    public Localhost()
    {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        pan = new JPanel(); // instancier le panneau
        nomDatabase = new JLabel ();
        loginDatabase = new JLabel ();
        passwordDatabase = new JLabel ();
        affich = new JLabel();
        textDB = new JTextField();
        textuserDB = new JTextField();
        textpwDB = new JTextField();
        valider = new JButton();
        nomDatabase.setLocation(100, 100);
        nomDatabase.setText("Nom Database :");
        nomDatabase.setSize(100, 35);



        loginDatabase.setLocation(100, 300);
        loginDatabase.setText("Login Database :");
        loginDatabase.setSize(120, 35);

        passwordDatabase.setLocation(100, 450);
        passwordDatabase.setText("Password Database :");
        passwordDatabase.setSize(100, 35);

        textuserDB.setPreferredSize(new Dimension(100, 35));
        textuserDB.setLocation(400,300);
        textuserDB.setForeground(Color.BLUE);



        textpwDB.setPreferredSize(new Dimension(100, 35));
        textpwDB.setLocation(400,450);
        textpwDB.setForeground(Color.RED);



        //  textDB.setFont(police);
        textDB.setPreferredSize(new Dimension(100, 35));
        textDB.setLocation(400,100);
        textDB.setForeground(Color.GREEN);


       valider.setLocation(100, 600);
        valider.setText("Valider");
        valider.setSize(100, 35);

    }

    public void connectionLocalhostGraphique(){
//        remove(pan);



        pan.add(nomDatabase);
        pan.add(loginDatabase);
        pan.add(passwordDatabase);
        pan.add(textDB);
        pan.add(textpwDB);
        pan.add(textuserDB);
        pan.add(valider);
        pan.add(affich);
        this.setContentPane(pan);
        this.setVisible(true);
        String DBNAME = textDB.getText();
        String DBUSER = textuserDB.getText();
        String DBPW = textpwDB.getText();
        affich.setLocation(500, 500);

        affich.setSize(100, 35);

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connexion connectSQL = new Connexion(DBNAME,DBUSER,DBPW);
                } catch (Exception  ex){
                    System.out.println(ex.getMessage());
                    affich.setText(ex.getMessage());
                }
            }
        });



    }

}
