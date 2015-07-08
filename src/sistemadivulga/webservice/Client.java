/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Thiago
 */
public class Client {
        URL url;
        QName qname;
        Service ws;
        server s;

        public Client() {
            try {
                this.url = new URL("http://0.0.0.0:9876/webservice.sistemadivulga?wsdl");
                this.qname = new QName("http://webservice.sistemadivulga/","PainelService");
                this.ws = Service.create(url, qname);
                s = ws.getPort(server.class);
                s.RegistrosProximo();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
