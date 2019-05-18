/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceregister;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Amandine
 */
@Path("register/{user}/{pass}")
public class RegisterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
    }

    /**
     * Retrieves representation of an instance of webserviceregister.RegisterResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RegisterResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public String doRegister(@PathParam("user")String user, @PathParam("pass")String pass) {
        if (register(user, pass))
            return "true";
        return "false";
    }

    private static boolean register(java.lang.String user, java.lang.String pass) {
        webserviceregister.Register_Service service = new webserviceregister.Register_Service();
        webserviceregister.Register port = service.getRegisterPort();
        return port.register(user, pass);
    }
    
}
