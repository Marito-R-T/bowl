/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.codigojava;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.commons.compiler.jdk.SimpleCompiler;

/**
 *
 * @author mari2bar
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*try {
            String javaSource = "public class prueba {\n"
                    + "\n"
                    + "    public String gg(int i) {\n"
                    + "        // TODO code application logic here\n"
                    + "        System.out.println(i);\n"
                    + "        return \"hola: \"+i;"
                    + "    }\n"
                    + "    \n"
                    + "    public String toString(){\n"
                    + "        return gg(2);\n"
                    + "    }\n"
                    + "}";
            SimpleCompiler compiler = new SimpleCompiler();
            compiler.cook(new StringReader(javaSource));
            Class cl = compiler.getClassLoader().loadClass("prueba");
            Object objeto = cl.newInstance();
            System.out.println(objeto.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }*/
        
        List<String> s = new ArrayList<>();
        s.add("a");
        s.add("b");
        for (String string : s) {
            System.out.println(string);
            if(s.size()<10){
                s.add(string+s.size());
            }
        }
    }

}
