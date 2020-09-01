/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.analizadores.guardado;

import com.mycompany.bowl.backend.lenguaje.Lenguaje;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mari2bar
 */
public class GuardarLenguaje {

    public void guardarLenguaje(Lenguaje lenguaje, String nombre) {
        ObjectOutputStream ob = null;
        try {
            File carpeta = new File(this.getClass().getResource("/").getPath() + "lenguajes/");
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }
            File file = new File(this.getClass().getResource("/").getPath() + "lenguajes/" + nombre + ".len");
            if (!file.exists()) {
                file.createNewFile();
            }
            ob = new ObjectOutputStream(new FileOutputStream(file));
            ob.writeObject(lenguaje);
            ob.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                ob.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }

    public List<Lenguaje> leerLenguaje() {
        File file = new File(this.getClass().getResource("/").getPath() + "lenguajes/");
        List<Lenguaje> lenguajes = new ArrayList<>();
        if (file.exists()) {
            ObjectInputStream ob = null;
            for (File f : file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File directory, String fileName) {
                    return fileName.endsWith(".len");
                }

            })) {
                try {
                    ob = new ObjectInputStream(new FileInputStream(f));
                    Lenguaje leng = (Lenguaje) ob.readObject();
                    leng.getCodigo().inizializar();
                    lenguajes.add(leng);
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println(ex);
                }
            }
        }
        return lenguajes;

    }

    public static void eliminar(File carpeta, File archivo) {
        ObjectOutputStream ob = null;
        if (carpeta.exists()) {
            if (archivo.exists()) {
                archivo.delete();
            }
        }
    }

}
