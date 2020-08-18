/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico;

import com.mycompany.bowl.backend.lenguaje.lexico.dfa.CreacionAFD;
import com.mycompany.bowl.backend.lenguaje.lexico.dfa.Estado;
import com.mycompany.bowl.backend.lenguaje.lexico.dfa.Transicion;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.Nodo;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.NodoAceptacion;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.NodoConcat;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.NodoTerminal;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class ArbolBinario {

    private final Nodo raiz;
    private List<Estado> estado;
    private List<Transicion> transicion;
    private int[][] tabla;
    private boolean[] finales;
    private String[] nombres;

    public ArbolBinario(Nodo raiz) {
        this.raiz = new NodoConcat(raiz, new NodoAceptacion());
        this.raiz.realizarOPS();
        /*this.raiz.getPrimerapos().forEach((primerapo) -> {
            if(primerapo instanceof NodoTerminal){
                System.out.println("Para el nodo : " + ((NodoTerminal)primerapo).getCodigoin());
                ((NodoTerminal)primerapo).getSiguientepos().forEach((object) -> {
                    System.out.println("----  "+((NodoTerminal) object).getCodigoin());
                });
            }else
                System.out.println("falso");
        });*/
    }

    public void crearAFD() {
        CreacionAFD creacion = new CreacionAFD();
        creacion.crearAFD(this, (NodoAceptacion) raiz.getUltimapos().get(0));
        estado = creacion.getDM();
        transicion = creacion.getTransicion();
        tabla = new int[estado.size()][251];
        nombres = new String[estado.size()];
        finales = new boolean[estado.size()];
        transicion.forEach((tra) -> {
            if (tra.getDestino() != null) {
                tabla[tra.getInicio().getCodigo()][tra.getCaracter()] = tra.getDestino().getCodigo();
                nombres[tra.getInicio().getCodigo()] = tra.getDestino().getNombre();
            } else {
                tabla[tra.getInicio().getCodigo()][tra.getCaracter()] = -1;
            }
        });
        estado.forEach((est) -> {
            System.out.println(est);
            finales[est.getCodigo()] = est.isAceptado();
        });
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public Token conseguirToken(String s, int column, int line) {
        int i = 0, lines = 0, col = 0, colf = 0, lineafin = 0, tam = 0;
        String cadenai = "", cadenaf = null, nombre = null;
        int e = 0;
        char c;
        while (true) {
            if (e != -1) {
                c = s.charAt(i);
                cadenai += c;
                i++;
                col++;
                e = tabla[e][c];
                if (c == 10) {
                    lines++;
                    col = 0;
                }
                if (e != -1 && finales[e]) {
                    cadenaf = cadenai;
                    lineafin = lines;
                    tam = i;
                    nombre = nombres[e];
                    colf = col;
                }
                if (s.length() <= i) {
                    break;
                }
            } else {
                break;
            }
        }
        if (cadenaf != null) {
            return new Token(line, column, tam, lineafin, colf, false, cadenaf, nombre);
        } else {
            return null;
        }
    }

}
