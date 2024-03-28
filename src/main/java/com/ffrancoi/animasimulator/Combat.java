/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ffrancoi.animasimulator;

import javax.swing.JTextArea;

/**
 *
 * @author fabie
 */
public class Combat {
    private Personnage a;
    private Personnage b;
    private int compteurTours;
    private boolean surprise = false;
    private JTextArea rapportCombat;
    
    public Combat(Personnage a, Personnage b, JTextArea rapport){
        this.a = a;
        this.b = b;
        this.compteurTours = 0;
        this.rapportCombat = rapport;
    }
    
    //potentiellement faire une méthode qui gère une attaque une défense pour moins de duplicata de code?
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
    
    public void combatNormal(){
        while(a.enVie() && b.enVie()){
            compteurTours++;
            tourDeCombat();
        }
    }
    
    public void resetCombat(){
        a.setPv(a.getMaxPv());
        b.setPv(b.getMaxPv());
        a.setEstEnVie(true);
        b.setEstEnVie(true);
        compteurTours = 0;
    }
    
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
