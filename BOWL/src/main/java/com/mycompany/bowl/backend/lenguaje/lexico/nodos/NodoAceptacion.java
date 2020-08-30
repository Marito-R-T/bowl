/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico.nodos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class NodoAceptacion extends NodoTerminal implements Serializable {
    
    public NodoAceptacion() {
        super('$');
    }
    
    @Override
    protected List<Nodo> realizarPrimerapos() {
        super.primerapos.add(this);
        return super.primerapos;
    }

    @Override
    protected List<Nodo> realizarUltimapos() {
        super.ultimapos.add(this);
        return super.ultimapos;
    }

}
