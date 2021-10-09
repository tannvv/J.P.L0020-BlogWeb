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

/**
 *
 * @author TanNV
 */
@WebServlet(name = "LoadListServlet", urlPatterns = {"/LoadListServlet"})
public class LoadListServlet extends HttpServlet {
    final static Logger LOGGER = Logger.getLogger(LoadListServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
            int page = 0;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception ex) {
                page = 1;
                LOGGER.error("Page default = 1" + ex.getMessage());
            }
            if (userData != null) {
                if (userData.getRole() == 1) { // memeber
                    loadForMember(request, response, page);
                } else {                  // admin role == 2
                    loadForAdmin(request, response, page);
                }
            } else {    // guest
                loadForGuest(request, response, page);
            }

        } catch (Exception e) {
              LOGGER.error("Get list is error" + e.getMessage());
            response.sendRedirect("invalid");
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
          LOGGER.error("Get list is error" + ex.getMessage());
            response.sendRedirect("invalid");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
          LOGGER.error("Get list is error" + ex.getMessage());
            response.sendRedirect("invalid");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void loadForGuest(HttpServletRequest request, HttpServletResponse response, int page) throws SQLException, ServletException, IOException {
        ArticleDAO dao = new ArticleDAO();
        ArrayList<ArticleDTO> listArticle = dao.getPageArticleUser(page);
        int numberPage = dao.getPageNumberUser();
        request.setAttribute("listArticle", listArticle);
        request.setAttribute("numberPage", numberPage);
        RequestDispatcher rd = request.getRequestDispatcher("indexGuest");
        rd.forward(request, response);
    }

    private void loadForMember(HttpServletRequest request, HttpServletResponse response, int page) throws SQLException, ServletException, IOException {
        ArticleDAO dao = new ArticleDAO();
        ArrayList<ArticleDTO> listArticle = dao.getPageArticleUser(page);
        int numberPage = dao.getPageNumberUser();
        request.setAttribute("listArticle", listArticle);
        request.setAttribute("numberPage", numberPage);
        RequestDispatcher rd = request.getRequestDispatcher("indexMember");
        rd.forward(request, response);
    }

    private void loadForAdmin(HttpServletRequest request, HttpServletResponse response, int page) throws SQLException, ServletException, IOException {
        ArticleDAO dao = new ArticleDAO();
        ArrayList<ArticleDTO> listArticle = dao.getPageArticleAdmin(page);
        int numberPage = dao.getPageNumberAdmin();
        request.setAttribute("listArticle", listArticle);
        request.setAttribute("numberPage", numberPage);
        RequestDispatcher rd = request.getRequestDispatcher("indexAdmin");
        rd.forward(request, response);
    }

}
