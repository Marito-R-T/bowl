/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.GoTo;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.OperacionSintactica;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Remove;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Shift;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.I;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.IrA;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.IrAN;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.ListaProducciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class LALR {

    private final List<IrA> tabla, ira;
    private final List<I> herradura, eliminados;
    private OperacionSintactica[][] tablaTransicion;
    private final int terminal, noterminales;
    private final List<IrAN> optimizado;
    private final ListaProducciones prod;

    public LALR(List<I> herradura, int terminal, int noterminales, ListaProducciones prod) {
        this.tabla = new ArrayList<>(prod.getTabla());
        this.herradura = new ArrayList<>(herradura);
        this.terminal = terminal;
        this.noterminales = noterminales;
        this.prod = prod;
        this.optimizado = new ArrayList<>();
        this.ira = new ArrayList<>();
        this.eliminados = new ArrayList<>();
    }

    public void analizarLALR() {
        System.out.println(herradura.size());
        int i = 0;
        while (i < herradura.size()) {
            int j = i+1;
            while (j < herradura.size()) {
                if (herradura.get(i).esSimilar(herradura.get(j))) {
                    I n = herradura.remove(j);
                    eliminados.add(n);
                    this.remplazarDestinos(n, herradura.get(i));
                    this.remplazarPrimeros(n, herradura.get(i));
                    j = i;
                }
                j++;
            }
            i++;
        }
                    System.out.println("holaaaa");
        for (int j = 0; j < herradura.size(); j++) {
            herradura.get(j).setId(j + 1);
        }
        tablaTransicion = new OperacionSintactica[herradura.size()][terminal + noterminales + 1];
        this.realizarGotoShift();
        this.realizarRemove();
        for (Terminal terminale : prod.getTerminales()) {
            System.out.print("|" + terminale + "|");
        }
        System.out.print("|$|");
        for (NoTerminal noterminal : prod.getNoterminales()) {
            System.out.print("|" + noterminal + "|");
        }
        System.out.println("");
        for (int j = 0; j < tablaTransicion.length; j++) {
            for (OperacionSintactica operacionSintactica : tablaTransicion[j]) {
                System.out.print("|" + operacionSintactica + "|");
            }
            System.out.println("");
        }
    }

    public void remplazarDestinos(I a, I r) {
        for (IrA irA : tabla) {
            if (irA.getDestino().equals(a)) {
                irA.setDestino(r);
            }
        }
    }

    public void remplazarPrimeros(I a, I r) {
        for (IrA irA : tabla) {
            if (irA.getInicial().equals(a)) {
                irA.setInicial(r);
            }
        }
        /*for (IrAN irAN : optimizado) {
            for (IrA irA : tabla) {
                if(irAN.getUltimo().equals(irA.getInicial())) {
                    
                }
            }
        }*/
    }

    public void realizarGotoShift() {
        for (IrA ir : tabla) {
            boolean tr = true;
            int i = 0;
            if (ir.isTerminal()) {
                while (tr && prod.getTerminales().size() > i) {
                    if (prod.getTerminales().get(i).getNombre().equals(ir.getNodo().getNombre())) {
                        OperacionSintactica op = tablaTransicion[ir.getInicial().getId() - 1][i], op1 = this.realizarOperacion(ir, ir.isTerminal());
                        if (op1 != null && (op == null || ((op instanceof Shift) && ((Shift) op).equals((Shift) op1)))) {
                            tablaTransicion[ir.getInicial().getId() - 1][i] = op1;
                            tr = false;
                        } else {

                        }
                    }
                    i++;
                }
            } else {
                while (tr && prod.getNoterminales().size() > i) {
                    if (prod.getNoterminales().get(i).getNombre().equals(ir.getNodo().getNombre())) {
                        OperacionSintactica op = tablaTransicion[ir.getInicial().getId() - 1][i], op1 = this.realizarOperacion(ir, ir.isTerminal());
                        if (op1 != null && (op == null || ((op instanceof GoTo) &&((GoTo) op).equals((GoTo) op1)))) {
                            tablaTransicion[ir.getInicial().getId() - 1][i] = op1;
                            tr = false;
                        } else {

                        }
                    }
                    i++;
                }
            }
        }
    }

    public OperacionSintactica realizarOperacion(IrA ir, boolean terminal) {
        if (terminal) {
            Shift s = new Shift(ir, optimizado);
            return s;
        } else {
            GoTo g = new GoTo(ir, optimizado);
            return g;
        }
    }

    public void realizarRemove() {
        for (I i : herradura) {
            List<Remove> r = i.verRemoves();
            for (Remove remove : r) {
                if (remove.getT() != null) {
                    remove.buscarPro(prod.getProducciones());
                }
                int f = prod.posTerminal(remove.getT());
                OperacionSintactica op = this.tablaTransicion[i.getId() - 1][f];
                if(op == null || (op instanceof Remove && ((Remove)op).isIgual(remove))){
                this.tablaTransicion[i.getId() - 1][f] = remove;
                }
            }
        }
    }
}
