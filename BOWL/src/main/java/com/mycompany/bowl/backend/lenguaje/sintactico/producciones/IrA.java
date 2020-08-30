/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class IrA implements Serializable {

    private I inicial, destino;
    private NodoSintactico nodo;
    private boolean terminal;

    public IrA(I inicial, I destino, NodoSintactico nodo) {
        this.inicial = inicial;
        this.destino = destino;
        this.nodo = nodo;
        this.terminal = nodo instanceof Terminal;
    }
    
    public IrA(IrA ira) {
        this.inicial = ira.getInicial();
        this.destino = ira.getDestino();
        this.nodo = ira.getNodo();
        this.terminal = ira.isTerminal();
    }

    @Override
    public String toString() {
        return "-" + inicial + " nodo: " + nodo + " -" + destino;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public NodoSintactico getNodo() {
        return nodo;
    }

    public I getInicial() {
        return inicial;
    }

    public I getDestino() {
        return destino;
    }

    public void setInicial(I inicial) {
        this.inicial = inicial;
    }

    public void setDestino(I destino) {
        this.destino = destino;
    }

}
