/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

import com.mycompany.bowl.backend.lenguaje.lexico.Token;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class TablaDeSimbolos implements Serializable {

    private ArrayList<Terminal> terminal = new ArrayList<>();
    private ArrayList<NoTerminal> noterminal = new ArrayList<>();

    public List<Terminal> getTerminal() {
        return terminal;
    }

    public List<NoTerminal> getNoterminal() {
        return noterminal;
    }

    public int posNoTerminal(String t) {
        for (int i = 0; i < noterminal.size(); i++) {
            if (noterminal.get(i).getNombre().equals(t)) {
                return i + terminal.size() + 1;
            }
        }
        return terminal.size() + noterminal.size() + 1;
    }

    public int posTerminal(Token t) {
        if (t.isUltimo()) {
            return terminal.size();
        }
        for (int i = 0; i < terminal.size(); i++) {
            if (terminal.get(i).getNombre().equals(t.getNombre())) {
                return i;
            }
        }
        return terminal.size() + noterminal.size() + 1;
    }

    public void setNivel(List<Terminal> ter) {
        int nivel;
        if (terminal.isEmpty()) {
            nivel = 0;
        } else {
            nivel = ((Terminal) terminal.get(terminal.size() - 1)).getNivel() + 1;
        }
        for (Terminal term : ter) {
            term.setNivel(nivel);
            System.out.println("nivel: " + term.getNivel());
        }
    }

}
