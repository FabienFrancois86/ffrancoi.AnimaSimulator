/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ffrancoi.animasimulator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author fabie
 */
public class AnimaSimulator implements ActionListener{
    
    /*private static final int COLS_COMPS = 4;
    private static final int COLS_NOM = 30;*/
    
    private JPanel panneauPrincipal = new JPanel();
    
    
    private JTextField fieldNom1 = new JTextField(); // possiblement ajouter un nombre de colonnes, à voir...
    private JTextField fieldNom2 = new JTextField();
    private JTextField fieldPv1 = new JTextField();
    private JTextField fieldPv2 = new JTextField();
    private JTextField fieldAttaque1 = new JTextField();
    private JTextField fieldAttaque2 = new JTextField();
    private JTextField fieldDefense1 = new JTextField();
    private JTextField fieldDefense2 = new JTextField();
    private JTextField fieldInit1 = new JTextField();
    private JTextField fieldInit2 = new JTextField();
    private JTextField fieldBaseDégats1 = new JTextField();
    private JTextField fieldBaseDégats2 = new JTextField();
    
    private JComponent[] composantsPersonnage1 = {new JLabel("Nom :"), fieldNom1, new JLabel("Points de vie :"), fieldPv1, 
                                                new JLabel("Attaque :"), fieldAttaque1, new JLabel("Defense :"), fieldDefense1,
                                                new JLabel("Initiative :"), fieldInit1, new JLabel("Base de dégats :"), 
                                                fieldBaseDégats1};
    
    private JComponent[] composantsPersonnage2 = {new JLabel("Nom :"), fieldNom2, new JLabel("Points de vie :"), fieldPv2, 
                                                new JLabel("Attaque :"), fieldAttaque2, new JLabel("Defense :"), fieldDefense2,
                                                new JLabel("Initiative :"), fieldInit2, new JLabel("Base de dégats :"), 
                                                fieldBaseDégats2};
    
    /*
    private JTextField[] allNumeralTextFields = {fieldAttaque1, fieldAttaque2, fieldDefense1,
                                            fieldDefense2, fieldInit1, fieldInit2, fieldBaseDégats1, 
                                            fieldBaseDégats2, fieldPv1, fieldPv2 };
    */
    
    /*private JButton unCombat = new JButton("Simulation un combat");
    private JLabel LabelNombreCombats = new JLabel("nb Combats");
    private JTextField nbCombats = new JTextField();
    private JButton combatsMultiples = new JButton("Simulation combats multiples");
    private static String COMMANDE_UN_COMBAT = "uncombat";
    private static String COMMANDE_SIMULATION_MULTIPLE = "simulationmultiple";*/
    
    //private JTextArea rapportCombat = new JTextArea();

    public AnimaSimulator(){
        panneauPrincipal.setLayout(new BoxLayout(panneauPrincipal,BoxLayout.X_AXIS));
        JPanel grillePersonnage1 = createGridPanel(composantsPersonnage1);
        grillePersonnage1.setBorder(BorderFactory.createTitledBorder("Personnage A"));
        JPanel grillePersonnage2 = createGridPanel(composantsPersonnage2);
        grillePersonnage2.setBorder(BorderFactory.createTitledBorder("Personnage B"));
        panneauPrincipal.add(Box.createRigidArea(new Dimension(20,0)));
        panneauPrincipal.add(grillePersonnage1);
        panneauPrincipal.add(Box.createRigidArea(new Dimension(30,0)));
        panneauPrincipal.add(grillePersonnage2);
        panneauPrincipal.add(Box.createRigidArea(new Dimension(20,0)));
        /*fenetrePrincipale = new JFrame("Test de bouton et texte");
        panneauPrincipal = new JPanel();
        ajoutTexte = new JButton("Ajout Texte");
        ajoutTexte.addActionListener(this);
        rapportCombat = new JTextArea(10, 50);
        rapportCombat.setEditable(false);
        JScrollPane panneauScroll = new JScrollPane(rapportCombat,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panneauPrincipal.add(ajoutTexte);
        */
    }
    
    public static JPanel createGridPanel(JComponent[] listeComposants){
        JPanel grille = new JPanel();
        grille.setLayout(new GridLayout(6,2,0,10));
        for (JComponent composant : listeComposants){
            grille.add(composant);
        }
        grille.setPreferredSize(new Dimension(400,230));
        return grille;
    }
    
    public static void createAndShowGUI(){
        JFrame fenetrePrincipale = new JFrame("Anima:Beyond Fantasy - Simulateur de duels");
        AnimaSimulator anima = new AnimaSimulator();
        fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetrePrincipale.setContentPane(anima.panneauPrincipal);
        fenetrePrincipale.pack();
        fenetrePrincipale.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        //tester de quel élément l'event vient et agir en fonction
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
