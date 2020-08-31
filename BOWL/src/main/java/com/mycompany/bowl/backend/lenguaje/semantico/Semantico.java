/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.semantico;

import java.io.Serializable;

/**
 *
 * @author mari2bar
 */
public class Semantico implements Serializable {

    private String id;
    private String texto;
    private String[][] ids;
    private Class[] clas;
    
    public Semantico(String texto) {
        this.texto = texto;
    }

    public void agregarTexto(String id, String[][] ids, String tipo) {
        this.id = id;
        this.ids = ids;
        this.texto = "public "+ tipo + " " + id + "("
                + agregarIDS(ids)
                + ") {\n"
                + texto
                + "}\n";
    }

    public Class[] getClas() {
        return clas;
    }

    private String agregarIDS(String[][] ids) {
        String s = "";
        this.ids = ids;
        clas = new Class[ids.length];
        for (int i = 0; i < ids.length; i++) {
            switch (ids[i][1]) {
                case "Integer":
                    clas[i] = Integer.class;
                    break;
                case "Float":
                    clas[i] = Float.class;
                    break;
                case "String":
                    clas[i] = String.class;
                    break;
                case "Object":
                    clas[i] = Object.class;
                    break;
                default:
                    break;
            }
            s += ids[i][1] + " " + ids[i][0];
            if (i + 1 < ids.length) {
                s += ", ";
            }
        }
        return s;
    }

    public String getId() {
        return id;
    }

    public String[][] getIds() {
        return ids;
    }

    public String getTexto() {
        return texto;
    }

}
