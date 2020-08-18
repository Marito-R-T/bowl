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
public class NodoAsterisco extends Nodo {

    private Nodo nodo;

    public NodoAsterisco(Nodo nodo) {
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
        super.primerapos.addAll(this.nodo.realizarPrimerapos());
        return super.primerapos;
    }

    @Override
    protected List<Nodo> realizarUltimapos() {
        super.ultimapos.addAll(this.nodo.realizarUltimapos());
        return super.ultimapos;
    }

    @Override
    protected void realizarSiguientepos() {
        super.ultimapos.forEach((ultimapo) -> {
            super.primerapos.stream().filter((primerapo) -> (!((NodoTerminal) ultimapo).getSiguientepos().contains(primerapo))).forEachOrdered((primerapo) -> {
                ((NodoTerminal)ultimapo).getSiguientepos().add(primerapo);
            });
        });
        if (!(nodo instanceof NodoTerminal)) {
            nodo.realizarSiguientepos();
        }
    }

    @Override
    protected void ingresarNivelNombre(String nombre, int nivel) {
        nodo.ingresarNivelNombre(nombre, nivel);
    }

}
