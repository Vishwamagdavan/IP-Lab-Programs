/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishwa
 */
@WebServlet(urlPatterns = {"/Shoppingcart"})
public class Shoppingcart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingCart</title>");
            out.println("</head>");
            out.println("<body><form action=Cart>");
            out.println("<h1>Product Details</h1>");
            out.println("<table border=1>");
            out.println("<tr><th>Product ID</th><th>Product Name</th><th>UnitPrice</th></tr >");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/shopping", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from product");
            String pid = null;
            String pname = null;
            String price = null;
            while (rs.next()) {
                pid = rs.getString(1);
                pname = rs.getString(2);
                price = rs.getString(3);
                out.println("<tr><td>" + pid + "</td>");
                out.println("<td>" + pname + "</td>");
                out.println("<td>" + price + "</td></tr>");
            }
            out.println("</table>");
            out.println("<br><br><br>");
            out.println("<h2>Shopping cart</h2><table>");
            out.println("<tr><td>Enter the product id</td>");
            out.println("<td><input type='text' name='pid'/></td></tr>");
            out.println("<tr><td>Quantity</td>");
            out.println("<td><input type='text' name='qty'/></td></tr></table>");
            out.print("<input type='submit' name='submit' value='Submit'/>");
            out.println("<input type='submit' name='list' value='List'/>");
            out.println("</body>");
            out.println("</html>");

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Shoppingcart.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Shoppingcart.class.getName()).log(Level.SEVERE, null, ex);
        }
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
