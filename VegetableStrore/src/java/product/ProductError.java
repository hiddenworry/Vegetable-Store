/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class ProductError {

    private String productIDError;
    private String productNameError;
    private String productPriceError;
    private String productQuantityError;
    private String productCateIDError;
    private String productImportDateError;
    private String productUsingDateError;
    // invalid date Error to check whether the importDate is after usingDate
    private String InvalidDateError;
    private String line;
    private String imageError;

    public ProductError(String line, String productIDError, String productNameError, String productPrice, String productQuantity, String productCateIDError, String productImportDateError, String productUsingDateError, String imageError) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.productPriceError = productPrice;
        this.productQuantityError = productQuantity;
        this.productCateIDError = productCateIDError;
        this.productImportDateError = productImportDateError;
        this.productUsingDateError = productUsingDateError;
        this.imageError = imageError; 
        this.line = line;
    }

    public ProductError() {
        this.productIDError = "";
        this.productNameError = "";
        this.productPriceError = "";
        this.productQuantityError = "";
        this.productCateIDError = "";
        this.productImportDateError = "";
        this.productUsingDateError = "";
        this.InvalidDateError = "";
        this.line = "";
        this.imageError = "";
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getProductPrice() {
        return productPriceError;
    }

    public void setProductPriceError(String productPrice) {
        this.productPriceError = productPrice;
    }

    public String getProductQuantityError() {
        return productQuantityError;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantityError = productQuantity;
    }

    public String getProductCateIDError() {
        return productCateIDError;
    }

    public void setProductCateIDError(String productCateIDError) {
        this.productCateIDError = productCateIDError;
    }

    public String getProductImportDateError() {
        return productImportDateError;
    }

    public void setProductImportDateError(String productImportDateError) {
        this.productImportDateError = productImportDateError;
    }

    public String getProductUsingDateError() {
        return productUsingDateError;
    }

    public String getProductPriceError() {
        return productPriceError;
    }

    public void setProductUsingDateError(String productUsingDateError) {
        this.productUsingDateError = productUsingDateError;
    }

    public boolean isValidDateError(String importDateParams, String usingDateParams) {
        // Check import date after using date
        try {
            Date importDate = Date.valueOf(importDateParams);
            Date usingDate = Date.valueOf(usingDateParams);
            return importDate.before(usingDate);
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isValidDateformat(String date) {

        try {
            return Date.valueOf(date) != null;
        } catch (Exception e) {
            return false;

        }

    }

    public String getInvalidDateError() {
        return InvalidDateError;
    }

    public void setInvalidDateError(String InvalidDateError) {
        this.InvalidDateError = InvalidDateError;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }
    
}
