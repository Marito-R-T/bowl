/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.lalr;

import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public abstract class OperacionSintactica implements Serializable {
    
    public abstract boolean parecido(OperacionSintactica op);
    
}
