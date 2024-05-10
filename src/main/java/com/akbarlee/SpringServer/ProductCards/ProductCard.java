package com.akbarlee.SpringServer.ProductCards;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;


@Entity
@Table(name = "product")
public class ProductCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_id", nullable = false, unique = true)
    private Long pr_id;

    @Lob
    @Column(name = "product_image", length = Integer.MAX_VALUE, nullable = true)
    byte[] product_image;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "p_create_date", nullable = false)
    Date p_createDate;
    String product_title;
    @Column (name = "Price" )
    private double Price;
    @Column (name = "oldPrice" , nullable = true)
    private double oldPrice;


    public Long getPr_id() {
        return pr_id;
    }

    public void setPr_id(Long pr_id) {
        this.pr_id = pr_id;
    }

    public byte[] getProduct_image() {
        return product_image;
    }

    public void setProduct_image(byte[] product_image) {
        this.product_image = product_image;
    }

    public Date getP_createDate() {
        return p_createDate;
    }

    public void setP_createDate(Date p_createDate) {
        this.p_createDate = p_createDate;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }







    @Override
    public String toString() {
        return "ProductCard{" +
                "pr_id=" + pr_id +
                ", product_image=" + Arrays.toString(product_image) +
                ", p_createDate=" + p_createDate +
                ", product_title='" + product_title + '\'' +
                ", Price=" + Price +
                ", oldPrice=" + oldPrice +
                '}';
    }
}
