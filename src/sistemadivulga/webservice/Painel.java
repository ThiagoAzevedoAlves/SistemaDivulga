/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.webservice;


import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import sistemadivulga.SistemaDivulga;
import sistemadivulga.database.Database;
import sistemadivulga.frames.Tela;
import sistemadivulga.frames.chamada;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
 
/**
 *
 * @author Thiago
 */
@WebService(endpointInterface = "sistemadivulga.webservice.server")
public class Painel implements server {
    
    public Database d;
    public static PrintService impressora;
    public static Tela tela;
    public chamada c = new chamada();
    public static Timer timer = new Timer(5000, null);
    public static int tempo = 0;
    
    public int [] guicheCert;
    public int [] guicheReg;
    public List histgui;
    public List histsen;
    
    public int cert, cert_a;
    public int cert_pref, cert_pref_a;
    public int reg, reg_a;
    public int last;
    
    public Painel(){
        d = new Database();
        d.connect();
        d.Inicia();
        this.CarregaDados();          
    }
    
    public void CarregaDados(){ //se tiver algum dado salvo no DB com a data atual, ele carrega os dados, senão ele inicia a contagem com os valores padão.
        if(d.getId() > 0){
            this.cert_a = d.getCertAtual();
            this.cert_pref_a = d.getPrefAtual();
            this.reg_a = d.getRegAtual();            
            this.cert = d.getCertFinal();
            this.cert_pref = d.getPrefFinal();
            this.reg = d.getRegFinal();
        }else{            
            d.Inicia();
            this.cert = 100;
            this.cert_a = 100;
            this.cert_pref = 0;
            this.cert_pref_a = 0;
            this.reg = 200;
            this.reg_a = 200;
        }        
        this.last = 0;
        this.tela = new Tela();
        tela.jLNCer.setText(String.valueOf(cert_a));
        tela.jLNCerP.setText(String.valueOf(cert_pref_a));
        tela.jLNReg.setText(String.valueOf(reg_a));
        tela.setVisible(true);
        c.setVisible(false);
        guicheCert = null;
        guicheReg = null;
        histgui = new ArrayList();
        histsen = new ArrayList();
    }
    
    public void TimerCertidao(){ //Aciona a Tela de Chamada de Certidao
        tempo = 0;
        c.setVisible(true);
        c.JlSenha.setText(Integer.toString(cert_a));
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
    
    public void TimerCertidaoGuiche(int guiche) { //Aciona a Tela de Chamada de Certidao por Guiche
        tempo = 0;
        c.setVisible(true);
        c.JlSenha.setText(Integer.toString(cert_a));
        c.jLguiche.setText("GUICHÊ "+ guiche);
        c.jLguiche.setVisible(true);
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
        
    public void TimerPreferencial() { //Aciona a Tela de Chamada de Certidao Preferencial
        tempo = 0;
        c.setVisible(true);
        c.JlSenha.setText(Integer.toString(cert_pref_a));
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
    
    public void TimerPreferencialGuiche(int guiche) {//Aciona a Tela de Chamada de Certidao Preferencial por Guiche
        tempo = 0;
        c.setVisible(true);
        c.jLguiche.setText("GUICHÊ "+ guiche);
        c.jLguiche.setVisible(true);
        c.JlSenha.setText(Integer.toString(cert_pref_a));
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
        
    public void TimerRegistros(){ //Aciona a Tela de Chamada de Registros
        tempo = 0;
        c.setVisible(true);
        c.JlSenha.setText(Integer.toString(reg_a));
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
    
    public void TimerRegistrosGuiche(int guiche){ //Aciona a Tela de Chamada de Registros por Guiche
        tempo = 0;
        c.setVisible(true);
        c.JlSenha.setText(Integer.toString(reg_a));
        c.jLguiche.setText("GUICHÊ "+ guiche);
        c.jLguiche.setVisible(true);
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
    
    public ActionListener Certidoes = (ActionEvent evt) -> { //Listener que ativa e desativa a tela de Chamada
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (tempo==0){
                    tempo = 1;
                    tela.jLNCer.setText(Integer.toString(cert_a));
                }else{
                    timer.stop();
                    c.setVisible(false);
                }
            }
        });
    };
    
    public ActionListener Preferencial = (ActionEvent evt) -> { //Listener que ativa e desativa a tela de Chamada
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (tempo==0){
                    tempo = 1;
                    tela.jLNCerP.setText(Integer.toString(cert_pref_a));
                }else{
                    timer.stop();
                    c.setVisible(false);
                }
            }
        });
    };
    
    public ActionListener Registros = (ActionEvent evt) -> { //Listener que ativa e desativa a tela de Chamada
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (tempo==0){
                    tempo = 1;
                    tela.jLNReg.setText(Integer.toString(reg_a));
                }else{
                    timer.stop();
                    c.setVisible(false);
                }
            }
        });
    };
    
    @Override
    public void ImprimeRegistros() { //Imprime o Ticket de Atendimento para Registros
        this.detectaImpressoras("Generic / Text Only");
        this.centraliza();
        this.imprime("CARTORIO MEZZARI\n");
        this.imprime("Primeiro Cartorio de\n");
        this.imprime("Imoveis de Pelotas\n");
        this.imprime(LocalDateTime.now().toString()+"\n\n\n");
        this.imprimeReg(Integer.toString(reg+1)+"\n\n\n");
        this.acionarGuilhotina();
        reg++;
        d.addReg(d.getId(), reg);
//        d.geraAtendimento(2); //Gera horario de atendimento no DB
    }

    @Override
    public void ImprimePreferencial() { //Imprime o Ticket de Atendimento para Certidoes Preferencial
        this.detectaImpressoras("Generic / Text Only");
        this.centraliza();
        this.imprime("CARTORIO MEZZARI\n");
        this.imprime("Primeiro Cartorio de\n");
        this.imprime("Imoveis de Pelotas\n");
        this.imprime(LocalDateTime.now().toString()+"\n\n\n");
        this.imprimeCertPref(Integer.toString(cert_pref+1)+"\n\n\n");
        this.acionarGuilhotina();
        cert_pref++;
        d.addPref(d.getId(), cert_pref);
//        d.geraAtendimento(0); //Gera horario de atendimento no DB
    }

    @Override
    public void ImprimeCertidoes() { //Imprime o Ticket de Atendimento para Certidoes
        this.detectaImpressoras("Generic / Text Only");
        this.centraliza();
        this.imprime("CARTORIO MEZZARI\n");
        this.imprime("Primeiro Cartorio de\n");
        this.imprime("Imoveis de Pelotas\n");
        this.imprime(LocalDateTime.now().toString()+"\n\n\n");
        this.imprimeCert(Integer.toString(cert+1)+"\n\n\n");
        this.acionarGuilhotina();
        cert++;
        d.addCert(d.getId(), cert);
//        d.geraAtendimento(1); //Gera horario de atendimento no DB
    }
    
    @Override
    public void AutomaticoCert(){
        if((this.cert_pref>this.cert_pref_a)){ //se tiver preferencial em espera
            if (last==0){ //se o ultimo chamado foi certidao normal
                cert_pref_a++;
                this.chamaCertPref(); //chama preferencial
                last=1;
                d.atualizaPref(d.getId(), cert_pref_a);
                //d.geraChamado(0);
                tela.jLNCerP.setText(String.valueOf(cert_pref_a));
            }else if (last==1){ //se o ultimo chamado foi certidao preferencial 
                if((this.cert>this.cert_a)){ //se tem certidao em espera
                    cert_a++;
                    this.chamaCert(); //chama certidao
                    last = 0;
                    d.atualizaCert(d.getId(), cert_a);
                    //d.geraChamado(1);
                    tela.jLNCer.setText(String.valueOf(cert_a));
                }
            }
        }else if((this.cert>this.cert_a)){
            cert_a++;
            this.chamaCert();
            last = 0;
            d.atualizaCert(d.getId(), cert_a);
            //d.geraChamado(1);
            tela.jLNCer.setText(String.valueOf(cert_a));
        }
    }
    
    @Override
    public void CertidaoProximo() {
        if((this.cert>this.cert_a)){
            cert_a++;
            this.chamaCert();
            last = 0;
            d.atualizaCert(d.getId(), cert_a);
            //d.geraChamado(1);
            
            tela.jLNCer.setText(String.valueOf(cert_a));
        }
    }
    
    @Override
    public void CertidaoProximo2(int guiche) {
        if((this.cert>this.cert_a)){
            cert_a++;
            histgui.add(guiche);
            histsen.add(cert_a);
            this.chamaCertGuiche(guiche);
            last = 0;
            d.atualizaCert(d.getId(), cert_a);
            //d.geraChamado(1);
            tela.jLNCer.setText(String.valueOf(cert_a));
        }
    }
    
    @Override
    public void PreferencialProximo() {
        if((this.cert_pref>this.cert_pref_a)){
            cert_pref_a++;
            this.chamaCertPref();
            last = 1;
            d.atualizaPref(d.getId(), cert_pref_a);
            
            //d.geraChamado(0);
            tela.jLNCerP.setText(String.valueOf(cert_pref_a));
        }
    }
    
    @Override
    public void PreferencialProximo2(int guiche) {
        if((this.cert_pref>this.cert_pref_a)){
            cert_pref_a++;
            histgui.add(guiche);
            histsen.add(cert_pref_a);
            this.chamaCertPrefGuiche(guiche);
            last = 1;
            d.atualizaPref(d.getId(), cert_pref_a);
            //d.geraChamado(0);
            tela.jLNCerP.setText(String.valueOf(cert_pref_a));
        }
    }
    
    @Override
    public void RegistrosProximo() {
        if(this.reg>this.reg_a){
            reg_a++;
            this.chamaReg();
            d.atualizaReg(d.getId(), reg_a);
            //d.geraChamado(2);
            tela.jLNReg.setText(String.valueOf(reg_a));
        }
    }
    
    @Override
    public void RegistrosProximo2(int guiche) {
        if(this.reg>this.reg_a){
            reg_a++;
            histgui.add(guiche);
            histsen.add(reg_a);
            this.chamaRegGuiche(guiche);
            d.atualizaReg(d.getId(), reg_a);
            //d.geraChamado(2);
            
            tela.jLNReg.setText(String.valueOf(reg_a));
        }
    }
    
    @Override
    public void RepeteRegistros() {
        this.chamaReg();
    }
    
    @Override
    public void RepeteCertidoes() {
        this.chamaCert();
    }
    
    @Override
    public void RepetePreferencial() {
        this.chamaCertPref();
        
    }
    
    @Override
    public int FilaRegistros() {
        return reg-reg_a;
    }
    
    @Override
    public int TotalRegistros() {
        return reg_a;
    }
    
    @Override
    public int [] FilaCertidoes() {
        int [] ret = new int[2];
        ret[0]= cert-cert_a;
        ret[1]= cert_pref-cert_pref_a;
        return ret;
    }
    
    @Override
    public int [] TotalCertidoes() {
        int [] ret = new int[2];
        ret[0]= cert_a;
        ret[1]= cert_pref_a;
        return ret;
    }
    
    public void chamaCert(){
        TimerCertidao();
        try{
            this.ContaSenha(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void chamaCertGuiche(int guiche){
        TimerCertidaoGuiche(guiche);
        try{
            this.ContaSenha(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void chamaReg(){
        TimerRegistros();
        try{
            this.ContaSenha(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void chamaRegGuiche(int guiche){
        TimerRegistrosGuiche(guiche);
        try{
            this.ContaSenha(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void chamaCertPref() {
        TimerPreferencial();
        try{
            this.ContaSenha(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    private void chamaCertPrefGuiche(int guiche){
        TimerPreferencialGuiche(guiche);
        try{
            this.ContaSenha(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
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
        
    public void rodaAudio(String audio){
        
        boolean found = new NativeDiscovery().discover();
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.setBounds(0, 0, 800,500);
        tela.jInternalFrame1.setVisible(false);
        mediaPlayerComponent.setVisible(false);
        tela.jInternalFrame1.getContentPane().add(mediaPlayerComponent);
        mediaPlayerComponent.getMediaPlayer().setVolume(150);
        mediaPlayerComponent.getMediaPlayer().playMedia(audio);
        tela.jInternalFrame1.setVisible(true);
    }
    
    @Override
    public void IniciaGuiche(int numero, int tipo){
        if (tipo == 0){ //se for guiche de certidoes
            if(guicheCert == null){
                guicheCert = new int[5];
                guicheCert[0] = numero;
                guicheCert[1] = 0;
                guicheCert[2] = 0;
                guicheCert[3] = 0;
                guicheCert[4] = 0;
            }else{
                for(int i = 0; i<5;i++){
                    if(guicheCert[i]== 0){
                        guicheCert[i]= numero;
                        JOptionPane.showMessageDialog(null, "[ "+ guicheCert[0]+" , "+ guicheCert[1]+" , "+ guicheCert[2]+" , "+guicheCert[3]+" , "+ guicheCert[4]+" ]");
                        break;
                    }else{
                        if(guicheCert[i]== numero){
                            JOptionPane.showMessageDialog(null, "Já existe um Giche com este número.");
                            break;
                        }                        
                    }
                }
            }
        }else{ //caso seja um guiche de registros
            if(guicheReg == null){
                guicheReg = new int[2];
                guicheReg[0] = numero;
                guicheReg[1] = 0;
            }else{
                for(int i = 0; i<2;i++){
                    if(guicheReg[i]== 0){
                        guicheReg[i]= numero;
                        JOptionPane.showMessageDialog(null, "[ "+ guicheReg[0]+" , "+ guicheReg[1]+" ]");
                        break;
                    }else{
                        if(guicheReg[i]== numero){
                            JOptionPane.showMessageDialog(null, "Já existe um Giche com este número.");
                            break;
                        }
                        
                    }
                }
            }
        }
    }
    
    /**
     * 
     * @param tipo 0 if preferencial; 1 if certidão; 2 if registros. 
     */
    public void ContaSenha(int tipo) throws InterruptedException {
        
        //inicializa as variaveis --------------//
        String senha = null;
        int digito1, digito2, digito3;
        digito1 = tipo;
        digito2 = 0;
        digito3 = 0;
        //-----------------------------------//
        
        //Roda o Sinal de Alerta -------------//
        this.rodaAudio("dong.mp3");
        //------------------------------------//
        
        //Um segundo após o apito Avisa se é Certidao, Registros ou Preferencial//
        Thread.sleep(1000);
        if (tipo==1){ //se for certidao
            senha = String.valueOf(cert_a); //converte a senha para string
            this.rodaAudio("soar/palavras/certidoes.mp3");
        }else if (tipo == 2){ //se for registros
            senha = String.valueOf(reg_a); //converte a senha para string
            this.rodaAudio("soar/palavras/registros.mp3");
        }else if (tipo == 0){ //se for preferencial
            senha = String.valueOf(cert_pref_a); //converte a senha para string
            this.rodaAudio("soar/palavras/preferencial.mp3");
        }        
        //----------------------------------------------------------------------//
        
        //Monta os Inteiros responsaveis pelo tratamento da Senha ------------------------------------------------------//
        if(senha.length() < 3){ //se a senha tiver menos de 3 digitos
            if (senha.length() < 2){ //caso tenha apenas 1 digito
                digito2  = 0; 
                digito3 = Integer.valueOf(senha);
                //exemplo(digito3 = 1) = 01
            }else{ //senao tem 2 digitos
                digito2 = Integer.valueOf(senha.substring(0,1)); //o digito2 recebe o primeiro digito da senha
                digito3 = Integer.valueOf(senha.substring(1,2)); //o digito3 recebe o ultimo digito da senha
                //exemplo(digito2 = 1)(digito3 = 1) = 11
            }
        }else{ //senao tem 3 digitos
            //digito1 = Integer.valueOf(senha.substring(0,1)); //nao é necessário por causa da inicialização (digito1 = tipo)
            digito2 = Integer.valueOf(senha.substring(1,2));
            digito3 = Integer.valueOf(senha.substring(2,3));
        }
        //-------------------------------------------------------------------------------------------------------------//
        
        //converte os valores da senha para String ---/
        String sdigito1 = String.valueOf(digito1);
        String sdigito2 = String.valueOf(digito2);
        String sdigito3 = String.valueOf(digito3);
        String sdigito2_3 = sdigito2.concat(sdigito3);
        //--------------------------------------------/
        
        //Após um segundo Chama a Respectiva Senha ----------------------------------------//
        Thread.sleep(1000);
        if(digito1 == 1 || digito1 == 2){ //se a senha começar em 100 ou 200
            this.rodaAudio("soar/numeros/" + senha + ".mp3");
        }else if(digito1 == 0 ){ //se for menos de 100
            if(digito2 == 0 ){ //se tiver 1 digito
                this.rodaAudio("soar/numeros/" + sdigito3 + ".mp3");
            }else{ //se tiver 2 digitos
                this.rodaAudio("soar/numeros/" + sdigito2_3 + ".mp3");
            }
        }
        //----------------------------------------------------------------------------------//
    }
}

//CONTAGEM DE THREAD.SLEEP() ANINHADOS = 2

//DIMINUIR PARA 2