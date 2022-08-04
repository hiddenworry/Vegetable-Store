/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.sql.Date;
import product.ProductDAO;
import product.ProductDTO;
import shopping.Cart;

/**
 *
 * @author ADMIN
 */
public class Order extends OrderError {

    private int orderID;
    private String userID;
    private Date date;
    private double total;

    public Order(int orderID, String userID, Date date, double total) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.total = total;
    }

    public Order() {
        this.orderID = 0;
        this.userID = "";
        this.date = null;
        this.total = 0;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Tao order va chen vao database
    // Create order phien ban public, dung de trien khai tat ca cac hoat dong khi tao order
    // Muốn tạo hóa đơn phải commit 2 quá trình: Tạo hóa đơn bên bản order
    // Và tạo chi tiết hóa dơn trên bản orderDetail
    // nếu 1 trong 2 quá trình bị lỗi thì sẽ roolback
    // Nếu thành công thì sẽ tiếp tục giảm số lượng của các sản phẩm đã bán
    public boolean createNewOrder(Cart cart, String userID) {
        boolean check = false;
        ProductDAO dao = new ProductDAO();
        try {
            double totalMoney = checkOrder(cart);

            if (totalMoney > 0) {
                this.userID = userID;
                this.total = totalMoney;
                check = dao.createOrder(this, cart);
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        
        return check;
    }

    // Ham se tra ve -1 neu hoa don khong hop le
    // Se tra ve gia tien nieu hoa don hop le
    public double checkOrder(Cart cart) {
        ProductDAO dao = new ProductDAO();
        double totalMoney = 0;
        try {
            for (ProductDTO product : cart.getCart().values()) {

                int productQuantity = product.getQuantity();
                int currentQuantity = dao.getProductQuantityByID(product.getProductID());

                if (productQuantity > currentQuantity) {
                    this.totalError = "Some products are out of stock";
                    return 0;
                } else {
                    totalMoney = totalMoney + product.getPrice() * productQuantity;

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        }
        return totalMoney;
    }

}
