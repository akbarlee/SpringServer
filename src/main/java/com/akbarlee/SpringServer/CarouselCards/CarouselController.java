package com.akbarlee.SpringServer.CarouselCards;


import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class CarouselController {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    CarouselDAO carouselDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());



    @PostMapping("/saveImageDetails")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("carousel_title") String title,
                                                         @RequestParam("description") String description, Model model, HttpServletRequest request
            , final @RequestParam("image") MultipartFile file) {
        try {
            String uploadDirectoryz = System.getProperty("user.dir") + uploadFolder;

            log.info("uploadDirectoryz:: " + uploadDirectoryz);

            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectoryz, fileName).toString();
            log.info("FileName: " + file.getOriginalFilename());
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            String[] names = title.split(",");
            String[] descriptions = description.split(",");
            Date createDate = new Date();
            log.info("Title: " + names[0] + " " + filePath);
            log.info("description: " + descriptions[0]);

            try {
                File dir = new File(uploadDirectoryz);
                if (!dir.exists()) {
                    log.info("Folder Created");
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                log.info("in catch");
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            CarouselCard imageGallery = new CarouselCard();
            imageGallery.setCarousel_title(names[0]);
            imageGallery.setCarousel_image(imageData);
            imageGallery.setCarousel_descr(descriptions[0]);
            imageGallery.setCreateDate(createDate);
            carouselDAO.saveImage(imageGallery);
            log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/image/imageDetails")
    String showProductDetails(@RequestParam("id") Long id, Optional<CarouselCard> imageGallery, Model model) {
        try {
            log.info("Id :: " + id);
            if (id != 0) {
                imageGallery = carouselDAO.getImageById(id);

                log.info("products :: " + imageGallery);
                if (imageGallery.isPresent()) {
                    model.addAttribute("id", imageGallery.get().getId());
                    model.addAttribute("description", imageGallery.get().getCarousel_descr());
                    model.addAttribute("name", imageGallery.get().getCarousel_title());
                    return "index";
                }
                return "redirect:/";
            }
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }


    //  Table data
    @RequestMapping("/itemEditor")
    String show(Model map) {
        List<CarouselCard> images = carouselDAO.getAllActiveImages();
        map.addAttribute("images", images);
        log.info("List table items "+images);
        return "dashboardComponents/itemEditor";
    }

    // Carousel content iterator
    @RequestMapping("/")
    String showCarousel(Model carouselModel) {
        List<CarouselCard> carousel = carouselDAO.getAllActiveImages();
        carouselModel.addAttribute("carousel", carousel);
        log.info("List carousel items "+carousel);
        return "index";
    }

    // Show carousel content's image in home page
    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<CarouselCard> carouselCard)
            throws ServletException, IOException {
        log.info("Id :: " + id);
        carouselCard = carouselDAO.getImageById(id);
        response.setContentType("image/jpeg,image/jpg,image/png,image/gif");
        response.getOutputStream().write(carouselCard.get().getCarousel_image());
        response.getOutputStream().close();
    }



}