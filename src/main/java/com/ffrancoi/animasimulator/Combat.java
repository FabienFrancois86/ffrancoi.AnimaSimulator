/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ffrancoi.animasimulator;

import javax.swing.JTextArea;

/**
 * Objet représentant une version simplifiée d'un combat entre 2 Personnages.
 * Chaque tour de combat est constitué d'une attaque, suivie d'une contre attaque si possible.
 * Les personnages jouent toujours dans l'ordre d'initiative (pas de gestion de retardement de tour).
 * Le malus de surprise en cas de grosse différence d'initiative est appliqué.
 * Les résultats des combats sont affichés dans une JTextArea.
 * @author fabie
 */
public class Combat {
    private Personnage a;
    private Personnage b;
    private int compteurTours;
    private boolean surprise = false;
    private JTextArea rapportCombat;
    
    /**
     * Crée un Combat classique.
     * @param a premier Personnage
     * @param b second Personnage
     * @param rapport la JTextArea où les résultats du combat sont affichés
     */
    public Combat(Personnage a, Personnage b, JTextArea rapport){
        this.a = a;
        this.b = b;
        this.compteurTours = 0;
        this.rapportCombat = rapport;
    }
    
    /**
     * Méthode gérant un tour de combat.
     * Déroulement d'un tour : jets d'initiative, attaque du personnage qui remporte le jet. Si l'autre personnage
     * arrive à défendre, il contre attaque.
     */
    public void tourDeCombat(){
        Personnage premier = initiative();
        Personnage second = (premier.equals(a)) ? b : a;
        int attaque, defense;
        attaque = premier.attaquer();
        defense = (surprise) ? second.defendre() - 90 : second.defendre();
        if(attaque > defense){
            second.prendreDégats(Math.max((int)Math.floor((attaque - defense - 20)*premier.getBaseDegats()/100), 0));
        }else{
            int bonusContreAttaque = (int)Math.floor((defense - attaque)/2);
            attaque = second.attaquer() + bonusContreAttaque;
            defense = premier.defendre();
            if(attaque > defense){
                premier.prendreDégats(Math.max((int)Math.floor((attaque - defense - 20)*premier.getBaseDegats()/100), 0));
            }
        }
    }
    
    /**
     * Méthode gérant l'initiative d'un tour de combat. Chaque personnage fait un jet d'initiative, celui obtenant le plus haut résultat a l'initiative.
     * Si la différence entre les deux jets est de 150 ou plus, le 2ème personnage est pris par surprise.
     * En cas d'égalité en jet d'initiative, le personnage ayant l'initiative est déterminé au hasard.
     * @return le Personnage ayant gagné l'initiative
     */
    public Personnage initiative(){
        int initA = a.jetInitiative();
        int initB = b.jetInitiative();
        surprise = Math.abs(initA - initB) >= 150;
        if (initA == initB){
            return (Math.random()<0.5) ? a : b;
        }else{
            return (initA > initB) ? a : b;
        }
        
        
    }
    
    /**
     * Méthode gérant un combat entier, tout en écrivant à chaque tour l'avancée du combat dans le rapport de combat.
     */
    public void combatCommenté(){
        while(a.enVie() && b.enVie()){
            compteurTours++;
            rapportCombat.append("\nTour de combat n° " + compteurTours);
            tourDeCombat();
            rapportCombat.append("\nLe tour de combat " + compteurTours + " est fini.,\n"+ a.getNom() +" est à " + a.getPv() + 
                    " points de vie, "+ b.getNom() +" est à " + b.getPv() + " points de vie.");
        }
        if(a.enVie()){
            rapportCombat.append("\n" + a.getNom() + " a remporté le combat !");
        }else{
            rapportCombat.append("\n" + b.getNom() + " a remporté le combat !");
        }
    }
    
    /**
     * Méthode gérant un combat entier, sans écrire l'avancement dans le rapport
     */
    public void combatNormal(){
        while(a.enVie() && b.enVie()){
            compteurTours++;
            tourDeCombat();
        }
    }
    
    /**
     * Réinitialise les points de vie des personnages et le compteur de tours
     */
    public void resetCombat(){
        a.setPv(a.getMaxPv());
        b.setPv(b.getMaxPv());
        a.setEstEnVie(true);
        b.setEstEnVie(true);
        compteurTours = 0;
    }
    
    /**
     * Simule plusieurs fois le même combat et affiche le nombre de victoires de chaque personnage, ainsi que le nombre de tours moyen
     * dans le rapport de combat.
     * @param nombre Le nombre de combats à simuler
     */
    public void simulationMultipleCombat(int nombre){
        int nombreVictoiresA = 0, nombreVictoiresB = 0, compteTourTotal = 0;
        for (int i = 0; i < nombre; i++){
            combatNormal();
            if(a.enVie()){
                nombreVictoiresA++;
            }else{
                nombreVictoiresB++;
            }
            compteTourTotal += compteurTours;
            resetCombat();
        }
        double moyenneTours = Math.floor(compteTourTotal*100/nombre) / 100;
        rapportCombat.append("\n" + a.getNom() + " a gagné un total de " + nombreVictoiresA + " fois.\n" + b.getNom() + " a gagné un total de " + 
                nombreVictoiresB + " fois.\nLe combat a duré en moyenne " + moyenneTours + " tours.");
    }
}
