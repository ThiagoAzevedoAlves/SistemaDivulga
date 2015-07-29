/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadivulga.webservice;


import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
 
@WebService
@SOAPBinding(style = Style.RPC)
public interface server {
    
    @WebMethod void CertidaoProximo();
    @WebMethod void PreferencialProximo();
    @WebMethod void RegistrosProximo();
    @WebMethod void AutomaticoCert();
    @WebMethod void RepeteRegistros();
    @WebMethod void RepeteCertidoes();
    @WebMethod void RepetePreferencial();
    
    @WebMethod void CertidaoProximo2(int guiche);
    @WebMethod void PreferencialProximo2(int guiche);
    @WebMethod void RegistrosProximo2(int guiche);
    
    @WebMethod int FilaRegistros();
    @WebMethod int TotalRegistros();
    @WebMethod int [] FilaCertidoes();
    @WebMethod int [] TotalCertidoes();
    @WebMethod void ImprimePreferencial();
    @WebMethod void ImprimeCertidoes();
    @WebMethod void ImprimeRegistros();
    @WebMethod void IniciaGuiche(int numero, int tipo);
    
}

