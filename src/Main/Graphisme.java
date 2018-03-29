package Main;

import javax.swing.*;

public class Graphisme extends JFrame {
    private JPanel pan; // panneau
    private ImageIcon hopital;
    private boolean created;

    public Graphisme()
    {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        pan = new JPanel(); // instancier le panneau
        hopital = new ImageIcon("/Annexe/hopital.png");
        created = false;
        getContentPane().add(pan);

    }
}
