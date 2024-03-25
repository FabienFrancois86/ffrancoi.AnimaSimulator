/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ffrancoi.animasimulator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 *
 * @author fabie
 */
public class AnimaSimulator implements ActionListener{
    
    private static final int COLS_NB_COMBATS = 7;
    
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
    
    private JComponent[] composantsPersonnage1 = {new JLabel("Nom :", JLabel.CENTER), fieldNom1, new JLabel("Points de vie :", JLabel.CENTER), 
                                                fieldPv1, new JLabel("Attaque :", JLabel.CENTER), fieldAttaque1, 
                                                new JLabel("Defense :", JLabel.CENTER), fieldDefense1, new JLabel("Initiative :", JLabel.CENTER), 
                                                fieldInit1, new JLabel("Base de dégats :", JLabel.CENTER), fieldBaseDégats1};
    
    private JComponent[] composantsPersonnage2 = {new JLabel("Nom :", JLabel.CENTER), fieldNom2, new JLabel("Points de vie :", JLabel.CENTER), 
                                                fieldPv2, new JLabel("Attaque :", JLabel.CENTER), fieldAttaque2, 
                                                new JLabel("Defense :", JLabel.CENTER), fieldDefense2, new JLabel("Initiative :", JLabel.CENTER), 
                                                fieldInit2, new JLabel("Base de dégats :", JLabel.CENTER), fieldBaseDégats2};
    
    private JTextField[] allNumeralTextFields = {fieldAttaque1, fieldAttaque2, fieldDefense1,
                                            fieldDefense2, fieldInit1, fieldInit2, fieldBaseDégats1, 
                                            fieldBaseDégats2, fieldPv1, fieldPv2 };
    
    private JTextField nbCombats = new JTextField(COLS_NB_COMBATS);
    private static String COMMANDE_UN_COMBAT = "uncombat";
    private static String COMMANDE_SIMULATION_MULTIPLE = "simulationmultiple";
    
    private JTextArea rapportCombat = new JTextArea("Fenêtre de rapport de combats",15,80);

    public AnimaSimulator(){
        panneauPrincipal.setLayout(new BoxLayout(panneauPrincipal,BoxLayout.Y_AXIS));
        
        panneauPrincipal.add(Box.createRigidArea(new Dimension(0,20)));
        panneauPrincipal.add(this.createCharacterBoxes());
        panneauPrincipal.add(Box.createRigidArea(new Dimension(0,50)));
        panneauPrincipal.add(this.createButtonsLayer());
        panneauPrincipal.add(Box.createRigidArea(new Dimension(0,30)));
        panneauPrincipal.add(this.createFightLog());
        panneauPrincipal.add(Box.createRigidArea(new Dimension(0,20)));
    }
    
    //Création du haut de l'interface, contenant les champs de caractéristiques des personnages
    private JPanel createCharacterBoxes(){
        JPanel panneauGrilles = new JPanel();
        panneauGrilles.setLayout(new BoxLayout(panneauGrilles,BoxLayout.X_AXIS));
        panneauGrilles.add(Box.createRigidArea(new Dimension(20,0)));
        panneauGrilles.add(createGridPanel(composantsPersonnage1, "A"));
        panneauGrilles.add(Box.createRigidArea(new Dimension(30,0)));
        panneauGrilles.add(createGridPanel(composantsPersonnage2, "B"));
        panneauGrilles.add(Box.createRigidArea(new Dimension(20,0)));
        return panneauGrilles;
    }
    
    //Création de la partie médiane de l'interface, contenant les boutons ainsi qu'un champ pour choisir le nombre de combats
    private JPanel createButtonsLayer(){
        JPanel panneauMilieu = new JPanel();
        panneauMilieu.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton unCombat = new JButton("Simulation un combat");
        unCombat.setActionCommand(COMMANDE_UN_COMBAT);
        unCombat.addActionListener(this);
        JButton combatsMultiples = new JButton("Simulation combats multiples");
        combatsMultiples.setActionCommand(COMMANDE_SIMULATION_MULTIPLE);
        combatsMultiples.addActionListener(this);
        JButton effacerTexte = new JButton("Effacer texte");
        effacerTexte.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                rapportCombat.setText("Fenêtre de rapport de combats");
            }
        });
        panneauMilieu.add(unCombat);
        panneauMilieu.add(Box.createRigidArea(new Dimension(80,0)));
        panneauMilieu.add(new JLabel("nb Combats : "));
        panneauMilieu.add(nbCombats);
        panneauMilieu.add(Box.createRigidArea(new Dimension(20,0)));
        panneauMilieu.add(combatsMultiples);
        panneauMilieu.add(Box.createRigidArea(new Dimension(80,0)));
        panneauMilieu.add(effacerTexte);
        return panneauMilieu;
    }
    
    private JPanel createFightLog(){
        JPanel panneauRapportCombat = new JPanel();
        rapportCombat.setEditable(false);
        JScrollPane panneauScroll = new JScrollPane(rapportCombat,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panneauRapportCombat.add(panneauScroll);
        return panneauRapportCombat;
    }
    
    private static JPanel createGridPanel(JComponent[] listeComposants, String lettre){
        JPanel grille = new JPanel();
        grille.setLayout(new GridLayout(6,2,0,10));
        for (JComponent composant : listeComposants){
            grille.add(composant);
        }
        grille.setPreferredSize(new Dimension(400,230));
        grille.setBorder(BorderFactory.createTitledBorder("Personnage " + lettre));
        return grille;
    }
    
    private static void createAndShowGUI(){
        JFrame fenetrePrincipale = new JFrame("Anima:Beyond Fantasy - Simulateur de duels");
        AnimaSimulator anima = new AnimaSimulator();
        fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetrePrincipale.setContentPane(anima.panneauPrincipal);
        fenetrePrincipale.pack();
        fenetrePrincipale.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(vérificationEntiers(allNumeralTextFields)){
            String cmd = e.getActionCommand();
            if(COMMANDE_UN_COMBAT.equals(cmd)){
                Combat duel = this.créationCombat();
                duel.combatCommenté(rapportCombat);
            }else if(COMMANDE_SIMULATION_MULTIPLE.equals(cmd)){
                if(vérificationEntiers(new JTextField[] {nbCombats})){
                    Combat duel = this.créationCombat();
                    duel.simulationMultipleCombat(Integer.parseInt(nbCombats.getText()), rapportCombat);
                }
            }
        }else{
            rapportCombat.append("\nErreur ! Les caractéristiques des personnages doivent être des entiers.");
        }
    }
    
    public boolean vérificationEntiers(JTextField[] listeDeVérification){
        Pattern p = Pattern.compile("\\d+");
        Matcher m;
        for (JTextField textField : listeDeVérification){
            m = p.matcher(textField.getText());
            if(!m.matches()){
                return false;
            }
        }
        return true;
    }
    
    public Combat créationCombat(){
        Personnage a = new Personnage(Integer.parseInt(fieldPv1.getText()),Integer.parseInt(fieldBaseDégats1.getText()),
                                    Integer.parseInt(fieldAttaque1.getText()),Integer.parseInt(fieldDefense1.getText()),
                                    fieldNom1.getText(), Integer.parseInt(fieldInit1.getText()));
        Personnage b = new Personnage(Integer.parseInt(fieldPv2.getText()),Integer.parseInt(fieldBaseDégats2.getText()),
                                    Integer.parseInt(fieldAttaque2.getText()),Integer.parseInt(fieldDefense2.getText()),
                                    fieldNom2.getText(), Integer.parseInt(fieldInit2.getText()));
        Combat duel = new Combat(a,b);
        return duel;
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
