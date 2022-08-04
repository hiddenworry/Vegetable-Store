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
import mail.JavaSendMail;
import order.Order;
import shopping.Cart;
import user.UserDTO;

/**
 *
 * @author ADMIN
 */
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "success.jsp";
    private static final String PAYPAL = "Paypal";
    private static final String PAY_AFTER_DELIVERY = "PayAfterDelivery";
    private static final String PAYPALCONTROLLER = "PaypalController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();

            boolean check = false;
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                UserDTO user = (UserDTO) session.getAttribute("USER");
                Order order = new Order();
                double total = order.checkOrder(cart);

                if (cart != null || !cart.getCart().isEmpty()) {
                    String payment = request.getParameter("Payment");
                    String PAYPAL_SUCCESS = (String) request.getAttribute("PAYPAL_SUCCESS");
                    if (total != 0) {
                       
                        // Bien PAYPAL_SUCEESS se nhan tin hieu thanh toan paypal thanh cong
                        // Sau khi PAYPAL_SUCESS = true thi bat dau inser Order vao database
                        if (PAYPAL.equals(payment)) {
                             request.setAttribute("TOTAL", total);
                            // forward vao PaypalController
                            url = PAYPALCONTROLLER;

                        } else if (PAY_AFTER_DELIVERY.equals(payment) || "true".equals(PAYPAL_SUCCESS)) {

                            check = order.createNewOrder(cart, user.getUserID());
                            if (check) {
                                url = SUCCESS;
                                session.setAttribute("CART", null);
                                cart = null;

                                // Gui mail xac nhan
                                JavaSendMail j = new JavaSendMail();
                                j.sendMail(user.getEmail(), "Xac nhan don hang", "Don hang" + order.getOrderID() + "se duocc chuyen đen trong vai ngay nua :)) form SE151333 Asignment");
                            } else {
                                request.setAttribute("ERROR", order.getError());
                            }
                        }
                    }

                    } else {
                        request.setAttribute("ERROR", "Your cart is empty!!!");

                    }

                } else {
                    request.setAttribute("ERROR", "Error ocured, please try again!!!");
                }

            }catch (Exception e) {
            log("Error at CheckOutController:" + e.toString());
        }finally {
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
        
            () {
        return "Short description";
        }// </editor-fold>

    }
