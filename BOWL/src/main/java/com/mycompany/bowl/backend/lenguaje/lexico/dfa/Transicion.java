/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico.dfa;

/**
 *
 * @author mari2bar
 */
public class Transicion {
    
    private final Estado inicio;
    private final char caracter;
    private final Estado destino;
    
    public Transicion(Estado inicio, Estado destino, char caracter){
        this.inicio = inicio;
        this.destino = destino;
        this.caracter = caracter;
    }
    
    @Override
    public String toString(){
        return "Transicion del nodo: " + inicio + " al nodo: " + destino + " con el caracter: " + caracter;
    }

    public Estado getInicio() {
        return inicio;
    }

    public Estado getDestino() {
        return destino;
    }

    public char getCaracter() {
        return caracter;
    }
    
}
