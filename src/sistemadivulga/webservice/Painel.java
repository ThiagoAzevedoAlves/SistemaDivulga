/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.webservice;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import java.io.IOException;
import java.io.InputStream;
import javax.jws.WebService;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
 
/**
 *
 * @author Thiago
 */
@WebService(endpointInterface = "sistemadivulga.webservice.server")
public class Painel implements server {
    
    public int cert;
    public int cert_pref;
    public int reg;
    
    public Painel(){
        this.cert = 0;
        this.cert_pref = 0;
        this.reg = 0;
    }
    
    
    @Override
    public void CertidaoProximo() {
        this.chamaCert(String.valueOf(this.cert+1));
        cert++;
    }
    
    public void chamaCert(String numero){
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
    
}
