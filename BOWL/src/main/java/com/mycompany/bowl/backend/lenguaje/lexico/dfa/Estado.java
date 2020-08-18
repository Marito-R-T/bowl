/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico.dfa;

import com.mycompany.bowl.backend.lenguaje.lexico.nodos.Nodo;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.NodoAceptacion;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.NodoTerminal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class Estado {

    private boolean marcado = false;
    private boolean aceptado;
    private final List<Nodo> nodos, anteriores;
    private int codigo;
    private String nombre;
    private Integer nivel = null;

    public Estado(List<Nodo> nodos, boolean aceptado, int codigo, List<Nodo> anteriores, char c) {
        this.nodos = nodos;
        this.aceptado = aceptado;
        this.codigo = codigo;
        this.anteriores = anteriores;
        if (aceptado) {
            for (Nodo nodo : anteriores) {
                if ((nivel == null || nivel > nodo.getNivel()) && ((NodoTerminal)nodo).getCodigoin()<= c && ((NodoTerminal)nodo).getCodigofin() >= c) {
                    nombre = nodo.getNombre();
                    nivel = nodo.getNivel();
                }
            }

        }
    }

    public Estado() {
        nodos = new ArrayList<>();
        anteriores = null;
    }

    public List<Nodo> getAnteriores() {
        return anteriores;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    @Override
    public String toString() {
        return String.valueOf("codigo: "+codigo+" nombre: " + nombre + " aceptado: " +aceptado + " nivel: " + nivel);
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

}
