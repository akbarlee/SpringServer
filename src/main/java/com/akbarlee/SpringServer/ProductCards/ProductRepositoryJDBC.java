package com.akbarlee.SpringServer.ProductCards;

import com.akbarlee.SpringServer.CarouselCards.CarouselCard;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryJDBC {

    public void saveProductImage(ProductCard productSave);
    public List<ProductCard> getAllProductImages();
    public Optional<ProductCard> getProductImageById(Long p_id);
}
