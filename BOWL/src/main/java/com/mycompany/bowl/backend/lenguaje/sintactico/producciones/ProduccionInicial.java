/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.sintactico.NoTerminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import java.util.ArrayList;

/**
 *
 * @author mari2bar
 */
public class ProduccionInicial extends Produccion{
    
    public ProduccionInicial(ArrayList<NodoSintactico> sintactico) {
        super(new NoTerminal("SP"), sintactico);
    }
    
    public ProduccionInicial(Produccion produccion) {
        super(produccion);
    }

}
