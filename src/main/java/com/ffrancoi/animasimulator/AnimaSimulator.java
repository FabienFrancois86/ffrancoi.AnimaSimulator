/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ffrancoi.animasimulator;

/**
 *
 * @author fabie
 */
public class AnimaSimulator {

    public static void main(String[] args) {
        Personnage a = new Personnage(50, 50, 50, 50, "TestA", 100);
        for (int i = 0; i < 300; i++){
            System.out.println(a.jetInitiative());
        }
    }
}
