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
import java.util.ArrayList;
import javax.jws.WebService;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
 
/**
 *
 * @author Thiago
 */
@WebService(endpointInterface = "sistemadivulga.webservice.server")
public class Painel implements server {
    
    public int cert, cert_a;
    public int cert_pref, cert_pref_a;
    public int reg, reg_a;
    
    public Painel(){
        this.cert = 10;
        this.cert_a = 0;
        this.cert_pref = 10;
        this.cert_pref_a = 0;
        this.reg = 10;
        this.reg_a = 0;
    }
    
    
    @Override
    public void AutomaticoCert() {
        if((this.cert_pref>this.cert_pref_a)){
            this.chamaCertPref(String.valueOf(this.cert_pref_a+1));
            cert_pref_a++;
        }else if(this.cert>this.cert_a){
            this.chamaCert(String.valueOf(this.cert_a+1));
            cert_a++;
        }else{
            this.RepeteCertidoes();
        }
    }
    
    @Override
    public void CertidaoProximo() {
        if((this.cert>this.cert_a)){
            this.chamaCert(String.valueOf(this.cert_a+1));
            cert_a++;
        }else{
            this.RepeteCertidoes();
        }
    }
    
    @Override
    public void PreferencialProximo() {
        if((this.cert_pref>this.cert_pref_a)){
            this.chamaCertPref(String.valueOf(this.cert_pref_a+1));
            cert_pref_a++;
        }else{
            this.RepetePreferencial();
        }
    }
    
    @Override
    public void RegistrosProximo() {
        if(this.reg>this.reg_a){
            this.chamaReg(String.valueOf(this.reg_a+1));
            reg_a++;
        }else{
            this.RepeteRegistros();
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
}
