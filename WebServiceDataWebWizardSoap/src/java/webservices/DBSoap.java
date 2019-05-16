/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Amandine
 */
@WebService(serviceName = "DBSoap")
public class DBSoap {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DataWebWizard", "root", "root");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM USERS WHERE USERNAME = '" + user
                    + "' AND PASS = '" + pass + "'");
            if (rs.next()) {
                return true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBSoap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBSoap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
