<%@page contentType="text/html" pageEncoding="UTF-8"%> <!DOCTYPE html> 
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> <title>Login Page</title> 
    </head> 
    <body> 
        <%!
            String uname;
            String pwd;
        %> 
        <%
            uname = request.getParameter("uname");
            pwd = request.getParameter("pwd");
            System.out.print(uname);
            if (uname != null && pwd != null) {
                response.sendRedirect("Shoppingcart");
            } else {
                response.sendRedirect("index.html");
            }
        %> 
    </body> 
</html> 
