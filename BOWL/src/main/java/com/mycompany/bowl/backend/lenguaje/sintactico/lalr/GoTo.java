/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.lalr;

import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.IrA;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.IrAN;
import java.util.List;

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
    
    public GoTo(IrA ira, List<IrAN> j) {
        this.ira = ira;
        this.nodo = ira.getNodo();
    }
    
    @Override
    public String toString() {
        return "G"+ira.getDestino().getId();
    }
    
}
