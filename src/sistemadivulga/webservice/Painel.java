/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.webservice;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.jws.WebService;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javazoom.jl.decoder.JavaLayerException;
import sistemadivulga.frames.Tela;
import sistemadivulga.frames.chamada;
 
/**
 *
 * @author Thiago
 */
@WebService(endpointInterface = "sistemadivulga.webservice.server")
public class Painel implements server {
    
    public static PrintService impressora;
    public static Tela tela;
    public chamada c;
    public static Timer timer = new Timer(5000, null);
    public static int tempo = 0;
    
    public int cert, cert_a;
    public int cert_pref, cert_pref_a;
    public int reg, reg_a;
    
    public void TimerCertidao() {
        tempo = 0;
        c = new chamada();
        c.setVisible(true);
        cert_a = cert_a+1;
        c.JlSenha.setText(Integer.toString(cert_a));
        c.JlSenha.setBounds(c.JlSenha.getX()-15, c.JlSenha.getY()-10, c.JlSenha.getWidth(), c.JlSenha.getHeight());
        //fundo------------------------------------------------------------------------------------//
        BufferedImage resizedImg = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/CERTIDOESCHAMAR.png")).getImage(), 0, 0, 800, 600, null);
        g.dispose();
        c.jLsetor.setIcon(new javax.swing.ImageIcon(resizedImg));
        //-----------------------------------------------------------------------------------------//
        timer.addActionListener(Certidoes);
        timer.start();
    }
    
    public void TimerPreferencial() {
        tempo = 0;
        c = new chamada();
        c.setVisible(true);
        cert_pref_a = cert_pref_a+1;
        c.JlSenha.setText(Integer.toString(cert_pref_a));
        c.JlSenha.setBounds(c.JlSenha.getX()-15, c.JlSenha.getY()-10, c.JlSenha.getWidth(), c.JlSenha.getHeight());
        //fundo------------------------------------------------------------------------------------//
        BufferedImage resizedImg = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/CERTIDOESPREFCHAMAR.png")).getImage(), 0, 0, 800, 600, null);
        g.dispose();
        c.jLsetor.setIcon(new javax.swing.ImageIcon(resizedImg));
        //-----------------------------------------------------------------------------------------//
        timer.addActionListener(Preferencial);
        timer.start();
    }
    
    public void TimerRegistros() {
        tempo = 0;
        c = new chamada();
        c.setVisible(true);
        reg_a = reg_a+1;
        c.JlSenha.setText(Integer.toString(reg_a));
        c.JlSenha.setBounds(c.JlSenha.getX()-15, c.JlSenha.getY()-10, c.JlSenha.getWidth(), c.JlSenha.getHeight());
        //fundo------------------------------------------------------------------------------------//
        BufferedImage resizedImg = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/REGISTROSCHAMAR.png")).getImage(), 0, 0, 800, 600, null);
        g.dispose();
        c.jLsetor.setIcon(new javax.swing.ImageIcon(resizedImg));
        //-----------------------------------------------------------------------------------------//
        timer.addActionListener(Registros);
        timer.start();
    }
    
    public ActionListener Certidoes = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        if (tempo==0){
                            tempo = 1;                            
                            tela.jLNCer.setText(Integer.toString(cert_a));
                        }else{
                            timer.stop();
                            c.dispose();
                        }
                    }
                });
        }
    };
    
    public ActionListener Preferencial = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        if (tempo==0){
                            tempo = 1;                            
                            tela.jLNCerP.setText(Integer.toString(cert_pref_a));
                        }else{
                            timer.stop();
                            c.dispose();
                        }
                    }
                });
        }
    };
    
    public ActionListener Registros = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        if (tempo==0){
                            tempo = 1;                            
                            tela.jLNReg.setText(Integer.toString(reg_a));
                        }else{
                            timer.stop();
                            c.dispose();
                        }
                    }
                });
        }
    };
    
    @Override
    public void ImprimeRegistros() {
        this.detectaImpressoras("Generic / Text Only");
        this.centraliza();
        this.imprime("CARTORIO MEZZARI\n");
        this.imprime("Primeiro Cartorio de\n");
        this.imprime("Imoveis de Pelotas\n");
        this.imprime(LocalDateTime.now().toString()+"\n\n\n");
        this.imprimeReg(Integer.toString(reg+1)+"\n\n\n");
        this.acionarGuilhotina();
        reg++;        
    }

    @Override
    public void ImprimePreferencial() {
        this.detectaImpressoras("Generic / Text Only");
        this.centraliza();
        this.imprime("CARTORIO MEZZARI\n");
        this.imprime("Primeiro Cartorio de\n");
        this.imprime("Imoveis de Pelotas\n");
        this.imprime(LocalDateTime.now().toString()+"\n\n\n");
        this.imprimeCertPref(Integer.toString(cert_pref+1)+"\n\n\n");
        this.acionarGuilhotina();
        cert_pref++;
    }

    @Override
    public void ImprimeCertidoes() {
        this.detectaImpressoras("Generic / Text Only");
        this.centraliza();
        this.imprime("CARTORIO MEZZARI\n");
        this.imprime("Primeiro Cartorio de\n");
        this.imprime("Imoveis de Pelotas\n");
        this.imprime(LocalDateTime.now().toString()+"\n\n\n");
        this.imprimeCert(Integer.toString(cert+1)+"\n\n\n");
        this.acionarGuilhotina();
        cert++;
    }
    
    public Painel(){
        this.cert = 100;
        this.cert_a = 90;
        this.cert_pref = 100;
        this.cert_pref_a = 90;
        this.reg = 100;
        this.reg_a = 99;
        this.tela = new Tela();
        tela.setVisible(true);
    }
    
    
    @Override
    public void AutomaticoCert() {
        if((this.cert_pref>this.cert_pref_a)){
            this.chamaCertPref(String.valueOf(this.cert_pref_a+1));
        }else if(this.cert>this.cert_a){
            this.chamaCert(String.valueOf(this.cert_a+1));
        }else{
            this.RepeteCertidoes();
        }
    }
    
    @Override
    public void CertidaoProximo() {
        if((this.cert>this.cert_a)){
            this.chamaCert(String.valueOf(this.cert_a+1));
        }else{
            this.RepeteCertidoes();
        }
    }
    
    @Override
    public void PreferencialProximo() {
        if((this.cert_pref>this.cert_pref_a)){
            this.chamaCertPref(String.valueOf(this.cert_pref_a+1));
        }else{
            this.RepetePreferencial();
        }
    }
    
    @Override
    public void RegistrosProximo() {
        if(this.reg>this.reg_a){
            this.chamaReg(String.valueOf(this.reg_a+1));
        }
    }
    
    @Override
    public void RepeteRegistros() {
        this.chamaReg(String.valueOf(this.reg_a));
    }
    
    @Override
    public void RepetePreferencial() {
        this.chamaCertPref(String.valueOf(this.cert_pref_a));
    }
    
    @Override
    public int FilaRegistros() {
        return reg-reg_a;
    }
    
    @Override
    public int [] FilaCertidoes() {
        int [] ret = new int[2];
        ret[0]= cert-cert_a;
        ret[1]= cert_pref-cert_pref_a;
        return ret;
    }
    
    @Override
    public void RepeteCertidoes() {
        this.chamaCert(String.valueOf(this.cert_a));
    }
    
    public void chamaCert(String numero){
        TimerCertidao();
        try {
            InputStream certidao = null;
            InputStream senha = null;
            Audio audio = Audio.getInstance();
            certidao = audio.getAudio("Certidões", Language.PORTUGUESE);
            senha = audio.getAudio("Senha " + numero, Language.PORTUGUESE);
            audio.play(certidao);
            audio.play(senha);
            
        } catch (IOException | JavaLayerException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void chamaReg(String numero){
        TimerRegistros();
        try {
            InputStream registros = null;
            InputStream senha = null;
            Audio audio = Audio.getInstance();
            registros = audio.getAudio("Registros, ", Language.PORTUGUESE);
            senha = audio.getAudio("Senha " + numero, Language.PORTUGUESE);
            audio.play(registros);
            audio.play(senha);
        } catch (IOException | JavaLayerException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void chamaCertPref(String numero) {
        TimerCertidao();
        try {
            InputStream certidao = null;
            InputStream senha = null;
            Audio audio = Audio.getInstance();
            certidao = audio.getAudio("Certidões Preferencial, ", Language.PORTUGUESE);
            senha = audio.getAudio("Senha " + numero, Language.PORTUGUESE);
            audio.play(certidao);
            audio.play(senha);
            
        } catch (IOException | JavaLayerException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
      
    public static List<String> retornaImressoras(){  
        try {  
            List<String> listaImpressoras = new ArrayList<>();  
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;    
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);    
            for (PrintService p : ps) {    
                listaImpressoras.add(p.getName());       
            }    
            return listaImpressoras;  
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        return null;  
    }  
      
    public void detectaImpressoras(String impressoraSelecionada) {    
        try {    
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;    
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);    
            for (PrintService p : ps) {    
                if(p.getName()!=null && p.getName().contains(impressoraSelecionada)){    
                    impressora = p;
                }
            }    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }  
      
    public  boolean imprime(String texto) {    
        if (impressora == null) {    
            JOptionPane.showMessageDialog(null, "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.");   
        } else {    
            try {    
                DocPrintJob dpj = impressora.createPrintJob();    
                InputStream stream = new ByteArrayInputStream((texto).getBytes());    
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;    
                Doc doc = new SimpleDoc(stream, flavor, null);
                dpj.print(doc, null);    
                return true;    
            } catch (PrintException e) {    
                e.printStackTrace();    
            }    
        }    
        return false;    
    }   
  
    public void acionarGuilhotina(){  
        imprime(""+(char)27+(char)109);  
    }
    
    public void centraliza(){
        imprime(""+(char)27+(char)97+(char)1);
        
    }
    
    public void imprimeCert(String senha){
        imprime(""+(char)27+(char)33+(char)56);
        imprime(""+(char)27+(char)100+(char)1);
        imprime("CERTIDOES\n\n");
        imprime(""+(char)27+(char)100+(char)0);
        imprime(""+(char)27+(char)33+(char)1); 
        imprime("SENHA:\n\n");
        imprime(""+(char)27+(char)33+(char)56);
        imprime(""+(char)27+(char)100+(char)1);
        imprimeNegrito(senha+"\n\n\n\n");
        imprime(""+(char)27+(char)100+(char)0);
        imprime(""+(char)27+(char)33+(char)1);
    }
    
    public void imprimeCertPref(String senha){
        imprime(""+(char)27+(char)33+(char)56);
        imprime(""+(char)27+(char)100+(char)1);
        imprime("CERTIDOES PREFERENCIAL\n\n");
        imprime(""+(char)27+(char)100+(char)0);
        imprime(""+(char)27+(char)33+(char)1); 
        imprime("SENHA:\n\n");
        imprime(""+(char)27+(char)33+(char)56);
        imprime(""+(char)27+(char)100+(char)1);
        imprimeNegrito(senha+"\n\n\n\n");
        imprime(""+(char)27+(char)100+(char)0);
        imprime(""+(char)27+(char)33+(char)1);
    }
    
    public void imprimeReg(String senha){
        imprime(""+(char)27+(char)33+(char)56);
        imprime(""+(char)27+(char)100+(char)1);
        imprime("REGISTROS\n\n");
        imprime(""+(char)27+(char)100+(char)0);
        imprime(""+(char)27+(char)33+(char)1); 
        imprime("SENHA:\n\n");
        imprime(""+(char)27+(char)33+(char)56);
        imprime(""+(char)27+(char)100+(char)1);
        imprimeNegrito(senha+"\n\n\n\n");
        imprime(""+(char)27+(char)100+(char)0);
        imprime(""+(char)27+(char)33+(char)1);
    }
    
    public void imprimeNegrito(String texto){
        imprime(""+(char)27+(char)69 + texto +(char)27+(char)70);
    }
    
}
