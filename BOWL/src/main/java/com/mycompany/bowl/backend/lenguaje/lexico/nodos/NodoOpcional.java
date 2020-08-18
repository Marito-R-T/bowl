/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico.nodos;

import java.util.List;

/**
 *
 * @author mari2bar
 */
public class NodoOpcional extends Nodo {
    
    private Nodo nodo;

    public NodoOpcional(Nodo nodo){
        this.nodo = nodo;
        super.anulable = true;
    }
    
    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    @Override
    protected List<Nodo> realizarPrimerapos() {
        super.primerapos.addAll(nodo.realizarPrimerapos());
        return super.primerapos;
    }

    @Override
    protected List<Nodo> realizarUltimapos() {
        super.ultimapos.addAll(nodo.realizarUltimapos());
        return super.ultimapos;
    }

    @Override
    protected void realizarSiguientepos() {
        if(!(nodo instanceof NodoTerminal)){
            nodo.realizarSiguientepos();
        }
    }
    
    @Override
    protected void ingresarNivelNombre(String nombre, int nivel) {
        nodo.ingresarNivelNombre(nombre, nivel);
    }

}
