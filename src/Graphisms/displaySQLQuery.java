package Graphisms;

import Main.Connexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class displaySQLQuery extends JFrame {

    private final JLabel image;
    private final JLabel bravo;
    private JPanel panSQL;
    private JTable maTable;

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

        panSQL = displayQueries(true, table, colonne, nbElem, connexion);
    }

    public JPanel displayQueries(boolean onAfficheouPas, String table, String colonne, int nbElem, Connexion connexion) {

        this.add(bravo);

        DefaultTableModel model = new DefaultTableModel();
        JTable tableRes = new JTable(model);

// Create a couple of columns
        model.addColumn("Col1");
        model.addColumn("Col2");

// Append a row
        model.addRow(new Object[]{"v1", "v2"});

        tableRes.setLocation(80,130);
        tableRes.setSize(640,350);
        this.add(tableRes);

        /*maTable = connexion.readDTB(table, colonne, nbElem, true);
        maTable.setLocation(80,130);
        maTable.setSize(640,350);
        this.add(new JScrollPane(maTable));
*/
        maTable = connexion.readDTB(table, colonne, nbElem, true);
        JScrollPane scrollPane = new JScrollPane(maTable);
        scrollPane.setLocation(80,130);
        scrollPane.setSize(640,350);
        this.add(scrollPane);

        this.add(image);
        image.setVisible(false);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSQL;
    }
}
