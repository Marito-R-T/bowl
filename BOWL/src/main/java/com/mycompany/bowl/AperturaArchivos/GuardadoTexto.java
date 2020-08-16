/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.AperturaArchivos;

import com.mycompany.bowl.GUI.BowlGUI;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;

/**
 *
 * @author mari2bar
 */
public class GuardadoTexto {

    private final JFileChooser choser;

    public GuardadoTexto() {
        choser = new JFileChooser();
    }

    public boolean guardarArchivo(File file, String s) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(file);
            pw = new PrintWriter(fichero);
            pw.println(s);
            pw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public File guardarArchivoComo(String s, BowlGUI gui) {
        choser.showSaveDialog(gui);
        File archivo = choser.getSelectedFile();
        System.out.println(archivo.getName());
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println(s);
            pw.close();
            System.out.println(archivo.getName());
            return new File(archivo.getPath());
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}
