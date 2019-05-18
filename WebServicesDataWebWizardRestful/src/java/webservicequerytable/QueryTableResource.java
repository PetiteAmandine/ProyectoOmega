/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservicequerytable;

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
@Path("queryTable/{table}")
public class QueryTableResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QueryTableResource
     */
    public QueryTableResource() {
    }

    /**
     * Retrieves representation of an instance of webservicequerytable.QueryTableResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doQueryTable(@PathParam("table")String table) {
        return queryTable(table);
    }

    /**
     * PUT method for updating or creating an instance of QueryTableResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }

    private static String queryTable(java.lang.String tableName) {
        webservicequerytable.TableQuery_Service service = new webservicequerytable.TableQuery_Service();
        webservicequerytable.TableQuery port = service.getTableQueryPort();
        return port.queryTable(tableName);
    }
}
