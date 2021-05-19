<%-- 
    Document   : add
    Created on : 19 May, 2021, 9:55:53 PM
    Author     : Vishwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        <%
            try {
                serv.AddSer service = new serv.AddSer();
                java.lang.Integer val1 = Integer.parseInt(request.getParameter("val1"));
                java.lang.Integer val2 = Integer.parseInt(request.getParameter("val2"));
                java.lang.Integer result = service.Addition(val1, val2);
                out.println("Result = " + result);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        %>
    
</html>

