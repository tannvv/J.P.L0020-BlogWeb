/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tannv.article.ArticleDAO;
import tannv.infor_user.InforUserDTO;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    final static Logger LOGGER = Logger.getLogger(SearchContentServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
        if (userData.getRole() != 2) {
            request.setAttribute("error", "You don't have permission to do function");
            RequestDispatcher rd = request.getRequestDispatcher("loginPageError");
            rd.forward(request, response);
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
        processRequest(request, response);
        try {
            int articleID = Integer.parseInt(request.getParameter("articleID"));
            int status = Integer.parseInt(request.getParameter("status"));
            ArticleDAO articleDAO = new ArticleDAO();
            boolean check = articleDAO.updateStatus(articleID, status);
            request.setAttribute("notifi", "Update success");
            RequestDispatcher rd = request.getRequestDispatcher("loadListAction");
            rd.forward(request, response);
        } catch (Exception e) {
             LOGGER.error("Update article is error" + e.getMessage());
            response.sendRedirect("invalid");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
