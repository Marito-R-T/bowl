/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

/**
 *
 * @author mari2bar
 */
public class Terminal extends NodoSintactico {
    
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
