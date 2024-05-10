package com.akbarlee.SpringServer.ProductCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDAO implements ProductRepositoryJDBC{

     @Autowired
    ProductRepository productRepository;

    @Override
    public void saveProductImage(ProductCard productSave) {
        productRepository.save(productSave);
    }

    @Override
    public List<ProductCard> getAllProductImages() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductCard> getProductImageById(Long p_id) {
        return productRepository.findById(p_id);
    }
}
