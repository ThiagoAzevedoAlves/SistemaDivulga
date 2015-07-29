package sistemadivulga;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.xml.ws.Endpoint;
import sistemadivulga.webservice.Painel;

/**
 *
 * @author Thiago
 */
public class SistemaDivulga {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    Painel p = new Painel();
                    Endpoint.publish("http://192.168.0.100:9876/webservice", p);
            }
        });
    }
    
}
