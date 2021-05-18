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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishwa
 */
@WebServlet(urlPatterns = {"/Cart"})
public class Cart extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cart</title>");
            out.println("</head>");
            out.println("<body>");
            String pid = request.getParameter("pid");
            String qty = request.getParameter("qty");
            String submit = request.getParameter("submit");
            String list = request.getParameter("list");
            if (submit != null) {
                Cookie ck1 = new Cookie(pid, qty);
                response.addCookie(ck1);
                response.sendRedirect("Shoppingcart");
            }
            if (list != null) {
                Cookie ck1[] = request.getCookies();
                out.print("<h1>Your Cart Details</h1>");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/shopping", "root", "root");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from product");
                String[][] product = new String[10][3];
                int i = 0;
                int j = 0;
                while (rs.next()) {
                    product[i][j++] = rs.getString(1);
                    product[i][j++] = rs.getString(2);
                    product[i][j] = rs.getString(3);
                    i++;
                    j = 0;
                }
                String price = null;
                String name = null;
                int amount = 0;
                out.println("<table border=1>");
                out.println("<tr><th>S.No</th><th>Product ID</th><th>Product Name</th><th>Unit Price</th><th>Quantity</th ><th>Amount</th><tr>");
                for (i = 1; i < ck1.length; i++) {
                    String pname = ck1[i].getName();
                    String qtys = ck1[i].getValue();
                    System.out.println(pname);
                    for (int k = 0; k < 4; k++) {
                        String s1 = product[k][0];
                        if (s1.equals(pname)) {
                            price = product[k][2];
                            name = product[k][1];
                            int q = Integer.parseInt(qtys);
                            int p = Integer.parseInt(price);
                            amount = amount + q * p;
                            out.print("<tr><td>"+i+"</td><td>"+pname+"</td><td>"+name+"</td><td>"+price+"</td><td>"+q+"</td><td>"+q*p+"</td></tr>");
                            break;
                        }
                    }
                }
                out.println("</table>");
                out.println("<h2>Total Amount:" + amount + "</h2>");
            }
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
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Cart.class.getName()).log(Level.SEVERE, null, ex);
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
