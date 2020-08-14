/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bowl.GUI;

import com.mycompany.bowl.AperturaArchivos.AperturaTexto;
import com.mycompany.bowl.analizadores.LexicoLenguaje;
import com.mycompany.bowl.analizadores.SintaxisLenguajes;
import com.mycompany.bowl.backend.lenguaje.Lenguaje;
import java.io.File;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author mari2bar
 */
public class BowlGUI extends javax.swing.JFrame {

    private final AperturaTexto apertura;
    /**
     * Creates new form BowlGUI
     */
    public BowlGUI() {
        apertura = new AperturaTexto();
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblSeleccionado = new javax.swing.JLabel();
        tabbedTexto = new javax.swing.JTabbedPane();
        lblColFil = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemAbrir = new javax.swing.JMenuItem();
        menuLenguajes = new javax.swing.JMenu();
        menuEjecutar = new javax.swing.JMenu();
        itemCargarLenguaje = new javax.swing.JMenuItem();
        menuVer = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabbedTexto)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addGap(0, 672, Short.MAX_VALUE)
                        .addComponent(lblColFil, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblColFil, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuArchivo.setText("Archivo");

        itemAbrir.setText("Abrir");
        itemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemAbrir);

        jMenuBar1.add(menuArchivo);

        menuLenguajes.setText("Lenguajes");
        jMenuBar1.add(menuLenguajes);

        menuEjecutar.setText("Ejecutar");

        itemCargarLenguaje.setText("Cargar Lenguaje");
        itemCargarLenguaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCargarLenguajeActionPerformed(evt);
            }
        });
        menuEjecutar.add(itemCargarLenguaje);

        jMenuBar1.add(menuEjecutar);

        menuVer.setText("Ver");
        jMenuBar1.add(menuVer);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirActionPerformed
        // TODO add your handling code here:
        File file = apertura.abrirTexto(this, "len");
        if(file != null && file.exists()){
            String texto = apertura.leerArchivo(file);
            JScrollPane pane = new JScrollPane();
            JTextPane area = new JTextPane();
            area.setText(texto);
            NumeroLinea linea = new NumeroLinea(area);
            pane.setViewportView(area);
            pane.setRowHeaderView(linea);
            
            tabbedTexto.addTab(file.getName(),null, pane, file.getPath());
        }
    }//GEN-LAST:event_itemAbrirActionPerformed

    private void itemCargarLenguajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCargarLenguajeActionPerformed
        try {
            // TODO add your handling code here:
            String s = apertura.leerTexto(tabbedTexto.getToolTipTextAt(tabbedTexto.getSelectedIndex()));
            SintaxisLenguajes lenguajes = new SintaxisLenguajes(new LexicoLenguaje(new StringReader(s)));
            Lenguaje len = (Lenguaje) lenguajes.parse().value;
            System.out.println(len.getInfo().getAutor());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_itemCargarLenguajeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemCargarLenguaje;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblColFil;
    private javax.swing.JLabel lblSeleccionado;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuEjecutar;
    private javax.swing.JMenu menuLenguajes;
    private javax.swing.JMenu menuVer;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTabbedPane tabbedTexto;
    // End of variables declaration//GEN-END:variables
}
