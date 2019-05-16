/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Amandine
 */
@Path("MyPath/{user}/{pass}")
public class MyPathResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MyPathResource
     */
    public MyPathResource() {
    }

    /**
     * PUT method for updating or creating an instance of MyPathResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String doLogin(@PathParam("user")String user, @PathParam("pass")String pass) {
        if (login(user, pass))
            return "true";
        return "false";
    }

    private static boolean login(java.lang.String user, java.lang.String pass) {
        webserviceclients.DBSoap_Service service = new webserviceclients.DBSoap_Service();
        webserviceclients.DBSoap port = service.getDBSoapPort();
        return port.login(user, pass);
    }
    
}
