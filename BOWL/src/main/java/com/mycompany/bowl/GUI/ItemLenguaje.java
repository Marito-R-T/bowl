/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.GUI;

import com.mycompany.bowl.analizadores.guardado.GuardarLenguaje;
import com.mycompany.bowl.backend.lenguaje.Lenguaje;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author mari2bar
 */
public class ItemLenguaje extends JMenuItem {

    private Lenguaje lenguaje;

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }
    private ItemLenguaje hermano;
    private BowlGUI bowl;

    public ItemLenguaje(Lenguaje lenguaje, BowlGUI bowl) {
        super(lenguaje.getInfo().getNombre());
        this.lenguaje = lenguaje;
        this.bowl = bowl;
        this.addActionListener((java.awt.event.ActionEvent evt) -> {
            hacerAccion(evt);
        });
    }

    public ItemLenguaje(Lenguaje lenguaje, BowlGUI bowl, boolean borrrado) {
        super(lenguaje.getInfo().getNombre());
        this.lenguaje = lenguaje;
        this.bowl = bowl;
        this.addActionListener((java.awt.event.ActionEvent evt) -> {
            eliminarItem(evt);
        });
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public ItemLenguaje getHermano() {
        return hermano;
    }

    public void setHermano(ItemLenguaje hermano) {
        this.hermano = hermano;
    }

    public void hacerAccion(ActionEvent evt) {
        if (bowl != null) {
            bowl.setSeleccionado(this);
            bowl.getLblSeleccionado().setText("Lenguaje Seleccionado: " + this.lenguaje.getInfo().getNombre() + "    versión: " + this.lenguaje.getInfo().getVersion()
                    + "   ext: " + this.lenguaje.getInfo().getExtension() + "   autor: " + this.lenguaje.getInfo().getAutor());
        }
    }

    public void eliminarItem(ActionEvent evt) {
        if (bowl != null) {
            if (JOptionPane.showConfirmDialog(bowl, "Esta seguro de eliminar el lenguaje: \n" + "---" + lenguaje.getInfo().getNombre() + "---")
                    == JOptionPane.OK_OPTION) {
                bowl.getMenuLenguajes().remove(this.hermano);
                bowl.getMenuBorrar().remove(this);
                if (bowl.getLblSeleccionado().getText().equals("Lenguaje Seleccionado: " + this.lenguaje.getInfo().getNombre() + "    versión: " + this.lenguaje.getInfo().getVersion()
                        + "   ext: " + this.lenguaje.getInfo().getExtension() + "   autor: " + this.lenguaje.getInfo().getAutor())) {
                    bowl.getLblSeleccionado().setText("");
                }
                File file = new File(this.getClass().getResource("/").getPath() + "lenguajes/" + lenguaje.getInfo().getNombre() + ".len");
                GuardarLenguaje.eliminar(new File(this.getClass().getResource("/").getPath() + "lenguajes/"), file);
            }
        }
    }

    public void cambiarLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
        hermano.setLenguaje(lenguaje);
    }

}
