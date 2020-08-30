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
public class NodoConcat extends Nodo implements Serializable {

    private Nodo nodo1, nodo2;

    public NodoConcat(Nodo nodo1, Nodo nodo2) {
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
        super.anulable = nodo1.anulable && nodo2.anulable;
    }

    public Nodo getNodo1() {
        return nodo1;
    }

    public void setNodo1(Nodo nodo1) {
        this.nodo1 = nodo1;
    }

    public Nodo getNodo2() {
        return nodo2;
    }

    public void setNodo2(Nodo nodo2) {
        this.nodo2 = nodo2;
    }

    @Override
    protected List<Nodo> realizarPrimerapos() {
        if (nodo1.anulable) {
            super.primerapos.addAll(nodo1.realizarPrimerapos());
            super.primerapos.addAll(nodo2.realizarPrimerapos());
        } else {
            super.primerapos.addAll(nodo1.realizarPrimerapos());
            nodo2.realizarPrimerapos();
        }
        return super.primerapos;
    }

    @Override
    protected List<Nodo> realizarUltimapos() {
        if (nodo2.anulable) {
            super.ultimapos.addAll(nodo1.realizarUltimapos());
            super.ultimapos.addAll(nodo2.realizarUltimapos());
        } else {
            nodo1.realizarUltimapos();
            super.ultimapos.addAll(nodo2.realizarUltimapos());
        }
        return super.ultimapos;
    }

    @Override
    protected void realizarSiguientepos() {
        nodo1.getUltimapos().forEach((ultimapo) -> {
            for (Nodo primerapo : nodo2.getPrimerapos()) {
                if (!((NodoTerminal) ultimapo).getSiguientepos().contains(primerapo)) {
                    ((NodoTerminal) ultimapo).getSiguientepos().add(primerapo);
                }
            }
        });
        if (!(nodo1 instanceof NodoTerminal)) {
            nodo1.realizarSiguientepos();
        }
        if (!(nodo2 instanceof NodoTerminal)) {
            nodo2.realizarSiguientepos();
        }
    }

    @Override
    protected void ingresarNivelNombre(String nombre, int nivel) {
        nodo1.ingresarNivelNombre(nombre, nivel);
        nodo2.ingresarNivelNombre(nombre, nivel);
    }

}
