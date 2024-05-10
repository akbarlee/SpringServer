package com.akbarlee.SpringServer.Cart;

import com.akbarlee.SpringServer.Customer.User;
import com.akbarlee.SpringServer.ProductCards.ProductCard;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cart_id;

     @ManyToOne
     @JoinColumn(name = "product_id")
    private ProductCard productCard;

    @ManyToOne
    @JoinColumn(name = "user_id")
     private User user;

    private int quantity;


    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public ProductCard getProductCard() {
        return productCard;
    }

    public void setProductCard(ProductCard productCard) {
        this.productCard = productCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cart_id=" + cart_id +
                ", productCard=" + productCard +
                ", user=" + user +
                ", quantity=" + quantity +
                '}';
    }
}
