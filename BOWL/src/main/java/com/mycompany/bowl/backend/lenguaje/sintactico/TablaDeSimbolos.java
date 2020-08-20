/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class TablaDeSimbolos {
    
    private List<Terminal> terminal = new ArrayList<>();
    private List<NoTerminal> noterminal = new ArrayList<>();

    public List<Terminal> getTerminal() {
        return terminal;
    }

    public List<NoTerminal> getNoterminal() {
        return noterminal;
    }
    
    
}
