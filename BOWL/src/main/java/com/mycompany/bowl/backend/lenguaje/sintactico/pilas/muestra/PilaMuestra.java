/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.pilas.muestra;

import com.mycompany.bowl.backend.lenguaje.sintactico.pilas.EstadoPila;
import com.mycompany.bowl.backend.lenguaje.sintactico.pilas.NodoPila;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class PilaMuestra implements Serializable {
    
    private final List<Muestra> muestra;
    
    public PilaMuestra() {
        muestra = new ArrayList<>();
    }
    
    public void agregarMuestra(EstadoPila estado, NodoPila nodo, String accion){
        Muestra ms = new Muestra(accion);
        NodoPila n = nodo;
        while(n!=null) {
            ms.getString().add(0, n.getNombre()+"<>"+n.getValor());
            n = n.getAnterior();
        }
        EstadoPila e = estado;
        while (e != null) {
            ms.getEnteros().add(0, e.getNumero());
            e = e.getAnterior();
        }
        muestra.add(ms);
    }

    public void aceptacion(EstadoPila estado, NodoPila nodo, String accion) {
        this.agregarMuestra(estado, nodo, accion);
        muestra.get(muestra.size()-1).getString().add("AAA");
    }
    
    public List<Muestra> getMuestra() {
        return muestra;
    }
    
}
