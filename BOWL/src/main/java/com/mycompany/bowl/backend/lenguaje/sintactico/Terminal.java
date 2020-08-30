/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class Terminal extends NodoSintactico implements Serializable {
    
    private int nivel;

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public Terminal(String nombre, String id) {
        super(nombre, id);
    }
    
    public Terminal(String nombre) {
        super(nombre);
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
}
