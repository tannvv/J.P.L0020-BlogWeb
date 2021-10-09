/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tannv.infor_user.InforUserDTO;
import tannv.article.ArticleDAO;
import tannv.article.ArticleDTO;
import tannv.article.ArticleError;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "PostArticleServlet", urlPatterns = {"/PostArticleServlet"})
public class PostArticleServlet extends HttpServlet {

    final static Logger LOGGER = Logger.getLogger(PostArticleServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
        if (userData.getRole() != 1) {  // not member
            request.setAttribute("error", "You don't have permission to do function");
            RequestDispatcher rd = request.getRequestDispatcher("loginPageError");
            rd.forward(request, response);
        }
        response.sendRedirect("postPage");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
            if (userData.getRole() != 1) {  // not member
                request.setAttribute("error", "You don't have permission to do function");
                RequestDispatcher rd = request.getRequestDispatcher("loginPageError");
                rd.forward(request, response);
            }
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String shortDescription = request.getParameter("shortDescription");
            String content = request.getParameter("content");
            ArticleDAO dao = new ArticleDAO();
            ArticleDTO article = new ArticleDTO(title, shortDescription, content, author, userData.getInforID());
            ArticleError error = ArticleError.validArticle(article);
            if (error.getTitle() == null && error.getAuthor() == null && error.getShortDescription() == null && error.getContentArticle() == null) {
                // valid infor to save into database
                ArticleDTO check =  dao.createArticle(article);
                request.setAttribute("notifi", "Create new article success, wait for admin accept");
                loadForMember(request, response, 1);
            } else {
                request.setAttribute("error", error);
                request.setAttribute("article", article);
                RequestDispatcher rd = request.getRequestDispatcher("postPage");
                rd.forward(request, response);
            }
        } catch (Exception ex) {
             LOGGER.error("Create article" + ex.getMessage());
            response.sendRedirect("invalid");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void loadForMember(HttpServletRequest request, HttpServletResponse response, int page) throws SQLException, ServletException, IOException {
        ArticleDAO dao = new ArticleDAO();
        ArrayList<ArticleDTO> listArticle = dao.getPageArticleUser(page);
        int numberPage = dao.getPageNumberUser();
        request.setAttribute("listArticle", listArticle);
        request.setAttribute("numberPage", numberPage);
        RequestDispatcher rd = request.getRequestDispatcher("indexMember");
        rd.forward(request, response);
    }

}
