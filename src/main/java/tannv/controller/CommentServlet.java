/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
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
import tannv.comment.CommentDAO;
import tannv.comment.CommentDTO;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/CommentServlet"})
public class CommentServlet extends HttpServlet {

    final static Logger LOGGER = Logger.getLogger(CommentServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
        if (userData == null) { 
                request.setAttribute("error", "You need permission to do this function");
                RequestDispatcher rd = request.getRequestDispatcher("loginPageError");
                rd.forward(request, response);
        }
        if (userData.getRole() != 1 ) { // khong phai member 
                request.setAttribute("error", "You need permission to do this function");
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
        request.setCharacterEncoding("UTF-8");
        String articleID = request.getParameter("articleID");
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        InforUserDTO userData = (InforUserDTO) session.getAttribute("userData");
        CommentDAO dao = new CommentDAO();
        ArticleDAO articleDAO = new ArticleDAO();
        try {
            if (content.length() != 0) {
                CommentDTO dto = dao.createComment(new CommentDTO(Integer.parseInt(articleID), content, userData.getInforID()));
            }
            ArticleDTO articleDTO = articleDAO.getArticleByID(Integer.parseInt(articleID));
            CommentDAO commentDAO = new CommentDAO();
            ArrayList<CommentDTO> listComment = commentDAO.getCommentByArticle(Integer.parseInt(articleID));
            request.setAttribute("article", articleDTO);
            request.setAttribute("listComment", listComment);
            RequestDispatcher rd = request.getRequestDispatcher("detailArticleMember");
            rd.forward(request, response);
        } catch (Exception e) {
            LOGGER.error("Create comment is error" + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
