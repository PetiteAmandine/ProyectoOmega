/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsclientcreatetable;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CreateTableResource
 * [createTable/{base}/{table}/{user}]<br>
 * USAGE:
 * <pre>
 *        CreateTableClient client = new CreateTableClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Amandine
 */
public class CreateTableClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/WebServicesDataWebWizardRestful/webresources";

    public CreateTableClient(String base, String table, String user) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        String resourcePath = java.text.MessageFormat.format("createTable/{0}/{1}/{2}", new Object[]{base, table, user});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public void setResourcePath(String base, String table, String user) {
        String resourcePath = java.text.MessageFormat.format("createTable/{0}/{1}/{2}", new Object[]{base, table, user});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public void putText(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public String doCreateTable() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
