/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicecreatetable;

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
@Path("createTable/{base}/{table}/{user}")
public class CreateTableResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CreateTableResource
     */
    public CreateTableResource() {
    }

    /**
     * Retrieves representation of an instance of webservicecreatetable.CreateTableResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doCreateTable(@PathParam("base")String base, @PathParam("table")String table, @PathParam("user")String user) {
        if (createTable(base, table, user))
            return "true";
        else
            return "false";
    }

    /**
     * PUT method for updating or creating an instance of CreateTableResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }

    private static boolean createTable(java.lang.String base, java.lang.String tableName, java.lang.String user) {
        webservicecreatetable.CreateTable_Service service = new webservicecreatetable.CreateTable_Service();
        webservicecreatetable.CreateTable port = service.getCreateTablePort();
        return port.createTable(base, tableName, user);
    }
}
