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
import product.ProductDAO;
import product.ProductDTO;
import product.ProductError;

/**
 *
 * @author ADMIN
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";
    private static final String SYS_ERROR = " error.jsp";
  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDTO product = null;
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            double price = Double.valueOf(request.getParameter("price"));
            int quantity = Integer.valueOf(request.getParameter("quantity"));
            String importDate = request.getParameter("importDate");
            String usingDate = request.getParameter("usingDate");
            String imgLink = request.getParameter("imagePath");
            // get position of the data for Catch Error
            String position = request.getParameter("position");
            ProductError proError = new ProductError();

            // Validation for update
            boolean validation = true;
            if (productName.length() <= 0 || productName.length() > 40) {
                proError.setProductNameError("The product name must be valid and < 40 character");
                validation = false;
            }
            if (price <= 0) {
                proError.setProductPriceError("The product price must be posivtive number and > 0");
                validation = false;
            }
            if (quantity <= 0) {
                proError.setProductQuantity("The quatity must be a number and >= 0 ");
                validation = false;

            }

            if (!proError.isValidDateformat(importDate)) {

                proError.setProductImportDateError("The import date must be a valid date");
                validation = false;
            }
            if (!proError.isValidDateformat(usingDate)) {
                proError.setProductUsingDateError("The using date must be a valid date");
                validation = false;
            }
            if (!proError.isValidDateError(importDate, usingDate)) {
                proError.setInvalidDateError("The Using Date must after the Import Date!!!");
                validation = false;

            }

            // Xu ly file ảnh
            if (imgLink == null) {
                proError.setImageError("The image is not available!!!");
                validation = false;

            }

            if (validation) {
                ProductDAO dao = new ProductDAO();
                product = new ProductDTO(productID, productName, price, quantity, importDate, usingDate, imgLink);

                // update information at productID
                boolean check = dao.update(product);
                if (check) {
                    url = SUCCESS;
                } else {
                  
                    request.setAttribute("ERROR", "System error please try again!!!");
                    url = SYS_ERROR;

                }
            } else {
                proError.setLine("Error at row " + position + " :");
                request.setAttribute("ERROR", proError);
                

            }

        } catch (Exception e) {
            log("Error at UpdateController:" + e.toString());

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
