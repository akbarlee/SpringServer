package com.akbarlee.SpringServer.ProductCards;


import com.akbarlee.SpringServer.CarouselCards.CarouselCard;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductDAO productDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/saveProductImage")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("product_title") String p_title,
                                           @RequestParam("price") String price, @RequestParam("oldPrice") String oldPrice,
                                          Model model, HttpServletRequest request
            , final @RequestParam("Product_image") MultipartFile file) throws IOException {


        String[] array_names = p_title.split(",");
        String[] array_price = price.split(",") ;
        String[] array_oldPrice = oldPrice.split(",") ;
        Date productDate = new Date();

        byte[] imageData = file.getBytes();
        ProductCard productCard = new ProductCard();
        productCard.setProduct_image(imageData);
        productCard.setProduct_title(array_names[0]);
        productCard.setPrice(Double.parseDouble(array_price[0]));
        productCard.setOldPrice(Double.parseDouble(array_oldPrice[0]));
        productCard.setP_createDate(productDate);
        productDAO.saveProductImage(productCard);
        log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
        return new ResponseEntity<>("Product Saved With File - " + imageData, HttpStatus.OK);
    }

    // Show product content's image in home page
    @GetMapping("/product/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ProductCard> productCard)
            throws ServletException, IOException {
        log.info("Product Id :: " + id);
        productCard = productDAO.getProductImageById(id);
        response.setContentType("image/jpeg,image/jpg,image/png,image/gif");
        response.getOutputStream().write(productCard.get().getProduct_image());
        response.getOutputStream().close();
    }


    @RequestMapping("/productEditor")
    String showProduct(Model map) {
        List<ProductCard> productImages = productDAO.getAllProductImages();

        map.addAttribute("productImages", productImages);
        log.info("List table items "+productImages);
        return "dashboardComponents/productEditor.html";
    }
}
