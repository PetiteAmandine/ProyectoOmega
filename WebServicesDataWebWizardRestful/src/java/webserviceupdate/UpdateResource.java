/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceupdate;

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
@Path("update/{base}")
public class UpdateResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UpdateResource
     */
    public UpdateResource() {
    }

    /**
     * Retrieves representation of an instance of webserviceupdate.UpdateResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doUpdate(@PathParam("base")String base) {
        if (update(base))
            return "true";
        return "false";
    }

    /**
     * PUT method for updating or creating an instance of UpdateResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }

    private static boolean update(java.lang.String base) {
        wsclientupdate.Update_Service service = new wsclientupdate.Update_Service();
        wsclientupdate.Update port = service.getUpdatePort();
        return port.update(base);
    }
}
