/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import product.ProductDAO;
import product.ProductDTO;
import user.UserDTO;

/**
 *
 * @author ADMIN
 */
public class SearchController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String USER_PAGE = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String searchVal = request.getParameter("Search");
            /// Bien nay dung de search all product, va tra ve trang user trong lan login dau tien
            String searchAllProduct = (String) request.getAttribute("GET_ALL_PRODUCT");
            if (searchVal == null) {
                searchVal = searchAllProduct;
            }

            /// Xac nhan xem ai dang Login (User hoac Admin)
            HttpSession session = request.getSession();
            if (session == null) {
                response.sendRedirect("login.jsp");
                return;

            }
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> productList = null;
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user.isAdmin()) {
                url = ADMIN_PAGE;
                productList = dao.searchProductForAdmin(searchVal);

            } else if (!user.isAdmin()) {

                url = USER_PAGE;
                productList = dao.searchProductForUser(searchVal);
            } else {
                request.setAttribute("ERROR", "Your Role is not support!!!");

            }

            if (!productList.isEmpty()) {
                request.setAttribute("PRODUCT_LIST", productList);

            } else if(productList.isEmpty() || productList == null) {
                request.setAttribute("ERROR", "The product not found!!!");

            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
