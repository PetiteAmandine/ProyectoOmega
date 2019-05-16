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

/**
 *
 * @author Amandine
 */
public class createTableServlet extends HttpServlet {

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
            //create string
            StringBuilder sb = new StringBuilder();
            if (request.getParameter("cCount") != null) {
                int cols = Integer.parseInt(request.getParameter("cCount"));
                String key = "";
                sb.append("create table " + request.getParameter("tableN") + " (");
                for (int i = 1; i <= cols; i++) {
                    String name = request.getParameter("field" + i);
                    String type;
                    switch (request.getParameter("type" + i)) {
                        case "Varchar": type = "varchar(30)";
                                        break;
                        case "Integer": type = "int";
                                        break;
                        default: type = "real";
                                        break;
                    }
                    sb.append(name + " " + type);
                    if (request.getParameter("key").equals("field" + i)) {
                        sb.append(" not null, ");
                        key = name;
                    } else {
                        sb.append(", ");
                    }
                }
                sb.append("primary key(" + key + "))");
                //String is built and ready to be sent to web service
                //once web service is executed with boolean result:
                if (true) {
                    response.sendRedirect("mainPage.jsp?succ=" + request.getParameter("tableN"));
                }
            }
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
