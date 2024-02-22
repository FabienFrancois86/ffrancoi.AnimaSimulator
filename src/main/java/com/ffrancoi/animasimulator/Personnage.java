/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ffrancoi.animasimulator;

/**
 *
 * @author fabie
 */
public class Personnage {
    private int maxPv;
    private int pv;
    private int baseDegats;
    private int attaque;
    private int defense;
    private boolean estEnVie;
    private String nom;
    private int initiative;
    
    public Personnage(int pv, int baseDegats, int attaque, int defense, String nom, int initiative){
        this.maxPv = pv;
        this.pv = maxPv;
        this.baseDegats = baseDegats;
        this.attaque = attaque;
        this.defense = defense;
        this.estEnVie = true;
        this.nom = nom;
        this.initiative = initiative;
    }
    
    public void prendreDégats(int dégats){
        this.pv -= dégats;
        if(this.pv < 0){
            estEnVie = false;
        }
    }
    
    public void estSoigné(int soin){
        this.pv = Math.min(this.pv + soin, this.maxPv);
    }
    
    public int attaquer(){
        LancéDeDés a = new LancéDeDés(attaque, "Attaque");
        return a.totalLancé();
    }
    
    public int defendre(){
        LancéDeDés a = new LancéDeDés(defense, "Defense");
        return a.totalLancé();
    }
    
    public int jetInitiative(){
        LancéDeDés a = new LancéDeDés(initiative, "Initiative");
        return a.totalLancé();
    }
    
    public boolean enVie(){
        return estEnVie;
    }
    
    public int getBaseDegats(){
        return this.baseDegats;
    }

    public int getMaxPv() {
        return maxPv;
    }

    public int getPv() {
        return pv;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getDefense() {
        return defense;
    }

    public String getNom() {
        return nom;
    }
    
    //override equals?

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setEstEnVie(boolean estEnVie) {
        this.estEnVie = estEnVie;
    }
}
