/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico.producciones;

import com.mycompany.bowl.backend.lenguaje.sintactico.Aceptacion;
import com.mycompany.bowl.backend.lenguaje.sintactico.LALR;
import com.mycompany.bowl.backend.lenguaje.sintactico.NoTerminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.NodoSintactico;
import com.mycompany.bowl.backend.lenguaje.sintactico.TablaDeSimbolos;
import com.mycompany.bowl.backend.lenguaje.sintactico.Terminal;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.GoTo;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.OperacionSintactica;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Remove;
import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.Shift;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class ListaProducciones {

    private final List<Produccion> producciones;
    private List<NoTerminal> noterminales;
    private List<Terminal> terminales;
    private final List<I> herradura;
    private final List<IrA> tabla;
    private OperacionSintactica[][] tablaTransicion;
    private LALR lalr;

    public ListaProducciones(Produccion produccion) {
        ArrayList<Produccion> prod = new ArrayList<>();
        prod.add(produccion);
        this.producciones = prod;
        noterminales = new ArrayList<>();
        herradura = new ArrayList<>();
        tabla = new ArrayList<>();
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void realizarLALR(TablaDeSimbolos tabla) {
        if (producciones.size() > 0) {
            ArrayList<NodoSintactico> n = new ArrayList<>();
            n.add(producciones.get(0).getPrimerNodo());
            n.add(new Aceptacion());
            Produccion pr = new ProduccionInicial(n);
            producciones.add(0, pr);
            noterminales = tabla.getNoterminal();
            terminales = tabla.getTerminal();
            noterminales.add(0, pr.getPrimerNodo());
            for (NoTerminal noTerminal : noterminales) {
                int i = 0;
                while (!noTerminal.isAnulable() && i < producciones.size()) {
                    if (producciones.get(i).getPrimerNodo().getNombre().equals(noTerminal.getNombre()) && producciones.get(i).getProducidos().isEmpty()) {
                        noTerminal.setAnulable(true);
                    }
                    i++;
                }
            }
            for (NoTerminal noterminale : noterminales) {
                ListaProducciones.encontrarPrimeros(this, noterminale, noterminale);

            }
            I nuevo = new I();
            Produccion p = new ProduccionInicial(producciones.get(0));
            p.setPospunto(0);
            nuevo.getProducciones().add(p);
            nuevo.setId(1);
            nuevo = this.cerradura(nuevo);
            herradura.add(nuevo);
            this.ir_a(nuevo);
            for (IrA irA : this.tabla) {
                System.out.println(irA);
            }
            tablaTransicion = new OperacionSintactica[herradura.size()][noterminales.size() + terminales.size() + 1];
            this.realizarGotoShift();
            this.realizarRemove();
            for (Terminal terminale : terminales) {
                System.out.print("|" + terminale + "|");
            }
            System.out.print("|$|");
            for (NoTerminal noterminal : noterminales) {
                System.out.print("|" + noterminal + "|");
            }
            System.out.println("");
            for (int i = 0; i < tablaTransicion.length; i++) {
                for (OperacionSintactica operacionSintactica : tablaTransicion[i]) {
                    System.out.print("|" + operacionSintactica + "|");
                }
                System.out.println("");
            }
            lalr = new LALR(herradura, terminales.size(), noterminales.size(), this);
            lalr.analizarLALR();
        } else {

        }
    }

    public List<IrA> getTabla() {
        return tabla;
    }

    public void realizarGotoShift() {
        for (IrA ir : tabla) {
            boolean tr = true;
            int i = 0;
            if (ir.isTerminal()) {
                while (tr && terminales.size() > i) {
                    if (terminales.get(i).getNombre().equals(ir.getNodo().getNombre())) {
                        tablaTransicion[ir.getInicial().getId() - 1][i] = this.realizarOperacion(ir, ir.isTerminal());
                        tr = false;
                    }
                    i++;
                }
            } else {
                while (tr && noterminales.size() > i) {
                    if (noterminales.get(i).getNombre().equals(ir.getNodo().getNombre())) {
                        tablaTransicion[ir.getInicial().getId() - 1][i + this.terminales.size() + 1] = this.realizarOperacion(ir, ir.isTerminal());
                        tr = false;
                    }
                    i++;
                }
            }
        }
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

    public void realizarRemove() {
        for (I i : herradura) {
            List<Remove> r = i.verRemoves();
            for (Remove remove : r) {
                if (remove.getT() != null) {
                    remove.buscarPro(producciones);
                }
                int f = this.posTerminal(remove.getT());
                this.tablaTransicion[i.getId() - 1][f] = remove;
            }
        }
    }

    public int posTerminal(Terminal t) {
        if (t == null) {
            return terminales.size();
        }
        if (t instanceof Aceptacion) {
            return terminales.size();
        } else {
            for (int i = 0; i < terminales.size(); i++) {
                if (t.getNombre().equals(terminales.get(i).getNombre())) {
                    return i;
                }
            }
        }
        return 0;
    }

    public I cerradura(I nuevo) {
        int i = 0;
        while (nuevo.getProducciones().size() > i) {
            Produccion p = nuevo.getProducciones().get(i);
            if (p.getPospunto() < p.getProducidos().size() && p.getProducidos().get(p.getPospunto()) instanceof NoTerminal) {
                for (Produccion produccione : producciones) {
                    if (p.getPospunto() < p.getProducidos().size() && produccione.getPrimerNodo().getNombre().equals(p.getProducidos().get(p.getPospunto()).getNombre())) {
                        Produccion pro;
                        if (produccione instanceof ProduccionInicial) {
                            pro = new ProduccionInicial(produccione);
                        } else {
                            pro = new Produccion(produccione);
                        }
                        pro.setPospunto(0);
                        pro.getSiguientes().clear();
                        pro.getSiguientes().addAll(p.obtenerSiguientes(this));
                        if (!nuevo.existe(pro)) {
                            nuevo.getProducciones().add(pro);
                        }
                    }
                }
            }
            i++;
        }
        return nuevo;
    }

    public void ir_a(I estado) {
        for (NoTerminal terminale : noterminales) {
            I nuevo = new I();
            for (Produccion produccion : estado.getProducciones()) {
                if (produccion.getProducidos().size() > produccion.getPospunto()
                        && terminale.getNombre().equals(produccion.getProducidos().get(produccion.getPospunto()).getNombre())) {
                    Produccion p;
                    if (produccion instanceof ProduccionInicial) {
                        p = new ProduccionInicial(produccion);
                    } else {
                        p = new Produccion(produccion);
                    }
                    p.sumarPospunto();
                    nuevo.getProducciones().add(p);
                }
            }
            if (!nuevo.getProducciones().isEmpty()) {
                nuevo = this.cerradura(nuevo);
                boolean b = true;
                for (I i : herradura) {
                    if (i.esIgual(nuevo)) {
                        nuevo = i;
                        b = false;
                        break;
                    }
                }
                IrA ira = new IrA(estado, nuevo, terminale);
                tabla.add(ira);
                if (b) {
                    herradura.add(nuevo);
                    nuevo.setId((herradura.size()));
                    this.ir_a(nuevo);
                }
            }
        }
        for (Terminal terminale : terminales) {
            I nuevo = new I();
            for (Produccion produccion : estado.getProducciones()) {
                if (produccion.getProducidos().size() > produccion.getPospunto()
                        && terminale.getNombre().equals(produccion.getProducidos().get(produccion.getPospunto()).getNombre())) {
                    Produccion p;
                    if (produccion instanceof ProduccionInicial) {
                        p = new ProduccionInicial(produccion);
                    } else {
                        p = new Produccion(produccion);
                    }
                    p.sumarPospunto();
                    nuevo.getProducciones().add(p);
                }
            }
            if (!nuevo.getProducciones().isEmpty()) {
                nuevo = this.cerradura(nuevo);
                boolean b = true;
                for (I i : herradura) {
                    if (i.esIgual(nuevo)) {
                        nuevo = i;
                        b = false;
                        break;
                    }
                }
                IrA ira = new IrA(estado, nuevo, terminale);
                tabla.add(ira);
                if (b) {
                    herradura.add(nuevo);
                    nuevo.setId((herradura.size()));
                    this.ir_a(nuevo);
                }
            }
        }
    }

    public static void encontrarPrimeros(ListaProducciones n, NoTerminal noterminal, NoTerminal primeros) {
        for (Produccion produccione : n.getProducciones()) {
            if (produccione.getPrimerNodo().getNombre().equals(primeros.getNombre()) && !produccione.enproceso) {
                produccione.ingresarPrimeros(noterminal, n);
            }
        }
    }

    public static boolean isAnulable(NodoSintactico no, ListaProducciones n) {
        if (no instanceof NoTerminal) {
            for (NoTerminal noterminale : n.getNoterminales()) {
                if (noterminale.getNombre().equals(no.getNombre())) {
                    return noterminale.isAnulable();
                }
            }
        }
        return false;
    }

    public List<NoTerminal> getNoterminales() {
        return noterminales;
    }

    public List<Terminal> getPrimeros(NodoSintactico n) {
        List<Terminal> t = new ArrayList<>();
        for (Terminal terminale : terminales) {
            if (n.getNombre().equals(terminale.getNombre())) {
                t.add(terminale);
                return t;
            }
        }
        for (NoTerminal noterminale : noterminales) {
            if (n.getNombre().equals(noterminale.getNombre())) {
                System.out.println(noterminale.getPrimeros().size());
                t.addAll(noterminale.getPrimeros());
                return t;
            }
        }
        return t;
    }

    public List<Terminal> getTerminales() {
        return terminales;
    }

}
