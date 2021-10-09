<%-- 
    Document   : detailArticleGuest
    Created on : Oct 1, 2021, 9:00:15 PM
    Author     : TanNV
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="main">
            <div class="content">
                <h2>${requestScope.article.title}</h2>
                Short description : <span>${requestScope.article.shortDescription}</span></br>
                Author : <span>${requestScope.article.author}<span></br>
                        Post date : <span>${requestScope.article.postDate}</span></br></br></br>
                        <p>${requestScope.article.contentArticle}</p>
                        </div>
                        <h3>COMMENT</h3>
                        <div class="comment">
                            <c:forEach items="${requestScope.listComment}" var="comment">
                                ${comment.commentDate} - ${comment.userID} : ${comment.contentComment} </br>
                            </c:forEach> 
                        </div>
                        <form action="commentAction" method="POST"> 
                            <input type="hidden" name="articleID" value="${requestScope.article.articleID}">
                            Input your comment : <input type="text" name="content">
                            <input type="submit" value="Comment">
                        </form>
                        <footer>
                            <div class="container" style="background-color:#f1f1f1">
                                <a href="loadListAction">View blog list</a>
                            </div>
                        </footer>
                        </div>
                        </body> 
                        </html>
