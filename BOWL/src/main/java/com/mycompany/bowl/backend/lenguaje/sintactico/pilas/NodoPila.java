/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.pilas;

import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class NodoPila extends NodoSintactico implements Serializable {
    
    private NodoPila anterior;
    
    public NodoPila(NodoSintactico n) {
        super(n.getNombre(), n.getId());
    }
    
    public void setAnterior(NodoPila anterior) {
        this.anterior = anterior;
    }

    public NodoPila getAnterior() {
        return anterior;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
