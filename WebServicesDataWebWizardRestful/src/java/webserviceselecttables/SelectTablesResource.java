/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceselecttables;

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
@Path("selectTables/{user}")
public class SelectTablesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SelectTablesResource
     */
    public SelectTablesResource() {
    }

    /**
     * Retrieves representation of an instance of webserviceselecttables.SelectTablesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doSelectTables(@PathParam("user")String user) {
        return selectTables(user);
    }

    /**
     * PUT method for updating or creating an instance of SelectTablesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }

    private static String selectTables(java.lang.String user) {
        webserviceselecttables.SelectTables_Service service = new webserviceselecttables.SelectTables_Service();
        webserviceselecttables.SelectTables port = service.getSelectTablesPort();
        return port.selectTables(user);
    }
}
