/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ffrancoi.animasimulator;

import java.util.ArrayList;
/**
 *
 * @author fabie
 */
public class LancéDeDés {
    private int valeurCompétence;
    private String nomCompétence;
    private ArrayList<Integer> dés;
    private boolean estUneMaladresse;
    private int degréMaladresse;
    
    /**
     *
     * @param valeur
     * @param nom
     */
    public LancéDeDés(int valeur, String nom){
        valeurCompétence = valeur;
        nomCompétence = nom;
        int a,i = 0;
        dés = new ArrayList<>();
        do{
            a = (int)Math.floor((Math.random()*100)+1);
            dés.add(a);
            i++;
        }
        while (a > (88+i));
        if (dés.get(0)<3 || (dés.get(0) < 4 && valeurCompétence < 200)){
            estUneMaladresse = true;
            degréMaladresse = calculDegréMaladresse(dés.get(0), valeurCompétence, nomCompétence);
        }else{
            estUneMaladresse = false;
        }
    }
    
    //calcule le degré de maladresse, TODO initiative
    public static int calculDegréMaladresse(int dé, int compétence, String nom){
        if ("Initiative".equals(nom)){
            return switch (dé) {
                case 1 -> 125;
                case 2 -> 100;
                case 3 -> 75;
                default -> -999;
            };
        }else{
            int degré = (int)Math.floor((Math.random()*100)+1);
            return switch (dé) {
                case 1 -> (compétence >= 200) ? degré : degré + 15;
                case 2 -> (compétence >= 200) ? Math.max(degré -15, 1) : degré;
                case 3 -> Math.max(degré -15, 1); //possiblement un test à faire si compétence >= 200 throw exception
                default -> -999; //ne devrait jamais arriver, throw exception à faire
            };
        } 
    }
    
    /*Méthode qui calcule le total du jet, renvoie compétence - degré de maladresse si maladresse
    */
    public int totalLancé(){
        if (estUneMaladresse){
            return valeurCompétence - degréMaladresse;
        }else{
            int result = valeurCompétence;
            for(int i : dés){
                result += i;
            }
            return result;
        }
    }
    
    @Override
    public String toString(){
        if(estUneMaladresse){
            return "CATASTROPHE, le lancé de dés de compétence " + nomCompétence + " est une maladresse de degré " + 
                    degréMaladresse + ".\nLe total est de " + this.totalLancé() + ".";
        }else{
            if(dés.size()>1){
                return "JET OUVERT ! Le lancé de dés de compétence " + nomCompétence + " est un succès majeur !\n"
                        + "Le total est de " + this.totalLancé() + ".";
            }else{
                return "Le lancé de dés de compétence " + nomCompétence +" a pour total " + this.totalLancé() + ".";
            }
        }
    }
}
