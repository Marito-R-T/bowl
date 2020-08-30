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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class LALR implements Serializable {

    private List<IrA> tabla, ira;
    private final List<I> herradura;
    public List<I> eliminados;
    private OperacionSintactica[][] tablaTransicion;
    private final int terminal, noterminales;
    private final List<IrAN> optimizado;
    private final ListaProducciones prod;

    public LALR(List<I> herradura, int terminal, int noterminales, ListaProducciones prod) {
        this.tabla = new ArrayList<>(prod.getTabla());
        this.herradura = new ArrayList<>(herradura);
        this.eliminados = new ArrayList<>(herradura);
        this.terminal = terminal;
        this.noterminales = noterminales;
        this.prod = prod;
        this.optimizado = new ArrayList<>();
    }

    public void realizarirA() {
        this.tabla = new ArrayList<>();
        for (IrA irA : ira) {
            tabla.add(new IrA(irA));
        }
    }

    public void realizarirA2() {
        this.ira = new ArrayList<>();
        for (IrA irA : tabla) {
            ira.add(new IrA(irA));
        }
    }

    public void analizarLALR() {
        this.realizarirA2();
        int i = 0;
        while (i < herradura.size()) {
            int j = i + 1;
            while (j < herradura.size()) {
                if (herradura.get(i).esSimilar(herradura.get(j))) {
                    I n = eliminados.remove(j);
                    this.remplazarDestinos(n, eliminados.get(i));
                    this.remplazarPrimeros(n, eliminados.get(i));
                    if (realizarTabla()) {
                        herradura.get(i).agregarSiguientes(n.getProducciones());
                        herradura.remove(n);
                        this.realizarirA();
                        i = 0;
                        j = 0;
                    } else {
                        eliminados = new ArrayList<>(herradura);
                        for (int d = 0; d < eliminados.size(); d++) {
                            eliminados.get(d).setId2(d + 1);
                        }
                        this.realizarirA2();
                    }
                }
                j++;
            }
            i++;
        }
        if (this.realizarTabla()) {
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
    }

    public boolean realizarTabla() {
        for (int j = 0; j < eliminados.size(); j++) {
            eliminados.get(j).setId2(j + 1);
        }
        tablaTransicion = new OperacionSintactica[eliminados.size()][terminal + noterminales + 1];
        if (this.realizarGotoShift() && this.realizarRemove()) {
            return true;
        } else {
            return false;
        }
    }

    public void remplazarDestinos(I a, I r) {
        for (IrA irA : ira) {
            if (irA.getDestino().equals(a)) {
                irA.setDestino(r);
            }
        }
    }

    public void remplazarPrimeros(I a, I r) {
        for (IrA irA : ira) {
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

    public boolean realizarGotoShift() {
        for (IrA ir : ira) {
            boolean tr = true;
            int i = 0;
            if (ir.isTerminal()) {
                while (tr && prod.getTerminales().size() > i) {
                    if (prod.getTerminales().get(i).getNombre().equals(ir.getNodo().getNombre())) {
                        OperacionSintactica op = tablaTransicion[ir.getInicial().getId2() - 1][i], op1 = this.realizarOperacion(ir, ir.isTerminal());
                        if (op1 != null && (op == null || ((op instanceof Shift) && ((Shift) op).parecido((Shift) op1)))) {
                            tablaTransicion[ir.getInicial().getId2() - 1][i] = op1;
                            tr = false;
                        } else {
                            return false;
                        }
                    }
                    i++;
                }
            } else {
                while (tr && prod.getNoterminales().size() > i) {
                    if (prod.getNoterminales().get(i).getNombre().equals(ir.getNodo().getNombre())) {
                        OperacionSintactica op = tablaTransicion[ir.getInicial().getId2() - 1][i + terminal + 1], op1 = this.realizarOperacion(ir, ir.isTerminal());
                        if (op1 != null && (op == null || ((op instanceof GoTo) && ((GoTo) op).parecido((GoTo) op1)))) {
                            tablaTransicion[ir.getInicial().getId2() - 1][i + terminal + 1] = op1;
                            tr = false;
                        } else {
                            return false;
                        }
                    }
                    i++;
                }
            }
        }
        return true;
    }

    public OperacionSintactica realizarOperacion(IrA ir, boolean terminal) {
        if (terminal) {
            Shift s = new Shift(ir);
            return s;
        } else {
            GoTo g = new GoTo(ir);
            return g;
        }
    }

    public boolean realizarRemove() {
        for (I i : eliminados) {
            List<Remove> r = i.verRemoves();
            for (Remove remove : r) {
                if (remove.getT() != null) {
                    remove.buscarPro(prod.getProducciones());
                }
                int[] f = prod.posTerminal(remove.getT());
                OperacionSintactica op = this.tablaTransicion[i.getId2() - 1][f[0]];
                if (op == null || (op instanceof Remove && ((Remove) op).isIgual(remove))) {
                    this.tablaTransicion[i.getId2() - 1][f[0]] = remove;
                } else if (op instanceof Shift && i.getNivel()<=f[1]) {
                    this.tablaTransicion[i.getId() - 1][f[0]] = remove;
                }/* else {
                    System.out.println(remove);
                    System.out.println(f);
                    System.out.println(op);
                    this.tablaTransicion[i.getId() - 1][f[0]] = remove;
                }*/
            }
        }
        return true;
    }

    public OperacionSintactica[][] getTablaTransicion() {
        return tablaTransicion;
    }
}
