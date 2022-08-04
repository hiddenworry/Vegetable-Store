/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

/**
 *
 * @author ADMIN
 */
public class OrderError {

    protected String userIdError;
    protected String orderIDError;
    protected String totalError;
    protected String dateError;
    protected String quantityError;

    public OrderError(String userIdError, String orderIDError, String totalError, String dateError, String quantityError) {
        this.userIdError = userIdError;
        this.orderIDError = orderIDError;
        this.totalError = totalError;
        this.dateError = dateError;
        this.quantityError = quantityError;
    }

    public OrderError() {
        this.userIdError = "";
        this.orderIDError = "";
        this.totalError = "";
        this.dateError = "";
        this.quantityError = "";
    }

    public String getUserIdError() {
        return userIdError;
    }

    public void setUserIdError(String userIdError) {
        this.userIdError = userIdError;
    }

    public String getOrderIDError() {
        return orderIDError;
    }

    public void setOrderIDError(String orderIDError) {
        this.orderIDError = orderIDError;
    }

    public String getTotalError() {
        return totalError;
    }

    public void setTotalError(String totalError) {
        this.totalError = totalError;
    }

    public String getDateError() {
        return dateError;
    }

    public void setDateError(String dateError) {
        this.dateError = dateError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }
    public String getError(){
        return  this.getDateError() + this.getOrderIDError() + this.getQuantityError() + this.getTotalError() + this.getUserIdError();
        
    }
}
