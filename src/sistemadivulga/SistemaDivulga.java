package sistemadivulga;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
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
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            public void run() {
                Painel p = new Painel();
                Endpoint.publish("http://0.0.0.0:9876/webservice", p);
            }
        });
    }
}
