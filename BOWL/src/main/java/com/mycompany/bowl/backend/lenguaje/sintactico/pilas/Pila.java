/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.pilas;

import com.mycompany.bowl.backend.lenguaje.sintactico.pilas.muestra.PilaMuestra;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.Produccion;
import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class Pila implements Serializable {

    private NodoPila arriba;
    private EstadoPila pila;
    private final PilaMuestra muestra;

    public Pila() {
        EstadoPila est = new EstadoPila(1, null);
        this.pila = est;
        muestra = new PilaMuestra();
        muestra.agregarMuestra(est, arriba, "INICIAR");
    }

    public void shift(NodoPila nodo, int estado) {
        nodo.setAnterior(arriba);
        arriba = nodo;
        EstadoPila est = new EstadoPila(estado, pila);
        pila = est;
        muestra.agregarMuestra(pila, arriba, "shift" + estado);
    }

    public void goTo(int estado) {
        EstadoPila est = new EstadoPila(estado, pila);
        pila = est;
        muestra.agregarMuestra(pila, arriba, "goTo" + estado);
    }

    public NodoPila remove(Produccion pro, int produccion) {
        for (int i = pro.getProducidos().size() - 1; i >= 0; i--) {
            if (arriba.getNombre().equals(pro.getProducidos().get(i).getNombre())) {
                NodoPila n = this.arriba.getAnterior();
                this.arriba.setAnterior(null);
                this.arriba = n;
                EstadoPila p = this.pila.getAnterior();
                this.pila.setAnterior(null);
                this.pila = p;
            }
        }
        NodoPila nodo = new NodoPila(pro.getPrimerNodo());
        nodo.setAnterior(arriba);
        this.arriba = nodo;
        muestra.agregarMuestra(pila, arriba, "remove" + produccion);
        return nodo;
    }

    public NodoPila getArriba() {
        return arriba;
    }

    public EstadoPila getPila() {
        return pila;
    }

    public PilaMuestra getMuestra() {
        return muestra;
    }
    
    public void agregarAceptacion() {
        muestra.aceptacion(pila, arriba, "ACEPTACION");
    }

}
