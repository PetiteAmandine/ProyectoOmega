/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.webservicetablecreator;

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
@WebService(serviceName = "CreateTable")
@Stateless()
public class CreateTable {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createTable")
    public boolean createTable(@WebParam(name = "base") String base, @WebParam(name = "tableName") String tableName, @WebParam(name = "user") String user) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DataWebWizard;create=true;", "root", "root");
            Statement query = con.createStatement();
            query.executeUpdate(base);
            String other = "INSERT INTO USERSDBS VALUES ('" + user + "', '" + tableName + "')";
            query.executeUpdate(other);
            con.close();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
