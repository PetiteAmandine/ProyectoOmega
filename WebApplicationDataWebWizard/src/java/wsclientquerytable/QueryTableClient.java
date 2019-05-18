/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsclientquerytable;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:QueryTableResource
 * [queryTable/{table}]<br>
 * USAGE:
 * <pre>
 *        QueryTableClient client = new QueryTableClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Amandine
 */
public class QueryTableClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/WebServicesDataWebWizardRestful/webresources";

    public QueryTableClient(String table) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        String resourcePath = java.text.MessageFormat.format("queryTable/{0}", new Object[]{table});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public void setResourcePath(String table) {
        String resourcePath = java.text.MessageFormat.format("queryTable/{0}", new Object[]{table});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public String doQueryTable() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void putText(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public void close() {
        client.close();
    }
    
}
