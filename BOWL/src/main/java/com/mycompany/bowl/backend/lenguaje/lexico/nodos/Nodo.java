/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico.nodos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class Nodo {

    private final List<Nodo> primeramos = new ArrayList<>(), ultimapos = new ArrayList<>();
    private final List<Nodo> siguientepos = new ArrayList<>();
    private boolean anulable;

    public List<Nodo> getPrimeramos() {
        return primeramos;
    }

    public List<Nodo> getUltimapos() {
        return ultimapos;
    }

    public List<Nodo> getSiguientepos() {
        return siguientepos;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public static Nodo hacerNodos(String s) {
        if (s.length() > 0) {
            if(s.length() == 1){
                return new NodoTerminal(s.charAt(0));
            } else{
                return new NodoConcat(new NodoTerminal(s.charAt(0)), Nodo.hacerNodos(s.substring(1)));
            }
        } else {
            return null;
        }
    }
}
