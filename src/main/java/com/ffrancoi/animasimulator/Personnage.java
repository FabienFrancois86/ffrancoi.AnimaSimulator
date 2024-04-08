/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ffrancoi.animasimulator;

/**
 * Objet représentant une version simplifiée d'un personnage d'Anima : Beyond Fantasy
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
    
    /**
     * Crée un nouveau personnage avec les valeurs précisées en paramètres
     * @param pv Points de vie maximum du personnage
     * @param baseDegats Base de dégats du personnage
     * @param attaque Attaque du personnage
     * @param defense Défense du personnage
     * @param nom Nom du personnage
     * @param initiative Initiative du personnage
     */
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
    
    /**
     * Inflige des dégats au personnage courant. Modifie la valeur du booléen estEnVie si nécessaire
     * Ne gère pas pour l'instant le système de critiques de Anima
     * @param dégats Nombre de dégats infligés au personnage
     */
    public void prendreDégats(int dégats){
        this.pv -= dégats;
        if(this.pv < 0){
            estEnVie = false;
        }
    }
    
    /**
     * Soigne le personnage, ne peut faire passer un personnage au dessus de ses points de vie maximum
     * @param soin Valeur de soin reçue
     */
    public void estSoigné(int soin){
        this.pv = Math.min(this.pv + soin, this.maxPv);
    }
    
    /**
     * Jet d'attaque du personnage
     * @return le total du jet d'attaque, incluant la compétence du personnage
     */
    public int attaquer(){
        LancéDeDés a = new LancéDeDés(attaque, "Attaque");
        return a.totalLancé();
    }
    
    /**
     * Jet de défense du personnage
     * @return le total du jet de défense, incluant la compétence du personnage
     */
    public int defendre(){
        LancéDeDés a = new LancéDeDés(defense, "Defense");
        return a.totalLancé();
    }
    
    /**
     * Jet d'initiative du personnage
     * @return le total du jet d'initiative, incluant la compétence du personnage
     */
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
    

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setEstEnVie(boolean estEnVie) {
        this.estEnVie = estEnVie;
    }
}
