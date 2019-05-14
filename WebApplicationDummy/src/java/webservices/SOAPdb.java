/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Eduardo
 */
@WebService(serviceName = "SOAPdb")
@Stateless()
public class SOAPdb {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "create")
    public String create(@WebParam(name = "cant") int cant, @WebParam(name = "llave") int llave, @WebParam(name = "nombre") String nombre, @WebParam(name = "nombres") String[] nombres, @WebParam(name = "tipos") String[] tipos) {
        StringBuilder resp=new StringBuilder("CREATE TABLE " + nombre + " (");
        for(int i=0; i<cant; i++){
            resp.append(nombres[i]);
            resp.append(" ");
            resp.append(tipos[i]);
            if(i==llave)
                resp.append(" PRIMARY KEY");
            resp.append(",\n");
            if(i==cant-1)
                resp.append(");");
        }
        return resp.toString();
    }

}
