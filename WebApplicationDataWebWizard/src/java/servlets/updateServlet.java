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
import wsclientupdate.UpdateClient;

/**
 *
 * @author Amandine
 */
public class updateServlet extends HttpServlet {

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
            StringBuilder sb = new StringBuilder();
            if (request.getParameter("delBut") != null) {
                for (int i = 0; i < Integer.parseInt(request.getParameter("cCount")); i++) {
                    if (request.getParameter("check" + i) != null) {
                        sb.append("delete from " + request.getParameter("tTable") + " where ");
                        for (int j = 0; j < Integer.parseInt(request.getParameter("pCount")); j++) {
                            String type = request.getParameter("rowtype" + j + i);
                            if (type.equals("VARCHAR")) {
                                sb.append(request.getParameter("rowname" + i + j) + "='" + request.getParameter("row" + i + j) + "'");
                            } else if (type.equals("INTEGER")) {
                                Integer myInt = Integer.parseInt(request.getParameter("row" + i + j));
                                sb.append(request.getParameter("rowname" + i + j) + "=" + myInt);
                            } else {
                                Double myDouble = Double.parseDouble(request.getParameter("row" + i + j));
                                sb.append(request.getParameter("rowname" + i + j) + "=" + myDouble);
                            }
                            if (j < Integer.parseInt(request.getParameter("pCount")) - 1) {
                                sb.append(" and ");
                            }
                        }
                        UpdateClient client = new UpdateClient(sb.toString());
                        String res = client.doUpdate();
                        sb = new StringBuilder();
                        if (!res.equals("true")) {
                            response.sendRedirect("checkTables.jsp?fail=" + request.getParameter("tTable"));
                        }
                        out.write(sb.toString());
                    }
                }
                response.sendRedirect("checkTables.jsp?succ=" + request.getParameter("tTable"));
            } else if (request.getParameter("edBut") != null) {

            } else {
                int cuenta = Integer.parseInt(request.getParameter("miCount"));
                if (cuenta != 0) {
                    StringBuilder query = new StringBuilder("INSERT INTO "
                            + request.getParameter("miTabla") + " VALUES(");
                    for (int i = 0; i != cuenta; i++) {
                        String type = request.getParameter("rowtype0" + i);
                        out.write(type);
                        if (type.equals("VARCHAR")) {
                            query.append("'" + request.getParameter("txtB" + i) + "'");
                        } else {
                            Integer myInt = Integer.parseInt(request.getParameter("txtB" + i));
                            query.append("'" + myInt + "'");
                        }
                        if (i != cuenta - 1) {
                            query.append(", ");
                        }
                    }
                    query.append(");");
                    UpdateClient client = new UpdateClient(query.toString());
                    String res = client.doUpdate();
                    if (res.equals("true")) {
                        response.sendRedirect("checkTables.jsp?succ=" + request.getParameter("miTabla"));
                    }
                } else
                    response.sendRedirect("checkTables.jsp?fail=" + request.getParameter("miTabla"));
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
