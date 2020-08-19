/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.sintactico.NoTerminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class Produccion {
    
    private final NoTerminal productora;
    private final List<NodoSintactico> producidos;
    
    public Produccion(NoTerminal productora, ArrayList<NodoSintactico> producidos){
        this.productora = productora;
        this.producidos = producidos;
    }
    
}
