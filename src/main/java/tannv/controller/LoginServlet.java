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
import tannv.infor_user.InforUserDAO;
import tannv.infor_user.InforUserDTO;
import tannv.account.AccountDAO;
import tannv.account.AccountDTO;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
   final static Logger LOGGER = Logger.getLogger(LoginServlet.class); 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("loginPage");
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AccountDTO account = new AccountDTO(email, password, 1);
        AccountDAO dao = new AccountDAO();
        try {
            boolean login = dao.checkLogin(email, password);
            if (login) {   //login success
                InforUserDAO inforUserDAO = new InforUserDAO();
                InforUserDTO userData = inforUserDAO.getInforByID(email);
                HttpSession sesstion = request.getSession();
                sesstion.setAttribute("userData", userData);
                RequestDispatcher rd = request.getRequestDispatcher("loadListAction");
                rd.forward(request, response);
            } else {
                request.setAttribute("error", "Email or password is incrrect");
                request.setAttribute("account", account);
                RequestDispatcher rd = request.getRequestDispatcher("loginPageError");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.error("Get list is error" + e.getMessage());
            response.sendRedirect("invalid");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
