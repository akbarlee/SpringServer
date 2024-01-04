package com.akbarlee.SpringServer.CarouselCards;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarouselDAO implements CarouselRepositoryJDBC {

     @Autowired
     private CarouselRepository carouselRepository;


    @Override
    public CarouselCard create(CarouselCard image) {
        return null;
    }

    @Override
    public void saveImage(CarouselCard carouselSave) {
        carouselRepository.save(carouselSave);
    }

    @Override
    public List<CarouselCard> getAllActiveImages() {
        return carouselRepository.findAll();
    }

    @Override
    public Optional<CarouselCard> getImageById(Long id) {
        return carouselRepository.findById(id);
    }
}
