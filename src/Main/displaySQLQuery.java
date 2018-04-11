package Main;

import javax.swing.*;
import java.awt.*;

public class displaySQLQuery extends JFrame {

    private final JLabel image;
    private final JLabel bravo;
    private JPanel panSQL;

    public displaySQLQuery(Connexion connexion, String table, String colonne, int nbElem) {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSQL = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        bravo = new JLabel();
        bravo.setLocation(320, 80);
        bravo.setText("Résultat de votre recherche :");
        bravo.setSize(500, 35);

        connexion.readDTB(table, colonne, nbElem, true);

        panSQL = displayQueries(true);
    }

    public JPanel displayQueries(boolean onAfficheouPas) {

        this.add(bravo);

        this.add(image);
        image.setVisible(false);

        this.setVisible(true);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSQL;
    }
}
