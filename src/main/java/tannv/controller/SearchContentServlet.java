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
import tannv.util.Util;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "SearchContentServlet", urlPatterns = {"/SearchContentServlet"})
public class SearchContentServlet extends HttpServlet {
    final static Logger LOGGER = Logger.getLogger(PostArticleServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
          request.setAttribute("searchContent", content);
        // respone content
        checkContent(request,response,content);
        ArticleDAO dao = new ArticleDAO();
        int page = 0;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception ex) {
            page = 1;
            LOGGER.error("Page default = 1" + ex.getMessage());
        }
        if (userData == null) {  // guest
            ArrayList<ArticleDTO> listArticle = dao.getArticleByContentUser(content, page);
            checkSizeArticle(listArticle, request, response);
            int numberPage = Util.numberPage(listArticle.size(), 20);
           
            request.setAttribute("listArticle", listArticle);
            request.setAttribute("numberPage", numberPage);
            RequestDispatcher rd = request.getRequestDispatcher("indexGuest");
            rd.forward(request, response);
        } else {
            if (userData.getRole() == 1) {  //member
                ArrayList<ArticleDTO> listArticle = dao.getArticleByContentUser(content, page);
                checkSizeArticle(listArticle, request, response);
               // setInforRequest(request,response);
                int numberPage = Util.numberPage(listArticle.size(), 20);
          
                request.setAttribute("listArticle", listArticle);
                request.setAttribute("numberPage", numberPage);
                RequestDispatcher rd = request.getRequestDispatcher("indexMember");
                rd.forward(request, response);
            } else {            //admin
                ArrayList<ArticleDTO> listArticle = dao.getArticleByContentAdmin(content, page);
                checkSizeArticle(listArticle, request, response);
                int numberPage = Util.numberPage(listArticle.size(), 20);
      
                request.setAttribute("listArticle", listArticle);
                request.setAttribute("numberPage", numberPage);
                RequestDispatcher rd = request.getRequestDispatcher("indexAdmin");
                rd.forward(request, response);
            }
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
           LOGGER.error("Search by content is error" + ex.getMessage());
            response.sendRedirect("invalid");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            LOGGER.error("Search by content is error" + ex.getMessage());
            response.sendRedirect("invalid");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void checkSizeArticle(ArrayList<ArticleDTO> listArticle, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
        if (listArticle.isEmpty()) {
 
            request.setAttribute("error", "Can not find any article with the words");
            if (userData == null) {     //guest
                RequestDispatcher rd = request.getRequestDispatcher("indexGuest");
                rd.forward(request, response);
            } else {
                if (userData.getRole() == 1) { //member
                    RequestDispatcher rd = request.getRequestDispatcher("indexMember");
                    rd.forward(request, response);
                } else {                          // admin
                    RequestDispatcher rd = request.getRequestDispatcher("indexAdmin");
                    rd.forward(request, response);
                }
            }
        }
    }

    private void checkContent(HttpServletRequest request, HttpServletResponse response, String content) throws ServletException, IOException {
        HttpSession session = request.getSession();
        InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
        if (content.length() == 0) {
            request.setAttribute("error", "Can not find any article with the words");
            if (userData == null) {     //guest
                RequestDispatcher rd = request.getRequestDispatcher("indexGuest");
                rd.forward(request, response);
            } else {
                if (userData.getRole() == 1) { //member
                    RequestDispatcher rd = request.getRequestDispatcher("indexMember");
                    rd.forward(request, response);
                } else {                          // admin
                    RequestDispatcher rd = request.getRequestDispatcher("indexAdmin");
                    rd.forward(request, response);
                }
            }
        }
    }

}
