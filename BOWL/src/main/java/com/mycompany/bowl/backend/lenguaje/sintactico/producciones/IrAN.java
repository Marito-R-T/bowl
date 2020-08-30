/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class IrAN implements Serializable {
    
    private I nuevo, ultimo;
    
    public IrAN(I nuevo, I ultimo) {
        this.nuevo = nuevo;
        this.ultimo = ultimo;
    }

    public I getNuevo() {
        return nuevo;
    }

    public I getUltimo() {
        return ultimo;
    }
    
}
