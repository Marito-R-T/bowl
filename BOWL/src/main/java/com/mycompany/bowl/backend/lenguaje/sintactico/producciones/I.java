/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.sintactico.Aceptacion;
import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Aceptar;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Remove;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class I {

    private final List<Produccion> producciones = new ArrayList<>();
    private int id, pos;
    private boolean marcado = false;

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setId2(int id2) {
        this.pos = id2;
    }

    public int getId2() {
        return pos;
    }

    public int getId() {
        return id;
    }

    public List<Remove> verRemoves() {
        List<Remove> r = new ArrayList<>();
        for (Produccion produccion : producciones) {
            if (produccion instanceof ProduccionInicial && produccion.getPospunto()==1) {
                Aceptar nuevo = new Aceptar(produccion, null);
                r.add(nuevo);
            } else {
                if (produccion.getProducidos().size() <= produccion.getPospunto()) {
                    for (Terminal siguiente : produccion.getSiguientes()) {
                        Remove nuevo = new Remove(produccion, siguiente);
                        r.add(nuevo);
                    }
                }
            }
        }
        return r;
    }

    public boolean existe(Produccion p) {
        for (Produccion produccione : producciones) {
            if (produccione.getPrimerNodo().getNombre().equals(p.getPrimerNodo().getNombre())
                    && produccione.producidosIgual(p.getProducidos())
                    && p.getPospunto() == produccione.getPospunto()) {
                for (Terminal siguiente : p.getSiguientes()) {
                    if (!this.existeSiguiente(siguiente, produccione)) {
                        produccione.getSiguientes().add(siguiente);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void setId(int id) {
        this.id = id;
        this.pos = id;
    }

    public boolean existeSiguiente(Terminal siguiente, Produccion produccion) {
        for (Terminal siguiente1 : produccion.getSiguientes()) {
            if (!(siguiente instanceof Aceptacion)) {
                if (siguiente1.getNombre().equals(siguiente.getNombre())) {
                    return true;
                }
            } else if (siguiente instanceof Aceptacion && siguiente1 instanceof Aceptacion) {
                return true;
            }
        }
        return false;
    }

    public boolean esIgual(I similar) {
        if (similar.getProducciones().size() == this.producciones.size()) {
            for (Produccion produccion : similar.getProducciones()) {
                if (!this.existeProduccion(produccion)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean esSimilar(I similar) {
        if (similar.getProducciones().size() == this.producciones.size()) {
            for (Produccion produccion : similar.getProducciones()) {
                if (!this.existeSimilar(produccion)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean existeProduccion(Produccion produccion) {
        for (Produccion object : producciones) {
            if (object.esIgual(produccion)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeSimilar(Produccion produccion) {
        for (Produccion object : producciones) {
            if (object.esMuySimilar(produccion)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "I-" + id +"/"+pos;
    }

    public void agregarSiguientes(List<Produccion> prod) {
        for (Produccion produccion : prod) {
            for (Produccion produccione : producciones) {
                if(produccion.esSim(produccion)){
                    produccione.agregarSiguientes(produccion.getSiguientes());
                }
            }
        }
    } 
    
}
