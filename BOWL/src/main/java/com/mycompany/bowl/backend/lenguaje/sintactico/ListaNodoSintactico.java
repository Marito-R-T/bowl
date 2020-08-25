/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

import java.util.ArrayList;

/**
 *
 * @author mari2bar
 * @param <T>
 */
public class ListaNodoSintactico<T> extends ArrayList<T> {

    private String tipo;
    
    public ListaNodoSintactico(String tipo) {
        this.tipo = tipo;
    }
    
    public boolean add(NodoSintactico e) {
        e.setTipo(tipo);
        return super.add((T) e); //To change body of generated methods, choose Tools | Templates.
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
