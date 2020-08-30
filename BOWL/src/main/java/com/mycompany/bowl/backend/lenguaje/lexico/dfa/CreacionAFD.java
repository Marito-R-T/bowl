/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico.dfa;

import com.mycompany.bowl.backend.lenguaje.lexico.ArbolBinario;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.Nodo;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.NodoAceptacion;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.NodoTerminal;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author mari2bar
 */
public class CreacionAFD implements Serializable {

    private final List<Estado> D;
    private final List<Estado> DM;
    private final List<Transicion> transicion;

    public CreacionAFD() {
        this.D = new ArrayList<>();
        this.DM = new ArrayList<>();
        this.transicion = new ArrayList<>();
    }

    public void crearAFD(ArbolBinario arbol, NodoAceptacion aceptacion) {
        Estado nuevo = new Estado(arbol.getRaiz().getPrimerapos(),
                arbol.getRaiz().getPrimerapos().contains(aceptacion), 0, null, ' ');
        D.add(nuevo);
        marcarEstados(D.remove(0), aceptacion);
    }

    public void marcarEstados(Estado estado, NodoAceptacion aceptacion) {
        estado.setMarcado(true);
        DM.add(estado);
        for (char i = 0; i < 250; i++) {
            List<Nodo> nodo = new ArrayList<>();
            for (Nodo n : estado.getNodos()) {
                if (i >= ((NodoTerminal) n).getCodigoin()
                        && i <= ((NodoTerminal) n).getCodigofin()) {
                    for (Nodo siguientepo : ((NodoTerminal) n).getSiguientepos()) {
                        if (!nodo.contains(siguientepo)) {
                            nodo.add(siguientepo);
                        }
                    }
                }
            }
            Estado existe;
            Estado nuevo = new Estado(nodo, nodo.contains(aceptacion), (D.size() + DM.size()), estado.getNodos(), i);
            existe = isSimilar(nuevo);
            if (!nodo.isEmpty() && existe == null) {
                D.add(nuevo);
                this.transicion.add(new Transicion(estado, nuevo, i));
            } else if (nodo.isEmpty()) {
                this.transicion.add(new Transicion(estado, null, i));
            } else if (existe != null) {
                this.transicion.add(new Transicion(estado, existe, i));
            }
        }
        if (!D.isEmpty()) {
            this.marcarEstados(D.remove(0), aceptacion);
        }
    }

    public Estado isSimilar(Estado nuevo) {
        for (Estado estado : D) {
            if (this.listEqualsIgnoreOrder(estado.getNodos(), nuevo.getNodos())
                    && ((estado.getNombre() == null && nuevo.getNombre() == null) || (estado.getNombre() != null && estado.getNombre().equals(nuevo.getNombre())))) {
                return estado;
            }
        }
        for (Estado estado : DM) {
            if (this.listEqualsIgnoreOrder(estado.getNodos(), nuevo.getNodos())
                    && ((estado.getNombre() == null && nuevo.getNombre() == null) || (estado.getNombre() != null && estado.getNombre().equals(nuevo.getNombre())))) {
                return estado;
            }
        }
        return null;
    }

    public List<Transicion> getTransicion() {
        return transicion;
    }

    public boolean listEqualsIgnoreOrder(List<Nodo> list1, List<Nodo> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    public List<Estado> getDM() {
        return DM;
    }

}
