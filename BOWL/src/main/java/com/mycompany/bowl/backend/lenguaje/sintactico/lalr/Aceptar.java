/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.lalr;

import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.Produccion;
import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class Aceptar extends Remove implements Serializable {
    
    public Aceptar(Produccion pr, Terminal t) {
        super(pr, t);
    }
    
}
