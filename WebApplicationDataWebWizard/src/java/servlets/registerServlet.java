/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Amandine
 */
public class registerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataPresenter</title>");            
            out.println("</head>");
            out.println("<body>");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DataWebWizard", "root", "root");
            out.println("<p>Entré.</p>");
            out.println("</body>");
            out.println("</html>");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM USERS WHERE USERNAME = '" + request.getParameter("user") + "'");
            if (rs.next()) {
                response.sendRedirect("index.jsp?rerror=taken");
            } else {
                query.executeUpdate("INSERT INTO USERS VALUES ('" + request.getParameter("user") + "', '" + 
                        request.getParameter("pass") + "')");
                response.sendRedirect("index.jsp?succ=" + request.getParameter("user"));
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
            //response.sendRedirect("index.jsp?rerror=err");
        } catch (SQLException ex) {
            Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
            //response.sendRedirect("index.jsp?rerror=err");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
