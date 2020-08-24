/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.lalr;

import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.Produccion;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class Remove extends OperacionSintactica {

    private final Produccion pr;
    private final Terminal t;
    private int pro;

    public Remove(Produccion pr, Terminal t) {
        this.pr = pr;
        this.t = t;
    }

    public boolean isIgual(Remove rem) {
        if (this instanceof Aceptar && rem instanceof Aceptar) {
            return true;
        } else if (this instanceof Aceptar || rem instanceof Aceptar) {
            return false;
        } else if (this.pro == rem.getPro()) {
            return true;
        } else {
            return false;
        }
    }

    public int getPro() {
        return pro;
    }

    public void buscarPro(List<Produccion> ter) {
        for (int i = 0; i < ter.size(); i++) {
            if (pr.esSim(ter.get(i))) {
                pro = i - 1;
            }
        }
    }

    public void setPro(int pro) {
        this.pro = pro;
    }

    public Terminal getT() {
        return t;
    }

    public Produccion getPr() {
        return pr;
    }

    @Override
    public String toString() {
        if (this instanceof Aceptar) {
            return "AAA";
        }
        return "R" + (pro + 1);
    }

}
