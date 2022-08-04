/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import order.Order;
import shopping.Cart;
import utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    private static final String GET_PRODUCT_CATE_LIST = "SELECT categoryID, categoryName from tblCategory";
    private static final String SEARCH_PRODUCT_ADMIN = "SELECT productID, productName, price, quantity, categoryID, importDate, usingDate, image from tblProduct WHERE productName like ?";
    private static final String SEARCH_PRODUCT_USER = "SELECT productID, productName, price, quantity, categoryID, importDate, usingDate, image from tblProduct WHERE productName like ? and usingDate>?";
    private static final String DELETE_PRODUCT = "DELETE tblProduct WHERE productID=?";
    private static final String UPDATE_PRODUCT = "UPDATE tblProduct SET productName=?, price=?, quantity=?, importDate=?, usingDate=?, image=? WHERE productID=?";
    private static final String INSERT_PRODUCT = "INSERT INTO tblProduct (productID, productName, price, quantity, categoryID, importDate, usingDate, image) VALUES (?, ? , ? , ? , ?, ?, ?, ? )";
    private static final String CHECK_DUPLICATED = "SELECT productID FROM tblProduct where productID =?";
    private static final String SEARCH_PRODUCT_BY_ID = "SELECT productName, price from tblProduct WHERE productID = ?";
    private static final String UPDATE_PRODUCT_QUATITY_BY_ID = "UPDATE tblProduct SET quantity=quantity-? from tblProduct WHERE productID = ?";
    private static final String GET_PRODUCT_QUANTITY_BY_ID = "SELECT quantity from tblProduct where productID =?";
    private static final String CREATE_ORDER = "INSERT INTO tblOrder (userID, total, orderDate ) VALUES(?,?,?)";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO tblOrderDetail (orderID, productID, price, quantity) values (?,?,?,?)";

    public boolean checkDuplicated(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(CHECK_DUPLICATED);
                ps.setString(1, productID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    check = true;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;

    }
// Search all product in the database, use for admin to control the product quality

    public List<ProductDTO> searchProductForAdmin(String searchStr) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SEARCH_PRODUCT_ADMIN);
                ps.setString(1, "%" + searchStr + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getNString("productName");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    String imageLink = rs.getString("image");
                    ProductDTO product = new ProductDTO(productID, productName, price, quantity, categoryID, importDate, usingDate, imageLink);
                    list.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return list;
    }

    // Search and return the list of product that has importDay < usingDate
    public List<ProductDTO> searchProductForUser(String searchStr) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        Date today = new Date(System.currentTimeMillis()); // get current date

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SEARCH_PRODUCT_USER);
                ps.setString(1, "%" + searchStr + "%");
                ps.setDate(2, today);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getNString("productName");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    String imageLink = rs.getString("image");
                    ProductDTO product = new ProductDTO(productID, productName, price, quantity, categoryID, importDate, usingDate, imageLink);
                    list.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return list;
    }

    // Search Product by ID for Sales
    public ProductDTO searchProductForSales(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDTO product = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SEARCH_PRODUCT_BY_ID);
                ps.setString(1, productID);
                rs = ps.executeQuery();
                if (rs.next()) {

                    String productName = rs.getNString("productName");
                    double price = rs.getDouble("price");
                    product = new ProductDTO();
                    product.setProductID(productID);
                    product.setProductName(productName);
                    product.setPrice(price);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return product;
    }

    //Delete
    public boolean delete(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE_PRODUCT);
                ps.setString(1, productID);
                check = ps.executeUpdate() > 0 ? true : false;

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    //Update
    public boolean update(ProductDTO product) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_PRODUCT);
                ps.setString(1, product.getProductName());
                ps.setDouble(2, product.getPrice());
                ps.setInt(3, product.getQuantity());
                //Convert Date to String
                Date importDate = Date.valueOf(product.getImportDate());
                Date usingDate = Date.valueOf(product.getUsingDate());
                ps.setDate(4, importDate);
                ps.setDate(5, usingDate);
                ps.setString(6, product.getImageLink());
                ps.setString(7, product.getProductID());

                check = ps.executeUpdate() > 0 ? true : false;

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    //Insert new product
    public boolean insert(ProductDTO product) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_PRODUCT);
                ps.setString(1, product.getProductID());
                ps.setString(2, product.getProductName());
                ps.setDouble(3, product.getPrice());
                ps.setInt(4, product.getQuantity());
                ps.setString(5, product.getCategoryID());

                //Convert Date to String
                Date importDate = Date.valueOf(product.getImportDate());
                Date usingDate = Date.valueOf(product.getUsingDate());
                ps.setDate(6, importDate);
                ps.setDate(7, usingDate);
                ps.setString(8, product.getImageLink());
                check = ps.executeUpdate() > 0 ? true : false;

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public List<String> getProCateList() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> cateList = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_PRODUCT_CATE_LIST);
                rs = ps.executeQuery();
                while (rs.next()) {
                    cateList.add(rs.getString("CategoryID") + "-" + rs.getNString("CategoryName"));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                rs.close();
            }
            if ((ps != null)) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return cateList;
    }

    public boolean createOrder(Order order, Cart cart) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = true;
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp time = new Timestamp(cal.getTimeInMillis());
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            if (conn != null) {
                ps = conn.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, order.getUserID());
                ps.setDouble(2, order.getTotal());
                ps.setTimestamp(3, time);

                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    String id = rs.getString(1);
                    System.out.println(id);
                    order.setOrderID(Integer.parseInt(id));

                } else {
                    check = false;
                }

                // insert into order detail nếu có lỗi rollback
                for (ProductDTO product : cart.getCart().values()) {
                    // Vong lap insert toan bo cac san pham trong cart vao order detail
                    // setAutoCommit la flase de roolback khi gap loi
                    ps = conn.prepareStatement(CREATE_ORDER_DETAIL);
                    ps.setInt(1, order.getOrderID());
                    ps.setString(2, product.getProductID());
                    ps.setDouble(3, product.getPrice());
                    ps.setInt(4, product.getQuantity());
                    int row = ps.executeUpdate();
                    if (row == 0) {
                        check = false; // insert bị lỗi
                    }
                    // update số lượng sản phẩm trong database
                    check = updateProductQuantityByID(product.getProductID(), product.getQuantity());

                }
                if (!check) {
                    conn.rollback();
                } else {
                    conn.commit();
                }

            }

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            e.printStackTrace();
            check = false;
          
           

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;

    }

   

    public int getProductQuantityByID(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int quantity = 0;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_PRODUCT_QUANTITY_BY_ID);
                ps.setString(1, productID);
                rs = ps.executeQuery();
                if (rs.next()) {

                    quantity = rs.getInt("quantity");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return quantity;
    }
    // Update product quantity by product ID  

    public boolean updateProductQuantityByID(String productID, int quantity) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean check = false;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_PRODUCT_QUATITY_BY_ID);
                ps.setInt(1, quantity);
                ps.setString(2, productID);
                check = ps.executeUpdate() > 0 ? true : false;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }
}
