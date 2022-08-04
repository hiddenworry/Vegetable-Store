/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

/**
 *
 * @author ADMIN
 */
public class ProductDTO {

    private String productID;
    private String productName;
    private double price;
    private int quantity;
    private String categoryID;
    private String importDate;
    private String usingDate;
    private String imageLink;

    

    public ProductDTO(String productID, String productName, double price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

  
    // Contructor for search
   

   
    
   
    public ProductDTO() {
        this.productID = "";
        this.productName = "";
        this.imageLink = "";
        this.price = 0;
        this.quantity = 0;
        this.categoryID = "";
        this.importDate = "";
        this.usingDate = "";
        this.imageLink = "";
    }
// Contructor for insert

    public ProductDTO(String productID, String productName, double price, int quantity, String categoryID, String importDate, String usingDate, String imageLink) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.importDate = importDate;
        this.usingDate = usingDate;
        this.imageLink = imageLink;
    }
// contructor for update
    public ProductDTO(String productID, String productName, double price, int quantity, String importDate, String usingDate, String imageLink) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.importDate = importDate;
        this.usingDate = usingDate;
        this.imageLink = imageLink;
    }

    public ProductDTO(String productID, String productName, double price, int quantity, String importDate, String usingDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ProductDTO(String productID, String productName, double price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

  
 

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(String usingDate) {
        this.usingDate = usingDate;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    
  
   
   
}


