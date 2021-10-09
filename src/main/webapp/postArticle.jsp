<%-- 
    Document   : postArticle
    Created on : Oct 2, 2021, 3:41:16 PM
    Author     : TanNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Add new article</h1>
       

        <form action="postAction" method="POST">
            Title : <input type="text" name="title" value="${requestScope.article.title}"><font color="red">${requestScope.error.title}</font></br>
            Author : <input type="text" name="author" value="${requestScope.article.author}"><font color="red">${requestScope.error.author}</font></br>
            Short description : </br>
            <textarea name="shortDescription" rows="10" cols="50">${requestScope.article.shortDescription}</textarea>
            <font color="red">${requestScope.error.shortDescription}</font></br>
            Content : </br>
             <textarea name="content" rows="50" cols="50">${requestScope.article.contentArticle}</textarea>
             <font color="red">${requestScope.error.contentArticle}</font></br>
            <input type="submit" value="POST">
        </form>
        
        <footer>
            <div class="container" style="background-color:#f1f1f1">
                Hello : ${sessionScope.userData.userName}</br><a href="LoadListServlet">View blog list</a>
                - <a href="LogoutServlet">Log out</a>   </br></br>
            </div>
        </footer>
    </body>
</html>

