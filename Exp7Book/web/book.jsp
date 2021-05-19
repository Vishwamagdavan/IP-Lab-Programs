<%-- 
    Document   : book
    Created on : 19 May, 2021, 8:42:54 PM
    Author     : Vishwa
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dynamic Webpage</title>
    </head>
    <body>
        <%
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/bookdb", "book",
                    "book");
            Statement st = con.createStatement();
            String str = "select * from book";
            ResultSet rs = st.executeQuery(str);
            out.println("<table border='2'><tr><th>ISBN Number</th><th>Book Name</th><th> Author </th> <th>Price</th> </tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>"
                        + rs.getString(3) + "</td><td>" + rs.getInt(4) + "</td></tr>");
            }
            out.println("</table><br><br>");
            out.println("<form action='search'>");
            out.println("ISBN Number:" + "" + "<input type='search' name='search'><br><br><br>");
            out.println("<input type='submit' value='Seacrh'");
        %>
    </body>
</html>

