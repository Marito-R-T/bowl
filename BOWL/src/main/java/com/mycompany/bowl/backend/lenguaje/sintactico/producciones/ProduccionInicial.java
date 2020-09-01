/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.sintactico.NoTerminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mari2bar
 */
public class ProduccionInicial extends Produccion implements Serializable {
    
    public static String nombre = "89ads452Pasñ78klwD/&%H1Ger$!p?J¡Dnm#x4FAaS*+AewrjORv";
    
    public ProduccionInicial(ArrayList<NodoSintactico> sintactico) {
        super(new NoTerminal(ProduccionInicial.nombre), sintactico);
    }
    
    public ProduccionInicial(Produccion produccion) {
        super(produccion);
    }

}
