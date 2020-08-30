/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.pilas.muestra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class Muestra implements Serializable {

    private final List<Integer> enteros;
    private final List<String> string;
    private final String accion;

    public Muestra(String accion) {
        enteros = new ArrayList<>();
        string = new ArrayList<>();
        this.accion = accion;
    }

    public List<Integer> getEnteros() {
        return enteros;
    }

    public List<String> getString() {
        return string;
    }

    public String getAccion() {
        return accion;
    }

}
