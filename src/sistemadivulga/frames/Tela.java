/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.frames;

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
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;  
import java.util.Calendar;
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
import sistemadivulga.database.Database;
import sistemadivulga.read.NewParser;
import sistemadivulga.read.NewParser.Item;
import sistemadivulga.read.NewParser.RssFeed;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 *
 * @author Thiago
 */
public class Tela extends javax.swing.JFrame {
    static int contador =0;
    static int totalCount = 0;
    static int count = 0;
    static int video = 0;
    static String [] titulo = new String[100];
    static String [] desc = new String[100];
    static JLabel [] imagem = new JLabel[100];
    static Timer timer = new Timer(20000, null);
    static int TipoNoticia = 0;
    private static PrintService impressora;
    
    @SuppressWarnings("WaitWhileNotSynced")
    public Tela() {
        initComponents();
        this.setSize(1380, 768);        
        //previsao----------------------------------------------//
        try{
            String [] previsao = this.parsePrevisao();
                
            jLabel1.setText(previsao[0]);
            jLabel2.setText(previsao[1]);
            jLabel3.setText(previsao[2]);
        }catch(RuntimeException ex){
            jLabel1.setText("");
            jLabel2.setText("ERRO AO OBTER PREVISAO DO TEMPO");
            jLabel3.setText("");
        }
        //-----------------------------------------------------//
        
        //esconde os titulos e noticias 2 ------------------------------------//
        this.jLTitulo1.setVisible(false);
        this.jLNoticia1.setVisible(false);
        this.jLData.setVisible(false);
        this.jLFonte.setVisible(false);
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
        resizedImg = new BufferedImage(400, 180, BufferedImage.TYPE_INT_ARGB);
        g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(new ImageIcon(getClass().getResource("/recursos/CERTIDOESPREF.png")).getImage(), 0, 0, 400, 180, null);
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
        
        //prepara Parser Exame---------------------------------------------------------------//
        
        rodaVideo();
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
        jLNReg = new javax.swing.JLabel();
        jLNCer = new javax.swing.JLabel();
        jLNCerP = new javax.swing.JLabel();
        jLReg = new javax.swing.JLabel();
        jLCer = new javax.swing.JLabel();
        jLCerP = new javax.swing.JLabel();
        jLFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
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
        jPPrev.add(jLFundoPrev);
        jLFundoPrev.setBounds(0, 0, 370, 70);

        getContentPane().add(jPPrev);
        jPPrev.setBounds(10, 180, 370, 70);

        jInternalFrame1.setPreferredSize(new java.awt.Dimension(600, 600));
        jInternalFrame1.setVisible(true);

        jLTitulo.setFont(new java.awt.Font("Square721 BT", 0, 30)); // NOI18N
        jLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLNoticia.setFont(new java.awt.Font("Square721 BT", 2, 18)); // NOI18N
        jLNoticia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLData.setFont(new java.awt.Font("Square721 BT", 2, 14)); // NOI18N
        jLData.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jLFonte.setFont(new java.awt.Font("Square721 BT", 2, 14)); // NOI18N
        jLFonte.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jLTitulo1.setFont(new java.awt.Font("Square721 BT", 0, 30)); // NOI18N
        jLTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLNoticia1.setFont(new java.awt.Font("Square721 BT", 2, 18)); // NOI18N
        jLNoticia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLImagem.setFont(new java.awt.Font("Square721 BT", 2, 18)); // NOI18N
        jLImagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

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
                .addContainerGap(245, Short.MAX_VALUE))
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLTitulo)
                    .addContainerGap(461, Short.MAX_VALUE)))
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                    .addContainerGap(190, Short.MAX_VALUE)
                    .addComponent(jLImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        getContentPane().add(jInternalFrame1);
        jInternalFrame1.setBounds(10, 260, 800, 500);

        jLNReg.setFont(new java.awt.Font("Square721 BT", 0, 60)); // NOI18N
        jLNReg.setForeground(new java.awt.Color(103, 143, 200));
        jLNReg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLNReg.setText("200");
        getContentPane().add(jLNReg);
        jLNReg.setBounds(990, 650, 180, 70);

        jLNCer.setFont(new java.awt.Font("Square721 BT", 0, 60)); // NOI18N
        jLNCer.setForeground(new java.awt.Color(255, 255, 255));
        jLNCer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLNCer.setText("100");
        getContentPane().add(jLNCer);
        jLNCer.setBounds(994, 300, 180, 70);

        jLNCerP.setFont(new java.awt.Font("Square721 BT", 0, 60)); // NOI18N
        jLNCerP.setForeground(new java.awt.Color(153, 130, 204));
        jLNCerP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLNCerP.setText("0");
        getContentPane().add(jLNCerP);
        jLNCerP.setBounds(990, 500, 180, 70);
        getContentPane().add(jLReg);
        jLReg.setBounds(880, 590, 400, 150);
        getContentPane().add(jLCer);
        jLCer.setBounds(880, 240, 400, 150);
        getContentPane().add(jLCerP);
        jLCerP.setBounds(880, 400, 400, 180);
        getContentPane().add(jLFundo);
        jLFundo.setBounds(0, 0, 1390, 768);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        DateFormat dateFormat = new SimpleDateFormat("HH");
        if(Integer.valueOf(dateFormat.format(date)) >= 17){
            geraMedia();
        }
    }//GEN-LAST:event_formWindowClosing
    
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
    
    public ActionListener Exame = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                    String [] aux = new String[2];
                    char espaco = 0;
                    int i = 0;
                    i = titulo[totalCount].length()/2;
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
                    if((totalCount == 20)){
                        totalCount = 0;
                        timer.removeActionListener(Exame);
                        rodaVideo();
                    }
                }                
        };
    
    public ActionListener G1 = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String [] aux = new String[2];
                char espaco = 0;
                int i = 0;
                i = titulo[totalCount].length()/2;
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
                if(totalCount == 10){
                    timer.stop();
                    rodaVideo();
                    totalCount++;
                }
                }
            };
            
    public void parseG1(){
            RSSFeedParser parser = new RSSFeedParser("http://g1.globo.com/dynamo/brasil/rss2.xml");
            Feed feed = parser.readFeed();
            String noticia = "";
            int n = 0;
            JLabel not = new JLabel();
            this.jLFonte.setText("g1.globo.com");
            Instant i = Instant.now();
            LocalDateTime ldt = LocalDateTime.ofInstant(i, ZoneId.systemDefault());
            this.jLData.setText("Atualizado em " + ldt.getDayOfMonth() + "/" + ldt.getMonth() + "/" + ldt.getYear());
            this.jLImagem.setVisible(false);
            for (FeedMessage message : feed.getMessages()) {
                titulo[contador]= new String(message.getTitle());
                desc[contador]= new String(message.getDescription());
                contador++;
            }
            contador = 0;
    }
        
    public void parseExame(){
        NewParser p = new NewParser("http://feeds.feedburner.com/EXAME-Noticias?format=xml");
        p.parse(); 
        RssFeed feed = p.getFeed();
        
        String noticia = "";
        int n = 0;
        JLabel not = new JLabel();
        this.jLFonte.setText("exame.abril.com.br");
        this.jLData.setText(feed.items.get(0).pubDate);
        this.jLImagem.setVisible(true);
        for (Item lista : feed.items) {
            Image image = null;
            String [] aux = lista.description.split("src='", 2);
            String [] aux2 = null;
            String [] daux = null;
            String [] daux2 = null;
            titulo[contador]= new String(lista.title);
            try{
                aux2 = aux[1].split("'></p>", 2);
                daux = aux[0].split("<p>", 2);
                daux2 = daux[1].split("</p>", 2);
                desc[contador] = new String(daux2[0]);
            }catch(ArrayIndexOutOfBoundsException ex){
                aux = lista.description.split("src=\"", 2);
                aux2 = aux[1].split("'></p>", 2);
                desc[contador] = new String();
            }            
            try{
                URL url = new URL(aux2[0]); 
                image = ImageIO.read(url);
            }catch (IOException ex){
                image = null;
            }
            //redimensiona imagem-----------------------------------------------------------------------//
            BufferedImage resizedImg = new BufferedImage(500, 270, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = resizedImg.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            if (image != null){
                g.drawImage(new ImageIcon(image).getImage(), 0, 0, 500, 270, null);
            }
            g.dispose();
        
            imagem[contador] = new JLabel(new ImageIcon(resizedImg));
            //-----------------------------------------------------------------------------------------//
            contador++;
        }
        contador = 0;        
    }
    
    public void rodaVideo(){
        jLTitulo.setVisible(false);
        jLTitulo1.setVisible(false);
        jLNoticia.setVisible(false);
        jLNoticia1.setVisible(false);
        jLFonte.setVisible(false);
        jLData.setVisible(false);
        jLImagem.setVisible(false);
        boolean found = new NativeDiscovery().discover();
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.setBounds(0, 0, 800,500);
        this.jInternalFrame1.getContentPane().add(mediaPlayerComponent);
        mediaPlayerComponent.getMediaPlayer().setVolume(0);
        if (video==0){
            mediaPlayerComponent.getMediaPlayer().playMedia("amamentacao.mp4");
            mediaPlayerComponent.getMediaPlayer().setVolume(0);
            video++;
        }else if (video==1){
            mediaPlayerComponent.getMediaPlayer().playMedia("Dengue_Sintomas.swf");
            video++;
        }else if (video==2){
            mediaPlayerComponent.getMediaPlayer().playMedia("tabagismo.mp4");
            video = 0;
        }
        
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter(){
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                mediaPlayerComponent.setVisible(false);
                parseExame();
                timer.addActionListener(Exame);
                timer.start();
                jLTitulo.setVisible(true);
                jLTitulo1.setVisible(true);
                jLNoticia.setVisible(true);
                jLNoticia1.setVisible(true);
                jLFonte.setVisible(true);
                jLData.setVisible(true);
                jLImagem.setVisible(true);                
            };
        });
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
    
    public void geraMedia(){
        Database d = new Database();
        d.connect();
        int mcert = d.getCertFinal()-100;
        int mpref = d.getPrefFinal();
        int mreg = d.getRegFinal()-200;
        d.salvaMedia(mcert, mpref, mreg);
        JOptionPane.showMessageDialog(null, "<html><center>Média do Dia Gerada:</center><br>Certidões: " + mcert + "<br>Certidões Preferencial: " + mpref + "<br>Registros: " + mreg + "</html>");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLCer;
    private javax.swing.JLabel jLCerP;
    private javax.swing.JLabel jLData;
    private javax.swing.JLabel jLFonte;
    private javax.swing.JLabel jLFundo;
    private javax.swing.JLabel jLFundoPrev;
    private javax.swing.JLabel jLImagem;
    public static javax.swing.JLabel jLNCer;
    public static javax.swing.JLabel jLNCerP;
    public static javax.swing.JLabel jLNReg;
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
