/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import google.GoogleAccountDAO;
import google.GoogleAccountDTO;
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
public class GoogleLoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = false;
        try {
            // Google
            String code = request.getParameter("code");
            //System.out.println(code);
            String accessToken = GoogleAccountDAO.getToken(code);
            GoogleAccountDTO account = GoogleAccountDAO.getUserInfo(accessToken);
            if (account != null) {
                url = SUCCESS;
                String userID = account.getId();
                String email = account.getEmail();
                // Forworad vao SearchController de render ra view cho nguoi dung
                UserDAO dao = new UserDAO();
                // chekc whether th account is exist on the databse
                
                // if the account is new user, then insert it into database 
                if (!dao.checkExist(userID)){
                    dao.insertGoogleUser(userID,email,email);
                        // set fullname and email is gmail bacuse the return fullname is null
                }
                UserDTO user = new UserDTO();
                user.setUserID(userID);
               
                user.setFullName(email);// set fullname is email
                user.setEmail(email);
                user.setRoleID(false);
                HttpSession session = request.getSession();
                session.setAttribute("USER", user);
                request.setAttribute("GET_ALL_PRODUCT", "%");
            } else {
                request.setAttribute("ERROR", "Login failed, try again!!!");

            }

        } catch (Exception e) {
            log("Error occured at GoogleLoginController : " + e.toString());
        } finally {
            // Gui vao search controller de search product va render ra view
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
