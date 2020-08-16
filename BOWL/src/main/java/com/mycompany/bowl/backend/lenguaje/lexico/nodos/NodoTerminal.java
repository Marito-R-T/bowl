/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico.nodos;

/**
 *
 * @author mari2bar
 */
public class NodoTerminal extends Nodo {
    
    private char codigoin, codigofin;

    public NodoTerminal(String codigoin, String codigofin){
        this.codigoin = codigoin.charAt(0);
        this.codigofin = codigofin.charAt(0);
    }
    
    public NodoTerminal(String codigoin){
        this.codigoin = codigoin.charAt(0);
    }
    
    public NodoTerminal(char codigoin){
        this.codigoin = codigoin;
    }
    
    public NodoTerminal(char codigoin, char codigofin){
        this.codigoin = codigoin;
        this.codigofin = codigofin;
    }
    
    public char getCodigoin() {
        return codigoin;
    }

    public void setCodigoin(char codigoin) {
        this.codigoin = codigoin;
    }

    public char getCodigofin() {
        return codigofin;
    }

    public void setCodigofin(char codigofin) {
        this.codigofin = codigofin;
    }
    
}
