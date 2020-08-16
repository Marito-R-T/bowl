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
public class NodoAsterisco extends Nodo {
    
    private Nodo nodo;

    public NodoAsterisco(Nodo nodo){
        this.nodo = nodo;
    }
    
    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
    
}
