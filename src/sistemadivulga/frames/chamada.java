/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.frames;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Thiago
 */
public class chamada extends javax.swing.JFrame {

    /**
     * Creates new form chamada
     */
    public chamada() {
        initComponents();
        this.setSize(1360, 768);
        //fundo------------------------------------------------------------------------------------//
        BufferedImage resizedImg = new BufferedImage(1360, 768, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/chamar.png")).getImage(), 0, 0, 1360, 768, null);
        g.dispose();
        
        jLabel1.setIcon(new javax.swing.ImageIcon(resizedImg));
        //-----------------------------------------------------------------------------------------//
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JlSenha = new javax.swing.JLabel();
        jLsetor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        JlSenha.setFont(new java.awt.Font("Square721 BT", 0, 120)); // NOI18N
        JlSenha.setForeground(new java.awt.Color(255, 51, 51));
        JlSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlSenha.setText("10");
        getContentPane().add(JlSenha);
        JlSenha.setBounds(240, 310, 830, 90);
        getContentPane().add(jLsetor);
        jLsetor.setBounds(240, 100, 830, 610);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1400, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel JlSenha;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLsetor;
    // End of variables declaration//GEN-END:variables
}