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
public class ListaProducciones {
    
    private final List<Produccion> producciones;

    public ListaProducciones(Produccion produccion) {
        ArrayList<Produccion> prod = new ArrayList<>();
        prod.add(produccion);
        this.producciones = prod;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }
    
    
    
}
