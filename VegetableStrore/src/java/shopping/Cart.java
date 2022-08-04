/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.util.HashMap;
import java.util.Map;
import product.ProductDTO;

/**
 *
 * @author ADMIN
 */
public class Cart {

    private Map<String, ProductDTO> cart;

    public Cart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Cart() {
        this.cart = new HashMap<>();
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public boolean addToCart(ProductDTO product) {
        boolean check = false;
        if (this.cart == null) {
            this.cart = new HashMap<>();

        }
        if (cart.containsKey(product.getProductID())) {
            int currentQuantity = cart.get(product.getProductID()).getQuantity() + 1;
            product.setQuantity(currentQuantity);
        }
        this.cart.put(product.getProductID(), product);
        check = true;
        return check;

    }

    public boolean removeCart(String productID) {
        boolean check = false;
        if (this.cart != null) {
            if (this.cart.containsKey(productID)) {
                this.cart.remove(productID);
                check = true;
            }

        }
        return check;

    }

    public boolean editCart(String productID, ProductDTO replaceProduct) {
        boolean check = false;
        if (this.cart != null) {
            if (this.cart.containsKey(productID)) {
                this.cart.replace(productID, replaceProduct);
                check = true;
            }

        }
        return check;

    }

    public double getCartVal() {
        double totalMoney = 0;
        try {
            for (ProductDTO product : this.cart.values()) {
                totalMoney = totalMoney + product.getPrice() * product.getQuantity();

            }
        } catch (Exception e) {

        }
        return totalMoney;
    }

}
