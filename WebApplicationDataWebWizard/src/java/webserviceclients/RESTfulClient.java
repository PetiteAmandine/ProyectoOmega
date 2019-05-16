/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceclients;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:MyPathResource
 * [MyPath/{user}/{pass}]<br>
 * USAGE:
 * <pre>
 *        RESTfulClient client = new RESTfulClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Amandine
 */
public class RESTfulClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/WebServiceDataWebWizardRestful/webresources";

    public RESTfulClient(String user, String pass) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        String resourcePath = java.text.MessageFormat.format("MyPath/{0}/{1}", new Object[]{user, pass});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public void setResourcePath(String user, String pass) {
        String resourcePath = java.text.MessageFormat.format("MyPath/{0}/{1}", new Object[]{user, pass});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public String doLogin() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public void putHtml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.TEXT_HTML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_HTML));
    }

    public void close() {
        client.close();
    }
    
}
