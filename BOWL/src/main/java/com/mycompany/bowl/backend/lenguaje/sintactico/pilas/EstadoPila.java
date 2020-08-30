/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.pilas;

import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class EstadoPila implements Serializable {
    
    private final Integer numero;
    private EstadoPila anterior;
    
    public EstadoPila(Integer numero, EstadoPila anterior) {
        this.numero = numero;
        this.anterior = anterior;
    }

    public EstadoPila getAnterior() {
        return anterior;
    }

    public void setAnterior(EstadoPila anterior) {
        this.anterior = anterior;
    }

    public Integer getNumero() {
        return numero;
    }
    
}
