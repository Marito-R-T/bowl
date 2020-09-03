/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.backend.lenguaje.codigojava;

import java.io.StringReader;
import java.lang.reflect.Method;
import org.codehaus.janino.SimpleCompiler;

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
        String s = "if (val2 = = 2)";
        System.out.println(s.replaceAll("= =", "=="));
        /*System.out.println("resultado : " + (1 + 6 + 4 * 9 / 8 + 6 - 6));
        try {
            String javaSource = "public class prueba {\n"
                    + "\n"
                    + "    public String gg(Integer i) {\n"
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
            Object arne = cl.newInstance();
            //Method doWork = cl.getDeclaredMethod("gg");
            Method[] dw = cl.getDeclaredMethods();
            for (Method method : dw) {
                if (method.getName().equals("gg")) {
                    Class[] m = method.getParameterTypes();
                    Method doMethod = cl.getDeclaredMethod("gg", m);
                    System.out.println(doMethod);
                    Object resultado = doMethod.invoke(arne, new Object[]{14});
                    System.out.println(resultado);
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        /*List<String> s = new ArrayList<>();
        s.add("a");
        s.add("b");
        for (String string : s) {
            System.out.println(string);
            if(s.size()<10){
                s.add(string+s.size());
            }
        }*/
 /*Texto txt = new Texto();
        txt.escribirPath();*/
    }

    /*public static class Texto {

        public void escribirPath() {
            try {
                System.out.println(this.getClass().getResource("/").toURI().getPath());
            } catch (URISyntaxException ex) {
                Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
*
    }*/
}
