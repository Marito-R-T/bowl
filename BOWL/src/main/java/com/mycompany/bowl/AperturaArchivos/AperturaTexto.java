/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.AperturaArchivos;

import com.mycompany.bowl.GUI.BowlGUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mari2bar
 */
public class AperturaTexto {
    
    private final JFileChooser choser;

    public AperturaTexto() {
        choser = new JFileChooser();
    }
    
    public File abrirTexto(BowlGUI bowl, String extension){
        choser.setFileFilter(new FileNameExtensionFilter("la extensión permitida es: "+ extension, extension));
        if(choser.showOpenDialog(bowl) == JFileChooser.APPROVE_OPTION){
            return choser.getSelectedFile();
        }
        return null;
    }
    
    public String leerArchivo(File file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s1, s2 = "";
            while((s1=reader.readLine())!=null){
                s2+=s1+"\n";
            }
            reader.close();
            return s2;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public String leerTexto(String toolTipTextAt) {
        BufferedReader reader = null;
        try {
            File file = new File(toolTipTextAt);
            reader = new BufferedReader(new FileReader(file));
            String s1="", s2;
            while((s2=reader.readLine())!=null){
                s1+=s2;
            }
            reader.close();
            return s1;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
}
