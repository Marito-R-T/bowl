/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.semantico;

import com.mycompany.bowl.backend.lenguaje.sintactico.producciones.Produccion;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.SimpleCompiler;

/**
 *
 * @author mari2bar
 */
public class AnalisisCodigoJava implements Serializable {

    private final String nombre;
    private String codigo;
    private Class cl;

    public AnalisisCodigoJava(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public void ingresarClase() {
        /*try {
            Object arne = cl.newInstance();
            Method doWork = cl.getDeclaredMethod("mains");
            Object resultado = doWork.invoke(arne, new Object[0]);
            System.out.println(resultado);
        } catch (/*CompileException | IOException
                | ClassNotFoundException | InstantiationException
                | IllegalAccessException | NoSuchMethodException
                | SecurityException | IllegalArgumentException
                | InvocationTargetException ex) {
            Logger.getLogger(AnalisisCodigoJava.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void inizializar() {
        try {
            SimpleCompiler compiler;
            compiler = new SimpleCompiler();
            compiler.cook(new StringReader(codigo));
            cl = compiler.getClassLoader().loadClass(nombre);
        } catch (CompileException | IOException
                /*| ClassNotFoundException | InstantiationException
                | IllegalAccessException | NoSuchMethodException*/
                | SecurityException | IllegalArgumentException | ClassNotFoundException ex
                /*| InvocationTargetException ex*/) {
            Logger.getLogger(AnalisisCodigoJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void hacermetodos(List<Produccion> pro) {
        try {
            String s = "\n";
            for (Produccion produccion : pro) {
                if (produccion.getSemantico() != null) {
                    s += produccion.getSemantico().getTexto() + "\n";
                }
            }
            codigo = "public class " + nombre + "{\n" + codigo + s + "\n}\n";
            System.out.println(codigo);
            SimpleCompiler compiler;
            compiler = new SimpleCompiler();
            compiler.cook(new StringReader(codigo));
            cl = compiler.getClassLoader().loadClass(nombre);
        } catch (CompileException | IOException
                /*| ClassNotFoundException | InstantiationException
                | IllegalAccessException | NoSuchMethodException*/
                | SecurityException | IllegalArgumentException | ClassNotFoundException ex
                /*| InvocationTargetException ex*/) {
            Logger.getLogger(AnalisisCodigoJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Object hacerMetodo(String metodo, Object[] identidades, Class[] clas) {
        try {
            Object arne = cl.newInstance();
            Method doWork = cl.getDeclaredMethod(metodo, clas);
            Object resultado = doWork.invoke(arne, identidades);
            return resultado;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(AnalisisCodigoJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setCl(Class cl) {
        this.cl = cl;
    }

}
