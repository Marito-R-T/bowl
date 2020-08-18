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
public class NodoTerminal extends Nodo {

    private char codigoin, codigofin;
    private final List<Nodo> siguientepos = new ArrayList<>();

    public NodoTerminal(String codigoin, String codigofin) {
        this.codigoin = codigoin.charAt(0);
        this.codigofin = codigofin.charAt(0);
        super.anulable = false;
    }

    public NodoTerminal(String codigoin) {
        this.codigoin = codigoin.charAt(0);
        this.codigofin = codigoin.charAt(0);
        super.anulable = false;
    }

    public NodoTerminal(char codigoin) {
        this.codigoin = codigoin;
        this.codigofin = codigoin;
        super.anulable = false;
    }

    public NodoTerminal(char codigoin, char codigofin) {
        this.codigoin = codigoin;
        this.codigofin = codigofin;
        super.anulable = false;
    }

    public char getCodigoin() {
        return codigoin;
    }

    public void setCodigoin(char codigoin) {
        this.codigoin = codigoin;
    }

    public char getCodigofin() {
        return codigofin;
    }

    public void setCodigofin(char codigofin) {
        this.codigofin = codigofin;
    }

    public List<Nodo> getSiguientepos() {
        return siguientepos;
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

    @Override
    protected void realizarSiguientepos() {
    }

    @Override
    public String toString() {
        return "codigo inicial: " + codigoin + "  codigo final: " + codigofin;
    }

    @Override
    protected void ingresarNivelNombre(String nombre, int nivel) {
        super.nombre = nombre;
        super.nivel = nivel;
    }

}
