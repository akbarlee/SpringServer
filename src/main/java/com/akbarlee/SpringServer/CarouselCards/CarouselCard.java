package com.akbarlee.SpringServer.CarouselCards;


import jakarta.persistence.*;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "carousel")
public class CarouselCard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

   @Lob
   @Column(name = "carousel_image", length = Integer.MAX_VALUE, nullable = true)
    byte[] carousel_image;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
  Date createDate;
   String carousel_title;
    String carousel_descr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getCarousel_image() {
        return carousel_image;
    }

    public void setCarousel_image(byte[] carousel_image) {
        this.carousel_image = carousel_image;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCarousel_title() {
        return carousel_title;
    }

    public void setCarousel_title(String carousel_title) {
        this.carousel_title = carousel_title;
    }

    public String getCarousel_descr() {
        return carousel_descr;
    }

    public void setCarousel_descr(String carousel_descr) {
        this.carousel_descr = carousel_descr;
    }
}
