/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class I {
    
    private final List<Produccion> producciones = new ArrayList<>();
    private boolean marcado = false;
    
    public void setMarcado(boolean marcado){
        this.marcado = marcado;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }
    
}
