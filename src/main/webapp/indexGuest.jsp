<%-- 
    Document   : indexGuest
    Created on : Oct 1, 2021, 8:58:34 PM
    Author     : TanNV
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <style>
            .article{
                width: 70%;
                margin-left: auto;
                margin-right: auto;
                margin-bottom: 30px;
            }  
        </style>
    </head>
        <div id="main">
            <a href="loginPage">Login</a></br>
            <a href="register">Register</a>
            <h1>Blog  website</h1>
            <form action="searchContentAction" method="POST"> 
                 Search article by content : <input id="searchContent" type="text" name="content" value="${requestScope.searchContent}">
                 <input type="submit" value="Search">
            </form>
            <font color="green">${requestScope.notifi}</font>
             <font color="red">${requestScope.error}</font>
            <c:forEach items="${requestScope.listArticle}" var="article">
                <div class="article">
                <a href="loadDetailGuest?articleID=${article.articleID}"><h3>${article.title}</h3></a>
                Post date : <span>${article.postDate}</span></br>
                Author : <span>${article.author}</span>
                <p>${article.shortDescription}</p>
                </div>
            </c:forEach>
            <div width="50%" class="article">
            <c:forEach begin="1" end="${numberPage}" var="i">
                <a href="loadListAction?page=${i}">${i}</a>
            </c:forEach>
        </div>
         <footer>
            <div class="container" style="background-color:#f1f1f1">
                <a href="LoadListServlet">View blog list</a>
            </div>
        </footer>
    </body>
    <script>
        //document.getElementById("searchContent").value = "${requestScope.searchContent}";
    </script>
    
</html>
