/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje;

import com.mycompany.bowl.backend.lenguaje.semantico.AnalisisCodigoJava;
import com.mycompany.bowl.backend.lenguaje.lexico.ArbolBinario;
import com.mycompany.bowl.backend.lenguaje.lexico.nodos.Nodo;
import com.mycompany.bowl.backend.lenguaje.sintactico.ManejadorAnalisis;
import com.mycompany.bowl.backend.lenguaje.sintactico.TablaDeSimbolos;
import com.mycompany.bowl.backend.lenguaje.sintactico.TablaLALR;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.ListaProducciones;
import java.io.Serializable;

/**
 *
 * @author moise
 */
public class Lenguaje implements Serializable {

    private InfoLenguaje info;
    private AnalisisCodigoJava codigo;
    private ArbolBinario binario;
    private TablaDeSimbolos tablaSimbolos;
    private ListaProducciones producciones;
    private ManejadorAnalisis analisis;
    private TablaLALR tablaLALR;

    public Lenguaje() {
    }

    public void realizarCodigo(String codigo) {
        this.codigo = new AnalisisCodigoJava(codigo, "CodigoJava");
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

    public AnalisisCodigoJava getCodigo() {
        return codigo;
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
        this.analisis = new ManejadorAnalisis(binario, tablaSimbolos, this.producciones);
        this.tablaLALR = new TablaLALR(this.producciones.getTablaTransicion(), this.info.getNombre(), tablaSimbolos);
        for (int i = 1; i < producciones.getProducciones().size(); i++) {
            producciones.getProducciones().get(i).hacerSemantico(i, tablaSimbolos);
            System.out.println(producciones.getProducciones().get(i).getSemantico().getTexto());
        }
        this.codigo.hacermetodos(this.producciones.getProducciones());
        analisis.setAnalisis(codigo);
    }

    public ManejadorAnalisis getAnalisis() {
        return analisis;
    }

    public TablaLALR getTablaLALR() {
        return tablaLALR;
    }

}
