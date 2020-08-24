/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.Produccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class NoTerminal extends NodoSintactico {
    
    private List<Terminal> primeros = new ArrayList<>();
    
    public NoTerminal(String nombre, String id) {
        super(nombre, id);
    }
    
    public NoTerminal(String nombre) {
        super(nombre);
    }

    public List<Terminal> getPrimeros() {
        return primeros;
    }
    
    public void encontrarPrimeros(List<Produccion> pr, List<NoTerminal> noterminal, List<Terminal> terminales){
        for (Produccion produccion : pr) {
            if(produccion.getPrimerNodo().nombre.equals(super.nombre)){
            }
        }
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
}
