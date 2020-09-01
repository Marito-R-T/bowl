/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

import com.mycompany.bowl.backend.lenguaje.lexico.ArbolBinario;
import com.mycompany.bowl.backend.lenguaje.lexico.Token;
import com.mycompany.bowl.backend.lenguaje.semantico.AnalisisCodigoJava;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Aceptar;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.GoTo;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.OperacionSintactica;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Remove;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Shift;
import com.mycompany.bowl.backend.lenguaje.sintactico.pilas.NodoPila;
import com.mycompany.bowl.backend.lenguaje.sintactico.pilas.Pila;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.ListaProducciones;
import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class ManejadorAnalisis implements Serializable {

    private ArbolBinario binario;
    private TablaDeSimbolos tablaSimbolos;
    private ListaProducciones producciones;
    private AnalisisCodigoJava analisis;
    private int line, column, tam;
    private Pila pila;

    public ManejadorAnalisis(ArbolBinario binario, TablaDeSimbolos tablaSimbolos, ListaProducciones producciones) {
        this.binario = binario;
        this.tablaSimbolos = tablaSimbolos;
        this.producciones = producciones;
        line = 0;
        column = 0;
        tam = 0;
    }

    public Pila getPila() {
        return pila;
    }

    public void setAnalisis(AnalisisCodigoJava analisis) {
        this.analisis = analisis;
    }

    public Token analizarTexto(String s) {
        if (s.length() > tam) {
            Token token = binario.conseguirToken(s.substring(tam), column, line, tablaSimbolos);
            if (token != null) {
                line += token.getLines();
                column = token.getColumnf();
                tam += token.getTamano();
                return token;
            } else {
                if (s.charAt(tam) == '\n') {
                    line++;
                    column = 0;
                }
                tam++;
                return null;
            }
        } else {
            return new Token(line, column, 0, 0, column, true, null, null, null);
        }
    }

    private void reiniciarAnalisis() {
        this.line = 0;
        this.column = 0;
        this.tam = 0;
        this.pila = new Pila(analisis);
    }

    public Object analizarSintactico(String s) {
        this.reiniciarAnalisis();
        Token tok;
        Object o = null;
        do {
            do {
                tok = this.analizarTexto(s);
            } while (tok != null && tok.getNombre() == null && !tok.isUltimo());
            System.out.println(tok);
            if (tok != null) {
                //o = producciones.realizarAnalisis(tok);
                OperacionSintactica op;
                op = producciones.getTablaTransicion()[pila.getPila().getNumero() - 1][tablaSimbolos.posTerminal(tok)];
                while (op != null) {
                    if (op instanceof Aceptar) {
                        pila.agregarAceptacion();
                        System.out.println("aceptado");
                        return null;
                    }
                    if (op instanceof Shift) {
                        System.out.println("shift");
                        System.out.println(((Shift)op).getIra().getDestino().getId());
                        pila.shift(new NodoPila(((Shift) op).getNodo()), ((Shift) op).getIra().getDestino().getId2(), tok.getObject());
                        op = null;
                    } else if (op instanceof Remove) {
                        System.out.println("remove");
                        NodoPila p = pila.remove(((Remove) op).getPr(), (((Remove) op).getPro() + 1), producciones);
                        op = producciones.getTablaTransicion()[pila.getPila().getNumero() - 1][tablaSimbolos.posNoTerminal(p.getNombre())];
                    } else if (op instanceof GoTo) {
                        System.out.println("goto");
                        pila.goTo(((GoTo) op).getIra().getDestino().getId2());
                        op = producciones.getTablaTransicion()[pila.getPila().getNumero() - 1][tablaSimbolos.posTerminal(tok)];
                    } else {
                        System.out.println(" hahahaha");
                    }
                }
            } else {
                System.out.println("error sintactico.");
            }
        } while (tok == null || !tok.isUltimo());
        //this.producciones.getTablaTransicion()
        return o;
    }

}
