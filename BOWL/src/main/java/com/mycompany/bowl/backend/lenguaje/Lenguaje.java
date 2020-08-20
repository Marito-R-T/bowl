/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje;

import com.mycompany.bowl.backend.lenguaje.codigojava.AnalisisCodigoJava;
import com.mycompany.bowl.backend.lenguaje.lexico.ArbolBinario;
import com.mycompany.bowl.backend.lenguaje.lexico.Token;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.Nodo;
import com.mycompany.bowl.backend.lenguaje.sintactico.TablaDeSimbolos;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.ListaProducciones;

/**
 *
 * @author moise
 */
public class Lenguaje {

    private InfoLenguaje info;
    private AnalisisCodigoJava codigo;
    private ArbolBinario binario;
    private int line, column, tam;
    private TablaDeSimbolos tablaSimbolos;
    private ListaProducciones producciones;

    public Lenguaje() {
        line = 0;
        column = 0;
        tam = 0;
    }

    public void realizarCodigo(String codigo) {
        this.codigo = new AnalisisCodigoJava(codigo, "hola");
    }

    public InfoLenguaje getInfo() {
        return info;
    }

    public void setInfo(InfoLenguaje info) {
        this.info = info;
    }

    public ArbolBinario getBinario() {
        return binario;
    }

    public void setBinario(Nodo binario) {
        this.binario = new ArbolBinario(binario);
        this.binario.crearAFD();
    }

    public TablaDeSimbolos getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void setTablaSimbolos(TablaDeSimbolos tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

    public ListaProducciones getProducciones() {
        return producciones;
    }

    public void setProducciones(ListaProducciones producciones) {
        this.producciones = producciones;
        this.producciones.realizarLALR(tablaSimbolos);
    }

    public Token analizarTexto(String s) {
        if (s.length() > tam) {
            Token token = binario.conseguirToken(s.substring(tam), column, line);
            if (token != null) {
                line += token.getLines();
                column = token.getColumnf();
                tam += token.getTamano();
                return token;
            }
        } else {
            return new Token(line, column, 0, 0, column, true, null, null);
        }
        return null;
    }

}
