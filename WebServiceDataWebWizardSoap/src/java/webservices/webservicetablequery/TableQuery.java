/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices.webservicetablequery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
@WebService(serviceName = "TableQuery")
@Stateless()
public class TableQuery {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "queryTable")
    public String queryTable(@WebParam(name = "tableName") String tableName) {
        JSONObject jsonResponse = new JSONObject();
        JSONArray rows = new JSONArray();
        JSONArray types = new JSONArray();
        JSONArray keys = new JSONArray();
        ArrayList<String> columns = new ArrayList();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DataWebWizard",
                    "root", "root");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData cols = rs.getMetaData();
            for (int i = 1; i <= cols.getColumnCount(); i++) {
                columns.add(cols.getColumnLabel(i));
                types.add(cols.getColumnTypeName(i));
                keys.add(cols.getColumnLabel(i));
            }
            while (rs.next()) {
                JSONArray row = new JSONArray();
                for (int i = 0; i < columns.size(); i++) {
                    row.add(rs.getString(i+1));
                }
                rows.add(row);
            }
            jsonResponse.put("types", types);
            jsonResponse.put("keys", keys);
            jsonResponse.put("rows", rows);
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TableQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonResponse.toString();
    }
}
