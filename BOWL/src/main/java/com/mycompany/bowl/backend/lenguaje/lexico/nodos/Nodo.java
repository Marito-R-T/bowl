/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico.nodos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public abstract class Nodo implements Serializable{

    protected final List<Nodo> primerapos = new ArrayList<>(), ultimapos = new ArrayList<>();
    protected boolean anulable;
    protected String nombre;
    protected int nivel;

    public void realizarOPS(){
        this.realizarPrimerapos();
        this.realizarUltimapos();
        this.realizarSiguientepos();
    }
    
    protected abstract List<Nodo> realizarPrimerapos();
    
    protected abstract List<Nodo> realizarUltimapos();
    
    protected abstract void realizarSiguientepos();
    
    protected abstract void ingresarNivelNombre(String nombre, int nivel);
    
    public List<Nodo> getPrimerapos() {
        return primerapos;
    }

    public List<Nodo> getUltimapos() {
        return ultimapos;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public void ingresar(){
        this.ingresarNivelNombre(nombre, nivel);
    }
    
}
