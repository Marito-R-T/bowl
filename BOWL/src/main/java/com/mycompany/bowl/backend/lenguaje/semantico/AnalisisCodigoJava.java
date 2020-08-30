/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.semantico;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.SimpleCompiler;

/**
 *
 * @author mari2bar
 */
public class AnalisisCodigoJava implements Serializable {

    private String codigo, nombre;

    public AnalisisCodigoJava(String codigo, String nombre) {
        this.codigo = "class " + nombre + "{\n"
                + codigo + "}\n";
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public void ingresarClase() {
        try {
            SimpleCompiler compiler;
            compiler = new SimpleCompiler();
            compiler.cook(new StringReader(codigo));
            Class cl = compiler.getClassLoader().loadClass(nombre);
            Object arne = cl.newInstance();
            Method doWork = cl.getDeclaredMethod("mains");
            Object resultado = doWork.invoke(arne, new Object[0]);
            System.out.println(resultado);
        } catch (CompileException | IOException
                | ClassNotFoundException | InstantiationException
                | IllegalAccessException | NoSuchMethodException
                | SecurityException | IllegalArgumentException
                | InvocationTargetException ex) {
            Logger.getLogger(AnalisisCodigoJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
