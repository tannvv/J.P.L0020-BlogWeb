/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SearchStatusAndContentServlet", urlPatterns = {"/SearchStatusAndContentServlet"})
public class SearchStatusAndContentServlet extends HttpServlet {

    final static Logger LOGGER = Logger.getLogger(SearchContentServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchStatusAndContentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchStatusAndContentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
            String content = request.getParameter("content").trim();
            int status = Integer.parseInt(request.getParameter("status"));
            ArticleDAO articleDAO = new ArticleDAO();
            request.setAttribute("searchContent", content);
            request.setAttribute("status", status);
            if (content.length() == 0) {
                request.setAttribute("status", status);
                request.setAttribute("error", "Can not find any article with the words");
                RequestDispatcher rd = request.getRequestDispatcher("indexAdmin");
                rd.forward(request, response);
            }
            int page = 0;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception ex) {
                page = 1;
                log(ex.getMessage());
            }
      
            ArrayList<ArticleDTO> listArticle = new ArrayList<ArticleDTO>();
            listArticle = articleDAO.getArticleByContentAndStatus(content, status, page);
            checkSizeArticle(listArticle, request, response);
            int numberPage = Util.numberPage(listArticle.size(), 20);
            request.setAttribute("listArticle", listArticle);
            request.setAttribute("numberPage", numberPage);
            RequestDispatcher rd = request.getRequestDispatcher("indexAdmin");
            rd.forward(request, response);

        } catch (Exception e) {
            LOGGER.error("Search by content is error" + e.getMessage());
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

}
