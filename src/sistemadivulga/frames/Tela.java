/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.frames;

import bemajava.*;
import com.gtranslate.Audio;
import com.gtranslate.Language;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javazoom.jl.decoder.JavaLayerException;
import sistemadivulga.model.Feed;
import sistemadivulga.model.FeedMessage;
import sistemadivulga.read.RSSFeedParser;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import java.io.ByteArrayInputStream;  
import java.io.InputStream;  
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;  
import java.util.List;
import javax.imageio.ImageIO;
import javax.print.Doc;  
import javax.print.DocFlavor;  
import javax.print.DocPrintJob;  
import javax.print.PrintException;  
import javax.print.PrintService;  
import javax.print.PrintServiceLookup;  
import javax.print.SimpleDoc;  
import javax.swing.JLabel;
import javax.swing.JOptionPane;  
import javax.swing.Timer;
import sistemadivulga.read.NewParser;
import sistemadivulga.read.NewParser.Item;
import sistemadivulga.read.NewParser.RssFeed;

/**
 *
 * @author Thiago
 */
public class Tela extends javax.swing.JFrame {
    static int contador =0;
    static int delay = 2000; //milliseconds
    static int totalCount = 0;
    static int count = 0;
    static String [] titulo = new String[100];
    static String [] desc = new String[100];
    static JLabel [] imagem = new JLabel[100];
    
    /**
     * Creates new form SistemaDivulga
     */
    
    private static PrintService impressora;  
    
    @SuppressWarnings("WaitWhileNotSynced")
    public Tela() {
        initComponents();
        this.setSize(1380, 768);
        
        String [] previsao = this.parsePrevisao();
        
        this.parseExame();
        jLabel1.setText(previsao[0]);
        jLabel2.setText(previsao[1]);
        jLabel3.setText(previsao[2]);
        
        //this.rodaVideo("Dengue_Sintomas.swf");
        //this.chamaCert("Senha 1");
        
        //this.detectaImpressoras("Generic / Text Only");  
        //this.retornaImressoras();  
        //this.imprime("TESTE ");  
        //esconde os titulos e noticias 2 ------------------------------------//
        this.jLTitulo1.setVisible(false);
        this.jLNoticia1.setVisible(false);
        //--------------------------------------------------------------------//
        
        //fundo------------------------------------------------------------------------------------//
        BufferedImage resizedImg = new BufferedImage(1390, 768, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/painel1.png")).getImage(), 0, 0, 1390, 768, null);
        g.dispose();
        
        jLFundo.setIcon(new javax.swing.ImageIcon(resizedImg));
        jLFundo.setBackground(Color.black);
        //-----------------------------------------------------------------------------------------//
        
        //previsao------------------------------------------------------------------------------------//
        resizedImg = new BufferedImage(jPPrev.getWidth(), jPPrev.getHeight(), BufferedImage.TYPE_INT_ARGB);
        g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/PREVISAO.png")).getImage(), 0, 0, jPPrev.getWidth(), jPPrev.getHeight(), null);
        g.dispose();
        
        jLFundoPrev.setIcon(new javax.swing.ImageIcon(resizedImg));
        //-----------------------------------------------------------------------------------------//
        
        //certidao preferencial------------------------------------------------------------------------------------//
        resizedImg = new BufferedImage(400, 150, BufferedImage.TYPE_INT_ARGB);
        g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/CERTIDOESPREF.png")).getImage(), 0, 0, 400, 150, null);
        g.dispose();
        
        jLCerP.setIcon(new javax.swing.ImageIcon(resizedImg));
        //-----------------------------------------------------------------------------------------//
        
        //certidao ------------------------------------------------------------------------------------//
        resizedImg = new BufferedImage(400, 150, BufferedImage.TYPE_INT_ARGB);
        g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/CERTIDOES.png")).getImage(), 0, 0, 400, 150, null);
        g.dispose();
        
        jLCer.setIcon(new javax.swing.ImageIcon(resizedImg));
        //-----------------------------------------------------------------------------------------//
        
        //certidao preferencial------------------------------------------------------------------------------------//
        resizedImg = new BufferedImage(400, 150, BufferedImage.TYPE_INT_ARGB);
        g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/REGISTROS.png")).getImage(), 0, 0, 400, 150, null);
        g.dispose();
        
        jLReg.setIcon(new javax.swing.ImageIcon(resizedImg));
        //-----------------------------------------------------------------------------------------//
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPPrev = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLFundoPrev = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLTitulo = new javax.swing.JLabel();
        jLNoticia = new javax.swing.JLabel();
        jLData = new javax.swing.JLabel();
        jLFonte = new javax.swing.JLabel();
        jLTitulo1 = new javax.swing.JLabel();
        jLNoticia1 = new javax.swing.JLabel();
        jLImagem = new javax.swing.JLabel();
        jLReg = new javax.swing.JLabel();
        jLCer = new javax.swing.JLabel();
        jLCerP = new javax.swing.JLabel();
        jLFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPPrev.setBackground(new java.awt.Color(255, 255, 255));
        jPPrev.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, null, null));
        jPPrev.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Square721 BT", 0, 11)); // NOI18N
        jLabel1.setText("jLabel1");
        jPPrev.add(jLabel1);
        jLabel1.setBounds(0, 0, 72, 71);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("jLabel2");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPPrev.add(jLabel2);
        jLabel2.setBounds(80, 0, 251, 33);

        jLabel3.setFont(new java.awt.Font("Square721 BT", 0, 11)); // NOI18N
        jLabel3.setText("jLabel3");
        jPPrev.add(jLabel3);
        jLabel3.setBounds(80, 40, 251, 14);

        jLFundoPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/previsao.png"))); // NOI18N
        jPPrev.add(jLFundoPrev);
        jLFundoPrev.setBounds(0, 0, 370, 70);

        getContentPane().add(jPPrev);
        jPPrev.setBounds(10, 180, 370, 70);

        jInternalFrame1.setPreferredSize(new java.awt.Dimension(600, 600));
        jInternalFrame1.setVisible(true);

        jLTitulo.setFont(new java.awt.Font("Square721 BT", 0, 30)); // NOI18N
        jLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLTitulo.setText("<titulo>");

        jLNoticia.setFont(new java.awt.Font("Square721 BT", 2, 18)); // NOI18N
        jLNoticia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLNoticia.setText("<noticia>");

        jLData.setFont(new java.awt.Font("Square721 BT", 2, 14)); // NOI18N
        jLData.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLData.setText("<data>");

        jLFonte.setFont(new java.awt.Font("Square721 BT", 2, 14)); // NOI18N
        jLFonte.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLFonte.setText("<fonte>");

        jLTitulo1.setFont(new java.awt.Font("Square721 BT", 0, 30)); // NOI18N
        jLTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLTitulo1.setText("<titulo>");

        jLNoticia1.setFont(new java.awt.Font("Square721 BT", 2, 18)); // NOI18N
        jLNoticia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLNoticia1.setText("<noticia>");

        jLImagem.setFont(new java.awt.Font("Square721 BT", 2, 18)); // NOI18N
        jLImagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLImagem.setText("<imagem>");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLTitulo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addComponent(jLNoticia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLNoticia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLFonte, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jLData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(275, Short.MAX_VALUE)))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLTitulo1)
                .addGap(18, 18, 18)
                .addComponent(jLNoticia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLNoticia1)
                .addGap(37, 37, 37)
                .addComponent(jLFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLData, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLTitulo)
                    .addContainerGap(423, Short.MAX_VALUE)))
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                    .addContainerGap(190, Short.MAX_VALUE)
                    .addComponent(jLImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        getContentPane().add(jInternalFrame1);
        jInternalFrame1.setBounds(10, 260, 800, 500);
        getContentPane().add(jLReg);
        jLReg.setBounds(880, 590, 400, 150);
        getContentPane().add(jLCer);
        jLCer.setBounds(880, 270, 400, 150);
        getContentPane().add(jLCerP);
        jLCerP.setBounds(880, 430, 400, 150);
        getContentPane().add(jLFundo);
        jLFundo.setBounds(0, 0, 1390, 768);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String[] parsePrevisao(){
        String [] ret = new String [3];
        RSSFeedParser parser = new RSSFeedParser("http://servicos.cptec.inpe.br/RSS/cidade/3914/previsao.xml");
        Feed feed = parser.readFeed();
        String previsao = "";
            
        for (FeedMessage message : feed.getMessages()) {
            previsao = previsao + "\n" +message.getDescription()+ "\n";
        }
        
        String imagem = "<html>";
        String [] aux = previsao.split(">", 2); //String auxiliar 0 = imagem, 1 = resto;
            
        if(aux != null){
            imagem = imagem+aux[0];
        }
        imagem = imagem+"</html>";
        String [] aux2 = aux[1].split("P");
        String [] desc = aux2[0].split("-");
        ret[0] = imagem;
        ret[1] = desc[0];
        ret[2] = desc[1];
        
        return ret;
    }
    
    public void parseG1(){
        
        RSSFeedParser parser = new RSSFeedParser("http://g1.globo.com/dynamo/brasil/rss2.xml");
        Feed feed = parser.readFeed();
        String noticia = "";
        int n = 0;
        JLabel not = new JLabel();
        this.jLFonte.setText("g1.globo.com");
        this.jLData.setText("Atualizado em 29/06/2015");
        for (FeedMessage message : feed.getMessages()) {
            titulo[contador]= new String(message.getTitle());
            desc[contador]= new String(message.getDescription());
            contador++;
        }
        
        //método que atualiza as noticias ------------------------------------------------//
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String [] aux = new String[2];
                char espaco = 0;
                int i = titulo[totalCount].length()/2;
                if(i > 10){
                    while(espaco != ' '){
                        espaco = titulo[totalCount].charAt(i);
                        i++;
                    }
                    aux[0] = titulo[totalCount].substring(0,i);
                    aux[1] = titulo[totalCount].substring(i);
                }else{
                    aux[0] = titulo[totalCount];
                    aux[1] = null;
                }
                espaco = 'a';
                
                jLTitulo.setText(aux[0]);
                if (aux[1] != null){
                    jLTitulo1.setVisible(true);
                    jLTitulo1.setText(aux[1]);
                }else{
                    jLTitulo1.setVisible(false);
                }
                i = desc[totalCount].length()/2;
                if(i > 15){
                    while(espaco != ' '){
                        espaco = desc[totalCount].charAt(i);
                        i++;
                    }
                    aux[0] = desc[totalCount].substring(0,i);
                    aux[1] = desc[totalCount].substring(i);
                }else{
                    aux[0] = desc[totalCount];
                    aux[1] = null;
                }
                if(aux[0].length()>=1){
                    if(aux[0].charAt(0)=='<') aux[0] = "";
                }
                jLNoticia.setText(aux[0]);
                if (aux[1] != null){
                    jLNoticia1.setVisible(true);
                    jLNoticia1.setText(aux[1]);
                }else{
                    jLNoticia1.setVisible(false);
                }
                
                totalCount++;
            }
        };
        //-----------------------------------------------------------------------------------------------//
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();
        
    }
        
    public void parseExame(){
      if(totalCount < 100){
          
        NewParser p = new NewParser("http://feeds.feedburner.com/EXAME-Noticias?format=xml");
        p.parse(); 
        RssFeed feed = p.getFeed();
        
        String noticia = "";
        int n = 0;
        JLabel not = new JLabel();
        this.jLFonte.setText("exame.abril.com.br");
        this.jLData.setText(feed.items.get(0).pubDate);
        for (Item lista : feed.items) {
            Image image = null;
            String [] aux = lista.description.split("src='", 2);
            String [] aux2 = aux[1].split("'></p>", 2);
            String [] daux = aux[0].split("<p>", 2);
            String [] daux2 = daux[1].split("</p>", 2);
                
            try {
                                
                titulo[contador]= new String(lista.title);
                desc[contador] = new String(daux2[0]);
                URL url = new URL(aux2[0]);
                image = ImageIO.read(url);
                //redimensiona imagem-----------------------------------------------------------------------//
                BufferedImage resizedImg = new BufferedImage(500, 270, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = resizedImg.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(new ImageIcon(image).getImage(), 0, 0, 500, 270, null);
                g.dispose();
        
                imagem[contador] = new JLabel(new ImageIcon(resizedImg));
                //-----------------------------------------------------------------------------------------//
                
                
                contador++;
            } catch (MalformedURLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        
        //método que atualiza as noticias ------------------------------------------------//
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String [] aux = new String[2];
                char espaco = 0;
                int i = titulo[totalCount].length()/2;
                if(i > 10){
                    while(espaco != ' '){
                        espaco = titulo[totalCount].charAt(i);
                        i++;
                    }
                    aux[0] = titulo[totalCount].substring(0,i);
                    aux[1] = titulo[totalCount].substring(i);
                }else{
                    aux[0] = titulo[totalCount];
                    aux[1] = null;
                }
                espaco = 'a';
                
                jLTitulo.setText(aux[0]);
                if (aux[1] != null){
                    jLTitulo1.setVisible(true);
                    jLTitulo1.setText(aux[1]);
                }else{
                    jLTitulo1.setVisible(false);
                }
                i = desc[totalCount].length()/2;
                if(i > 15){
                    while(espaco != ' '){
                        espaco = desc[totalCount].charAt(i);
                        i++;
                    }
                    aux[0] = desc[totalCount].substring(0,i);
                    aux[1] = desc[totalCount].substring(i);
                }else{
                    aux[0] = desc[totalCount];
                    aux[1] = null;
                }
                
                jLNoticia.setText(aux[0]);
                if (aux[1] != null){
                    jLNoticia1.setVisible(true);
                    jLNoticia1.setText(aux[1]);
                }else{
                    jLNoticia1.setVisible(false);
                }
                jLImagem.setText(null);
                jLImagem.setIcon(imagem[totalCount].getIcon());
                
                
                totalCount++;
                
                
            }
        };
        //-----------------------------------------------------------------------------------------------//
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();
      }else{
        totalCount = 0;        
      }  
    }
    
    public void rodaVideo(String nome){
        boolean found = new NativeDiscovery().discover();
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.setBounds(0, 0, 800,600);
        this.jInternalFrame1.getContentPane().add(mediaPlayerComponent);
            
        mediaPlayerComponent.getMediaPlayer().playMedia(nome);
        mediaPlayerComponent.getMediaPlayer().play();
    }
    
    public void chamaCert(String numero){
        
        try {
            InputStream certidao = null;
            InputStream senha = null;
            Audio audio = Audio.getInstance();
            certidao = audio.getAudio("Certidões", Language.PORTUGUESE);
            senha = audio.getAudio(numero, Language.PORTUGUESE);
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
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLCer;
    private javax.swing.JLabel jLCerP;
    private javax.swing.JLabel jLData;
    private javax.swing.JLabel jLFonte;
    private javax.swing.JLabel jLFundo;
    private javax.swing.JLabel jLFundoPrev;
    private javax.swing.JLabel jLImagem;
    private javax.swing.JLabel jLNoticia;
    private javax.swing.JLabel jLNoticia1;
    private javax.swing.JLabel jLReg;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JLabel jLTitulo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPPrev;
    // End of variables declaration//GEN-END:variables
}
