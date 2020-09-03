/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.errores;

import com.mycompany.bowl.GUI.BowlGUI;
import com.mycompany.bowl.backend.lenguaje.lexico.Token;
import com.mycompany.bowl.backend.lenguaje.sintactico.NoTerminal;
import java_cup.runtime.Symbol;

/**
 *
 * @author mari2bar
 */
public class ErrorSintactico {

    public static void informarError(Symbol sym) {
        BowlGUI.txtTerminal.append("Error Sintactico en la letra: " + sym.value + "linea: "  + (sym.right + 1) + "    columna: " + (sym.left + 1) + "\n");
    }

    public static void errorLexico(Symbol sym) {
        BowlGUI.txtTerminal.append("Error Lexico en la letra: " + sym.value + "linea: "  + (sym.right + 1) + "    columna: " + (sym.left + 1) + "\n");
    }

    public static void errorToken(Token tok) {
        BowlGUI.txtTerminal.append("Error Lexico en la letra: " + tok.getToken() + "linea: "  + (tok.getLine() + 1) + "    columna: " + (tok.getColumn() + 1) + "\n");
    }

    public static void errorTokenSintactico(Token tok) {
        BowlGUI.txtTerminal.append("Error Sintactico en la letra: " + tok.getToken() + "linea: "  + (tok.getLine() + 1) + "    columna: " + (tok.getColumn() + 1) + "\n");
    }


    public static void errorProduccion(NoTerminal p, String s) {
        BowlGUI.txtTerminal.append("Error en la produccion: " + p.toString() + " error: " + s + "\n");
    }
    
    public static void errorNoTerminal(Symbol sym) {
        BowlGUI.txtTerminal.append("Error Lexico en la letra: " + sym.value + "linea: "  + (sym.right + 1) + "    columna: " + (sym.left + 1) + "\n");
    }
    
    public static void errorTerminal(Symbol sym) {
        BowlGUI.txtTerminal.append("Error Lexico en la letra: " + sym.value + "linea: "  + (sym.right + 1) + "    columna: " + (sym.left + 1) + "\n");
    }
    
    public static void errorCodigo(String s) {
        BowlGUI.txtTerminal.append("Error en la sección de codigo o semantica de una producción -- " + s + "\n");
    }
    
    public static void errorExpresion(String s) {
        BowlGUI.txtTerminal.append("Error en la expersion -- " + s + "\n");
    }
    
    public static void errorGeneral(String s) {
        BowlGUI.txtTerminal.append("Error -- " + s + "\n");
    }

}
