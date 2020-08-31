/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.lexico;

import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class Token implements Serializable {

    private final int line;
    private final int column;
    private final int tamano;
    private final boolean ultimo;
    private final int lines;
    private final int columnf;
    private int nivel;
    private final String token;
    private final String nombre;
    private final Object object;
    
    public Token(int line, int column, int tamano, int lines, int columnf, boolean ultimo, String token, String nombre, Object ob){
        this.line = line;
        this.lines = lines;
        this.column = column;
        this.columnf = columnf;
        this.tamano =tamano;
        this.token = token;
        this.nombre = nombre;
        this.ultimo = ultimo;
        this.object = ob;
    }

    public Object getObject() {
        return object;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getToken() {
        return token;
    }

    public int getTamano() {
        return tamano;
    }

    public int getLines() {
        return lines;
    }

    public int getColumnf() {
        return columnf;
    }

    public boolean isUltimo() {
        return ultimo;
    }
    
    @Override
    public String toString(){
        return "nombre: "+ this.nombre+" line: " + 
                this.line + " column: " + this.column + " size: " + this.tamano + " token: " + this.token + " final: " + this.ultimo;
    }

    public String getNombre() {
        return nombre;
    }
    
}
