/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.webserviceupdater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Amandine
 */
@WebService(serviceName = "Update")
@Stateless()
public class Update {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "update")
    public boolean update(@WebParam(name = "base") String base) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DataWebWizard;create=true;", "root", "root");
            Statement query = con.createStatement();
            int res = query.executeUpdate(base);
            con.close();
            if (res > 0)
                return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
