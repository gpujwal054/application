package com.example.application.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id", columnDefinition = "serial")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Transient
    private String cartUser;

    private int product_id;


    @Transient
    private Product cartProduct;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCartUser() {
        return cartUser;
    }

    public void setCartUser(String cartUser) {
        this.cartUser = cartUser;
    }

    public Product getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(Product cartProduct) {
        this.cartProduct = cartProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
