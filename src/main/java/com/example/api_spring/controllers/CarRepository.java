package com.example.api_spring.controllers;

import com.example.api_spring.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CarRepository extends ListCrudRepository<Car, Integer> {
    List<Car> findByMarque(String marque);
}
