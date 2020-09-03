/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.pilas;

import com.mycompany.bowl.backend.errores.ErrorSintactico;
import com.mycompany.bowl.backend.lenguaje.lexico.Token;
import com.mycompany.bowl.backend.lenguaje.semantico.AnalisisCodigoJava;
import com.mycompany.bowl.backend.lenguaje.sintactico.TablaDeSimbolos;
import com.mycompany.bowl.backend.lenguaje.sintactico.pilas.muestra.PilaMuestra;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.ListaProducciones;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.Produccion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class Pila implements Serializable {

    private NodoPila arriba;
    private EstadoPila pila;
    private final PilaMuestra muestra;
    private final AnalisisCodigoJava analisis;

    public Pila(AnalisisCodigoJava analisis) {
        EstadoPila est = new EstadoPila(1, null);
        this.pila = est;
        this.analisis = analisis;
        muestra = new PilaMuestra();
        muestra.agregarMuestra(est, arriba, "INICIAR");
    }

    public void shift(NodoPila nodo, int estado, Object ob) {
        nodo.setAnterior(arriba);
        nodo.setValor(ob);
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

    public NodoPila remove(Token tok, Produccion pro, int produccion, ListaProducciones lista) {
        List<Object> object = new ArrayList<>();
        for (int i = pro.getProducidos().size() - 1; i >= 0; i--) {
            if (arriba.getNombre().equals(pro.getProducidos().get(i).getNombre())) {
                if (pro.getProducidos().get(i).getId() != null) {
                    object.add(0, this.arriba.getValor());
                }
                NodoPila n = this.arriba.getAnterior();
                this.arriba.setAnterior(null);
                this.arriba = n;
                EstadoPila p = this.pila.getAnterior();
                this.pila.setAnterior(null);
                this.pila = p;
            } else {
                ErrorSintactico.errorTokenSintactico(tok);
            }
        }
        Object[] ob = new Object[object.size()];
        for (int i = 0; i < object.size(); i++) {
            ob[i] = object.get(i);
        }
        Produccion pr = lista.encontrar(pro);
        System.out.println("el metodo usado es: " +  pr.getSemantico().getId());
        Object resultado = analisis.hacerMetodo(pr, pr.getSemantico().getId(), ob, pr.getSemantico().getClas());
        NodoPila nodo = new NodoPila(pr.getPrimerNodo());
        nodo.setValor(resultado);
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
