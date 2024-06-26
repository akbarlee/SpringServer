package com.akbarlee.SpringServer.CarouselCards;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarouselRepositoryJDBC {

    public CarouselCard create(CarouselCard image);

    public void saveImage(CarouselCard carouselSave);
    public List<CarouselCard> getAllActiveImages();
    public Optional<CarouselCard> getImageById(Long id);

}
