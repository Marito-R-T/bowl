/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje;

/**
 *
 * @author moise
 */
public class InfoLenguaje {

    private String nombre, version, autor, extension;
    private Integer lanzamiento;

    public InfoLenguaje() {

    }

    public void ingresarDato(InfoLenguaje info) {
        if (info.getAutor() != null && this.autor == null) {
            this.autor = info.getAutor();
        } else if (info.getExtension() != null && this.extension == null) {
            this.extension = info.getExtension();
        } else if (info.getLanzamiento() != null && this.lanzamiento == null){
            this.lanzamiento = info.getLanzamiento();
        } else if (info.getNombre() != null && this.nombre == null){
            this.nombre = info.getNombre();
        } else if(info.getVersion() != null && this.version == null){
            this.version = info.getVersion();
        } else {
            
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getVersion() {
        return version;
    }

    public String getAutor() {
        return autor;
    }

    public String getExtension() {
        return extension;
    }

    public Integer getLanzamiento() {
        return lanzamiento;
    }

}
