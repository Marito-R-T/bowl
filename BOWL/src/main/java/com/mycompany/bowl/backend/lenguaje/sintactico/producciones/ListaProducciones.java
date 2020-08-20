/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.sintactico.NoTerminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import com.mycompany.bowl.backend.lenguaje.sintactico.TablaDeSimbolos;
import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class ListaProducciones {

    private final List<Produccion> producciones;
    private final List<I> estados;
    private List<NoTerminal> noterminales;
    private List<Terminal> terminales;

    public ListaProducciones(Produccion produccion) {
        ArrayList<Produccion> prod = new ArrayList<>();
        prod.add(produccion);
        this.producciones = prod;
        estados = new ArrayList<>();
        noterminales = new ArrayList<>();
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void realizarLALR(TablaDeSimbolos tabla) {
        if (producciones.size() > 0) {
            ArrayList<NodoSintactico> n = new ArrayList<>();
            n.add(producciones.get(0).getPrimerNodo());
            Produccion pr = new ProduccionInicial(n);
            producciones.add(0, pr);
            noterminales = tabla.getNoterminal();
            terminales = tabla.getTerminal();
            noterminales.add(0, pr.getPrimerNodo());
            for (NoTerminal noTerminal : noterminales) {
                int i = 0;
                while (!noTerminal.isAnulable() && i < producciones.size()) {
                    if (producciones.get(i).getPrimerNodo().getNombre().equals(noTerminal.getNombre()) && producciones.get(i).getProducidos().isEmpty()) {
                        noTerminal.setAnulable(true);
                    }
                    i++;
                }
            }
            for (NoTerminal noterminale : noterminales) {
                System.out.println(noterminale.getNombre());
                ListaProducciones.encontrarPrimeros(this, noterminale, noterminale);
               
            }
            for(NoTerminal noterminale : noterminales){
                System.out.println(noterminale.getPrimeros().size());
                for (Terminal primero : noterminale.getPrimeros()) {
                    System.out.println(primero.getNombre());
                }
            }
        } else {

        }
    }

    public void realizarIN(ArrayList<Produccion> pr) {
        int i = pr.size();
        for (Produccion produccione : producciones) {
            for (Produccion produccion : pr) {
            }
        }
    }

    public static void encontrarPrimeros(ListaProducciones n, NoTerminal noterminal, NoTerminal primeros) {
        for (Produccion produccione : n.getProducciones()) {
            if (produccione.getPrimerNodo().getNombre().equals(primeros.getNombre()) && !produccione.enproceso) {
                produccione.ingresarPrimeros(noterminal, n);
            }
        }
    }

    public static boolean isAnulable(NodoSintactico no, ListaProducciones n) {
        if (no instanceof NoTerminal) {
            for (NoTerminal noterminale : n.getNoterminales()) {
                if (noterminale.getNombre().equals(no.getNombre())) {
                    return noterminale.isAnulable();
                }
            }
        }
        return false;
    }

    public List<NoTerminal> getNoterminales() {
        return noterminales;
    }

}
