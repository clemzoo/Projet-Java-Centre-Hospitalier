package Main;

import javax.swing.*;

public class SuccesfullConnexion extends JFrame {

    private JPanel panSuccess;
    private JLabel bravo,image;

    public SuccesfullConnexion(){
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSuccess = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        bravo = new JLabel();
        bravo.setLocation(200, 250);
        bravo.setText("Bravo ! Tu as réussi à te connecter a la DTB de l'ECE Paris");
        bravo.setSize(500, 35);

        this.add(bravo);
        this.add(image);
        image.setVisible(false);

        this.setVisible(true);
    }

}