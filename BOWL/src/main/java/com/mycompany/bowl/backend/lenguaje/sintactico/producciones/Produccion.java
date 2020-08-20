/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.sintactico.NoTerminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class Produccion {

    private final NoTerminal productora;
    private final List<NodoSintactico> producidos;
    private final List<Terminal> siguientes = new ArrayList<>();
    public int pospunto;
    public boolean enproceso = false;

    public Produccion(NoTerminal productora, ArrayList<NodoSintactico> producidos) {
        this.productora = productora;
        this.producidos = producidos;
    }

    public NoTerminal getPrimerNodo() {
        return productora;
    }

    public List<NodoSintactico> getProducidos() {
        return producidos;
    }

    public boolean ingresarPrimeros(NoTerminal terminal, ListaProducciones n) {
        int i = 0;
        enproceso = true;
        if (!producidos.isEmpty()) {
            do {
                if (producidos.get(i) instanceof Terminal) {
                    boolean t = false;
                    for (Terminal primero : terminal.getPrimeros()) {
                        if (primero.getNombre().equals(producidos.get(i).getNombre())) {
                            t = true;
                        }
                    }
                    if (!t) {
                        terminal.getPrimeros().add((Terminal) producidos.get(i));
                    }
                } else if (!producidos.get(i).getNombre().equals(terminal.getNombre())
                        && !producidos.get(i).getNombre().equals(this.getPrimerNodo().getNombre())) {
                    System.out.println("--"+producidos.get(i).getNombre());
                    ListaProducciones.encontrarPrimeros(n, terminal, (NoTerminal)producidos.get(i));
                }
                i++;
            } while (producidos.size() > i && ListaProducciones.isAnulable(producidos.get(i - 1), n));
        }
        enproceso = false;
        return false;
    }

}
