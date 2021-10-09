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
import org.apache.log4j.Logger;
import tannv.infor_user.InforUserDAO;
import tannv.infor_user.InforUserDTO;
import tannv.account.AccountDAO;
import tannv.account.AccountDTO;
import tannv.account.AccountError;
import tannv.article.ArticleDAO;
import tannv.article.ArticleDTO;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    final static Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("register");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            AccountDTO newAcc = new AccountDTO(email, password, 1);
            InforUserDAO inforUserDAO = new InforUserDAO();
            AccountError errAcc = AccountError.checkValidAccount(newAcc);
            if (!password.equals(rePassword)) {
                errAcc.setRePassword("password and re-password not same");
            }
            if (errAcc.getEmail() == null && errAcc.getPassword() == null && errAcc.getRePassword() == null) { // vali infor
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.createAccount(newAcc);
                InforUserDTO userDTO = new InforUserDTO(email, null, null, 1);
                inforUserDAO.createInfor(userDTO);
                request.setAttribute("notifi", "Create account success");
                loadForGuest(request, response, 1);
            } else {              // invalid infor.. return the register page
                request.setAttribute("error", errAcc);
                request.setAttribute("dto", newAcc);
                request.setAttribute("rePassword", rePassword);
                RequestDispatcher rd = request.getRequestDispatcher("registerError");
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

    private void loadForGuest(HttpServletRequest request, HttpServletResponse response, int page) throws SQLException, ServletException, IOException {
        ArticleDAO dao = new ArticleDAO();
        ArrayList<ArticleDTO> listArticle = dao.getPageArticleUser(page);
        int numberPage = dao.getPageNumberUser();
        request.setAttribute("listArticle", listArticle);
        request.setAttribute("numberPage", numberPage);
        RequestDispatcher rd = request.getRequestDispatcher("indexGuest");
        rd.forward(request, response);
    }
}
