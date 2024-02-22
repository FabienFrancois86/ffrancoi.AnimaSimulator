/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ffrancoi.animasimulator;

/**
 *
 * @author fabie
 */
public class Combat {
    private Personnage a;
    private Personnage b;
    private int compteurTours;
    
    public Combat(Personnage a, Personnage b){
        //Vérifier que a et b sont différents
        this.a = a;
        this.b = b;
        this.compteurTours = 0;
    }
    
    //potentiellement faire une méthode qui gère une attaque une défense pour moins de duplicata de code?
    public void tourDeCombat(){
        Personnage premier = initiative();
        Personnage second = (premier.equals(a)) ? b : a;
        int attaque, defense;
        attaque = premier.attaquer();
        defense = second.defendre();
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
        if (initA == initB){
            return (Math.random()<0.5) ? a : b;
        }else{
            return (initA > initB) ? a : b;
        }
        
        
    }
    
    public void combatCommenté(){
        while(a.enVie() && b.enVie()){
            compteurTours++;
            tourDeCombat();
            System.out.println("Le tour de combat " + compteurTours + " est fini. a : " + a.getPv() + 
                    ". b : " + b.getPv());
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
    }
    
    public void simulationMultipleCombat(int nombre){
        int nombreVictoiresA = 0, nombreVictoiresB = 0;
        for (int i = 0; i < nombre; i++){
            combatNormal();
            if(a.enVie()){
                nombreVictoiresA++;
            }else{
                nombreVictoiresB++;
            }
            resetCombat();
        }
        System.out.println("A a gagné un total de " + nombreVictoiresA + " fois.\nB a gagné un total de " + 
                nombreVictoiresB + " fois");
    }
}
