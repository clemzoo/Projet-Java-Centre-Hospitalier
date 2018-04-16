package Reporting;

import javax.swing.*;


public class Graph extends JFrame{
    private JPanel pan; // panneau
    private Graphisms.SSH SSH;
    private Graphisms.Localhost Localhost;


    public Graph()
    {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        pan = new JPanel(); // instancier le panneau
    }



}
