/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author ADMIN
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String GOOGLE_CONTROLLER = "GoogleLoginController";
    private static final String SEARCH_COMTROLLER = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            // xem co dang nhap bang google ko
            String logincode = request.getParameter("code");
  
                if (logincode != null) {
                    url = GOOGLE_CONTROLLER;
                } else {
                    String userID = request.getParameter("userID");
                    String password = request.getParameter("password");
                    UserDAO dao = new UserDAO();
                    UserDTO user = dao.login(userID, password);

                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", user);

                        if (user.isAdmin()) {
                            url = SEARCH_COMTROLLER;
                            request.setAttribute("GET_ALL_PRODUCT", "%");
                        } else if (user.isAdmin() == false) {
                            url = SEARCH_COMTROLLER; // forward vao SearchController de render ra view cho User

                            request.setAttribute("GET_ALL_PRODUCT", "%");
                            // Truyen parameter "%" de lay toan bo product va render ra

                        } else {
                            request.setAttribute("ERROR", "Your role is not support");

                        }

                    } else {
                        request.setAttribute("ERROR", "Incorect UserId or Password");
                    }
                } 

            

        } catch (Exception e) {
            log("Error occur at LoginController: " + e.toString());

        } finally {
            request.getRequestDispatcher(url).forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

}
