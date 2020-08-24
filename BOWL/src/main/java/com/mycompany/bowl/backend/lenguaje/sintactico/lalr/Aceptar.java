/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.lalr;

import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.Produccion;

/**
 *
 * @author mari2bar
 */
public class Aceptar extends Remove {
    
    public Aceptar(Produccion pr, Terminal t) {
        super(pr, t);
    }
    
}
