<%-- 
    Document   : login
    Created on : Sep 8, 2021, 10:28:17 AM
    Author     : TanNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <font color="red">${requestScope.error}</font>
        <form action="LoginServlet" method="POST">
            <table>
                <tr>
                    <td>
                        User ID :
                    </td>
                    <td>
                        <input type="text" placeholder="Enter user ID" name="userID" value="${requestScope.account.email}">
                    </td>
                </tr>
                <tr>
                    <td>
                        Password :
                    </td>
                    <td>
                        <input type="password" placeholder="Enter password" name="password" value="${requestScope.account.password}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Login">
                    </td>
                    <td>
                        <input type="reset" value="Reset">
                    </td>
                </tr>
            </table>
        </form>
        <a href="register">Register</a>
    </body>
</html>
