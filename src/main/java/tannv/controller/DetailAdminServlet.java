/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "DetailAdminServlet", urlPatterns = {"/DetailAdminServlet"})
public class DetailAdminServlet extends HttpServlet {
    final static Logger LOGGER = Logger.getLogger(DetailAdminServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
            if (userData.getRole() != 2) {
                request.setAttribute("error", "You don't have permission to do function");
                RequestDispatcher rd = request.getRequestDispatcher("loginPageError");
                rd.forward(request, response);
            }

            ArticleDAO dao = new ArticleDAO();
            String id = request.getParameter("articleID");
            ArticleDTO articleDTO = dao.getArticleByID(Integer.parseInt(id));
            request.setAttribute("article", articleDTO);
            RequestDispatcher rd = request.getRequestDispatcher("detailArticleAdmin");
            rd.forward(request, response);
        } catch (Exception e) {
            LOGGER.error("Get infor article is error" + e.getMessage());
            response.sendRedirect("invalid");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            LOGGER.error("Get infor article is error" + ex.getMessage());
            response.sendRedirect("invalid");
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            LOGGER.error("Get infor article is error" + ex.getMessage());
            response.sendRedirect("invalid");
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
