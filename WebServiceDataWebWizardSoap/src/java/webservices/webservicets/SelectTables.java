/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.webservicets;

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
import javax.ejb.Stateless;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Amandine
 */
@WebService(serviceName = "SelectTables")
@Stateless()
public class SelectTables {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "selectTables")
    public String selectTables(@WebParam(name = "user") String user) {
        JSONObject jsonResponse = new JSONObject();
        JSONArray tables = new JSONArray();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DataWebWizard",
                    "root", "root");
            Statement query = con.createStatement();
            String qs = "SELECT TABLENAME FROM USERSDBS WHERE USERNAME='" + user + "'";
            ResultSet rs = query.executeQuery(qs);
            while (rs.next()) {
                JSONObject table = new JSONObject();
                table.put("nombre", rs.getString("tablename"));
                tables.add(table);
            }
            jsonResponse.put("tables", tables);
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SelectTables.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SelectTables.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonResponse.toString();
    }
}
