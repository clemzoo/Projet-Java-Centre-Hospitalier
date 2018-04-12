package Main;

import javax.swing.*;

public class TypeRequete extends JFrame{

    private JPanel panel; // panneau
    private JButton Consulter, Rechercher, Ajouter, Supprimer, annuler;
    private JLabel image,titre;

    public TypeRequete()
    {
        setTitle("Les types de requête");
        setSize(800, 600);
        setLocation(425, 200);
        panel = new JPanel(); // instancier le panneau
        image = new JLabel(new ImageIcon("hopital.jpg"));
        titre = new JLabel();


        Consulter = new JButton();
        Rechercher = new JButton();
        Ajouter = new JButton();
        Supprimer = new JButton();
        annuler = new JButton();

        Consulter = new JButton();
        Consulter.setLocation(150, 180);
        Consulter.setText("Consulter");
        Consulter.setSize(150, 40);

        Rechercher = new JButton();
        Rechercher.setLocation(150, 330);
        Rechercher.setText("Rechercher");
        Rechercher.setSize(150, 40);

        Ajouter = new JButton();
        Ajouter.setLocation(450, 180);
        Ajouter.setText("Ajouter");
        Ajouter.setSize(150, 40);

        Supprimer = new JButton();
        Supprimer.setLocation(450, 330);
        Supprimer.setText("Supprimer");
        Supprimer.setSize(150, 40);

        annuler.setLocation(320, 500);
        annuler.setText("Annuler");
        annuler.setSize(100, 35);

        titre.setText("Choisissez votre type de requête");
        titre.setLocation(300,100);
        titre.setSize(200,35);

        panel = afficher(true);

    }
    public JPanel afficher(boolean affichage) {

        this.add(Consulter);

        this.add(Ajouter);
        this.add(Rechercher);
        this.add(titre);
        this.add(Supprimer);
        this.add(annuler);

        this.add(image);

        image.setVisible(false);

        this.setVisible(affichage);
        repaint();

        return panel;
    }
}
