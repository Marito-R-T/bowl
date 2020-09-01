/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.sintactico;

import com.mycompany.bowl.backend.lenguaje.sintactico.lalr.OperacionSintactica;
import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.ProduccionInicial;
import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class TablaLALR implements Serializable {

    private String tabla;
//

    public TablaLALR(OperacionSintactica[][] op, String nombre, TablaDeSimbolos tabla) {
        this.tabla = "<!doctype html>\n"
                + "<html lang=\"en\">\n"
                + "  <head>\n"
                + "    <!-- Required meta tags -->\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
                + "\n"
                + "    <!-- Bootstrap CSS -->\n"
                + "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n"
                + "\n"
                + "    <title>Tabla LALR de: " + nombre + "</title>\n"
                + "  </head>\n"
                + "  <body style=\"background-color:white\">\n"
                + "     <table class=\"table\">\n"
                + "         <thead class=\"thead-dark\">\n"
                + "             <tr>\n"
                + this.realizarEncabezado(tabla)
                + "             </tr>\n"
                + "         </thead>\n"
                + "         <tbody>\n"
                + this.realizarTabla(op, tabla)
                + "         </tbody>\n"
                + "     </table>\n"
                + "    <!-- Optional JavaScript -->\n"
                + "    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\n"
                + "    <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"
                + "    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\" integrity=\"sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN\" crossorigin=\"anonymous\"></script>\n"
                + "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\" integrity=\"sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV\" crossorigin=\"anonymous\"></script>\n"
                + "  </body>\n"
                + "</html>";
    }

    public final String realizarEncabezado(TablaDeSimbolos tabla) {
        String s = "                 <th scope=\"col\">#</th>\n";
        for (Terminal terminal : tabla.getTerminal()) {
            s += "                 <th scope=\"col\">" + terminal.getNombre() + "</th>\n";
        }
        s += "                 <th scope=\"col\">$$$</th>\n";
        for (NoTerminal terminal : tabla.getNoterminal()) {
            if (!terminal.getNombre().equals(ProduccionInicial.nombre)) {
                s += "                 <th scope=\"col\">" + terminal.getNombre() + "</th>\n";
            }
        }
        return s;
    }

    public final String realizarTabla(OperacionSintactica[][] op, TablaDeSimbolos tab) {
        String s = "";
        for (int i = 0; i < op.length; i++) {
            s += "             <tr>\n"
                    + "                 <th class=\"table-dark\" scope=\"row\">" + (i + 1) + "</th>\n";
            for (int j = 0; j < op[i].length; j++) {
                if (j != tab.getTerminal().size() + 1) {
                    if (op[i][j] != null) {
                        s += "                 <th>" + op[i][j] + "</th>\n";
                    } else {
                        s += "                 <th>---</th>\n";
                    }
                }
            }
            s += "             </tr>\n";
        }
        return s;
    }

    public String getTabla() {
        return tabla;
    }

    public void abrirHTML() {

    }

}
