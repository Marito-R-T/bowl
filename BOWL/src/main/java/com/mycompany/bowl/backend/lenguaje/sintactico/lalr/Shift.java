/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.lalr;

import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.IrA;
import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class Shift extends OperacionSintactica implements Serializable {
    
    private final IrA ira;
    private final NodoSintactico nodo;
    
    public Shift(IrA ira) {
        this.ira = ira;
        this.nodo = ira.getNodo();
    }
    
    @Override
    public String toString() {
        return "S"+ira.getDestino().getId2();
    }

    @Override
    public boolean parecido(OperacionSintactica op) {
        return this.ira.getDestino().equals(((Shift)op).getIra().getDestino())
                && this.ira.getInicial().equals(((Shift)op).getIra().getInicial())
                && this.nodo.equals(((Shift) op).getNodo())
                && this.ira.getNodo().equals(((Shift)op).getIra().getNodo());
    }

    public IrA getIra() {
        return ira;
    }

    public NodoSintactico getNodo() {
        return nodo;
    }
    
}
