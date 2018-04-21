package Reporting;

import Main.Connexion;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Statistique extends JFrame {
    private JPanel fen; // panneau
    private JPanel bas;
    private JLabel total_malade_lab;
    private JLabel personel_lab;
    private Connexion connect;
    final DefaultPieDataset pieDataset = new DefaultPieDataset();
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public Statistique(Connexion a) throws SQLException {
        connect = a;
        setTitle("Statistique de la BDD");
        setSize(800,600);

        fen = new JPanel();
        bas = new JPanel();

        total_malade_lab = new JLabel("Nb malades : ");
        personel_lab = new JLabel("Nb personnel : ");

        RequeteStat();
        bas.add("WEST", total_malade_lab);
        bas.add("WEST", personel_lab);
        fen.add("SOUTH",bas);

        this.setContentPane(fen);
        this.setVisible(true);

    }

    public void RequeteStat() throws SQLException {
        int nb_service = 0;

        ResultSet resultat1 = connect.getStmt().executeQuery( "SELECT COUNT(*) AS total FROM malade");
        resultat1.next();
        total_malade_lab.setText("Nb malades : " + resultat1.getInt("total"));


        ResultSet resultat2 = connect.getStmt().executeQuery( "SELECT COUNT(*) AS total FROM employe");
        resultat2.next();
        personel_lab.setText("Nb personnel : " + resultat2.getInt("total"));

        ResultSet resultat3 = connect.getStmt().executeQuery( "SELECT COUNT(*) AS total FROM service");
        resultat3.next();
        nb_service = resultat3.getInt("total");

        ResultSet resultat4 = connect.getStmt().executeQuery( "SELECT * FROM service");
        String[] tab_service_code = new String[nb_service];
        String[] tab_service_nom = new String[nb_service];
        for(int i = 0; i<nb_service; i++) {
            resultat4.next();
            tab_service_code[i] = resultat4.getString("code");
            tab_service_nom[i] = resultat4.getString("nom");
        }

        //JPanel salaire = new JPanel();
        //salaire.setLayout(new GridLayout(0,1));
        for(int i =0; i<nb_service; i++){
            ResultSet resultat5 = connect.getStmt().executeQuery( "SELECT SUM(salaire) AS total FROM infirmier WHERE code_service = '"+ tab_service_code[i]+"'");
            resultat5.next();
            //JLabel a = new JLabel(tab_service_nom[i]+" : " + resultat5.getInt("total") + "€"); //Nom service
            dataset.addValue(resultat5.getInt("total"), tab_service_nom[i], new Integer(i));
            //salaire.add(a);
        }
        //droite.add(salaire);

        //JPanel patient = new JPanel();
        //patient.setLayout(new GridLayout(0,1));
        for(int i =0; i<nb_service; i++){
            ResultSet resultat6 = connect.getStmt().executeQuery( "SELECT COUNT(*) AS total FROM hospitalisation WHERE code_service = '"+ tab_service_code[i]+"'");
            resultat6.next();
            //JLabel a = new JLabel(tab_service_nom[i]+" : " + resultat6.getInt("total")); //Nom service
            pieDataset.setValue(tab_service_nom[i], resultat6.getInt("total"));
            //patient.add(a);
        }
        //gauche.add(patient);


        final JFreeChart pieChart = ChartFactory.createPieChart("Nombre de patients par service", pieDataset, true, false, false);
        final ChartPanel cPanel = new ChartPanel(pieChart);
        final JFreeChart barChart = ChartFactory.createBarChart("Masse salariale", "Service", "€", dataset, PlotOrientation.VERTICAL, true, true, false);
        final ChartPanel barPanel = new ChartPanel(barChart);

        fen.add("EAST", cPanel);
        fen.add("WEST", barPanel);

    }



}
