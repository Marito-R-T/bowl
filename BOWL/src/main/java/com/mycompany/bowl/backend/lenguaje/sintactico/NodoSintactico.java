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
public class NodoSintactico implements Serializable {
    
    protected final String id, nombre;
    private String tipo;
    private boolean anulable = false;
    
    public NodoSintactico(String nombre, String id){
        this.id = id;
        this.nombre = nombre;
    }
    
    public NodoSintactico(String nombre) {
        this.nombre = nombre;
        this.id = null;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
