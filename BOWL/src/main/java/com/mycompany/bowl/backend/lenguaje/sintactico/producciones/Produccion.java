/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.semantico.Semantico;
import com.mycompany.bowl.backend.lenguaje.sintactico.Aceptacion;
import com.mycompany.bowl.backend.lenguaje.sintactico.NoTerminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import com.mycompany.bowl.backend.lenguaje.sintactico.TablaDeSimbolos;
import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class Produccion implements Serializable {

    private final NoTerminal productora;
    private final List<NodoSintactico> producidos;
    private final List<Terminal> siguientes;
    private Semantico semantico;
    public int pospunto;
    public boolean enproceso = false;

    public Produccion(NoTerminal productora, ArrayList<NodoSintactico> producidos, Semantico semantico) {
        this.productora = productora;
        this.producidos = producidos;
        this.semantico = semantico;
        this.siguientes = new ArrayList<>();
        this.pospunto = 0;
    }

    public Produccion(NoTerminal productora, ArrayList<NodoSintactico> producidos) {
        this.productora = productora;
        this.producidos = producidos;
        this.siguientes = new ArrayList<>();
        this.pospunto = 0;
    }

    public Semantico getSemantico() {
        return semantico;
    }

    public Produccion(Produccion produccion) {
        this.siguientes = new ArrayList<>();
        this.productora = produccion.getPrimerNodo();
        this.producidos = produccion.getProducidos();
        this.pospunto = produccion.pospunto;
        this.siguientes.addAll(produccion.getSiguientes());
    }

    public NoTerminal getPrimerNodo() {
        return productora;
    }

    public List<NodoSintactico> getProducidos() {
        return producidos;
    }

    public int getPospunto() {
        return pospunto;
    }

    public void sumarPospunto() {
        this.pospunto++;
    }

    public boolean isEnproceso() {
        return enproceso;
    }

    public List<Terminal> getSiguientes() {
        return siguientes;
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
                    System.out.println("--" + producidos.get(i).getNombre());
                    ListaProducciones.encontrarPrimeros(n, terminal, (NoTerminal) producidos.get(i));
                }
                i++;
            } while (producidos.size() > i && ListaProducciones.isAnulable(producidos.get(i - 1), n));
        }
        enproceso = false;
        return false;
    }

    public List<Terminal> obtenerSiguientes(ListaProducciones prod) {
        List<Terminal> t = new ArrayList<>();
        int i = this.pospunto;
        boolean b = true;
        do {
            i++;
            if (i < this.producidos.size()) {
                if (!(this.producidos.get(i) instanceof Aceptacion)) {
                    this.revisarExistencia(t, prod.getPrimeros(this.producidos.get(i)));
                } else {
                    if (!this.revisarEx(t, (Terminal) this.producidos.get(i))) {
                        t.add((Terminal) this.producidos.get(i));
                    }
                }
                if (!this.producidos.get(i).isAnulable()) {
                    b = false;
                }
            } else {
                this.revisarExistencia(t, this.siguientes);
            }
        } while (this.producidos.size() >= i && b);
        return t;
    }

    public void revisarExistencia(List<Terminal> t, List<Terminal> prod) {
        for (Terminal terminal : prod) {
            if (!this.revisarEx(t, terminal)) {
                t.add(terminal);
            }
        }
    }

    public boolean revisarEx(List<Terminal> t, Terminal prod) {
        for (Terminal terminal : t) {
            if (prod instanceof Aceptacion && terminal instanceof Aceptacion) {
                return true;
            } else if (!(prod instanceof Aceptacion) && !(terminal instanceof Aceptacion)) {
                if (terminal.getNombre().equals(prod.getNombre())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPospunto(int pospunto) {
        this.pospunto = pospunto;
    }

    public boolean esIgual(Produccion p) {
        return p.getPrimerNodo().getNombre().equals(this.getPrimerNodo().getNombre())
                && p.getPospunto() == this.pospunto
                && this.producidosIgual(p.getProducidos())
                && p.getSiguientes().equals(this.getSiguientes());
    }

    public boolean esMuySimilar(Produccion p) {
        return p.getPrimerNodo().getNombre().equals(this.getPrimerNodo().getNombre())
                && p.getPospunto() == this.pospunto
                && this.producidosIgual(p.getProducidos());
    }

    public boolean esSim(Produccion p) {
        return p.getPrimerNodo().getNombre().equals(this.getPrimerNodo().getNombre())
                && this.producidosIgual(p.getProducidos());
    }

    public boolean producidosIgual(List<NodoSintactico> n) {
        if (n.size() == this.producidos.size()) {
            for (int i = 0; i < n.size(); i++) {
                if (!producidos.get(i).getNombre().equals(n.get(i).getNombre())) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return productora.toString();
    }

    public void agregarSiguientes(List<Terminal> sig) {
        for (Terminal terminal : sig) {
            if (!this.siguientes.contains(terminal)) {
                this.siguientes.add(terminal);
            }
        }
    }

    public void hacerSemantico(int i, TablaDeSimbolos tabla) {
        List<String> ids = new ArrayList<>(), nombre = new ArrayList<>();
        for (NodoSintactico producido : producidos) {
            if (producido.getId() != null) {
                ids.add(producido.getId());
                nombre.add(producido.getNombre());
            }
        }
        String[][] id = new String[ids.size()][2];
        for (int j = 0; j < id.length; j++) {
            id[j][0] = ids.get(j);
            id[j][1] = tabla.getTipo(nombre.get(j));
        }
        System.out.println(this.getPrimerNodo().getNombre());
        String tipo = tabla.getTipo(this.getPrimerNodo().getNombre());
        this.semantico.agregarTexto("azbyñkolewncvpasd"+i, id, tipo);
    }
    
}
