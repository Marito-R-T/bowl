/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.lalr;

import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.IrA;

/**
 *
 * @author mari2bar
 */
public class GoTo extends OperacionSintactica {
    
    private final IrA ira;
    private final NodoSintactico nodo;
    
    public GoTo(IrA ira) {
        this.ira = ira;
        this.nodo = ira.getNodo();
    }
    
    @Override
    public String toString() {
        return "G"+ira.getDestino().getId2();
    }


    @Override
    public boolean parecido(OperacionSintactica op) {
        return this.ira.getDestino().equals(((GoTo)op).getIra().getDestino())
                && this.ira.getInicial().equals(((GoTo)op).getIra().getInicial())
                && this.nodo.equals(((GoTo) op).getNodo())
                && this.ira.getNodo().equals(((GoTo)op).getIra().getNodo());
    }

    public IrA getIra() {
        return ira;
    }

    public NodoSintactico getNodo() {
        return nodo;
    }
    
    
}
