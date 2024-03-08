package com.example.api_spring.controllers;

import com.example.api_spring.Car;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarController {

    private final CarRepository carRepository;
    @GetMapping("/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping("/cars/ferrari")
    public List<Car> getFerrariCars() {
        return carRepository.findByMarque("Ferrari");
    }

    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }
}
