package com.example.api_spring.controllers;

import com.example.api_spring.Moto;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface MotoRepository extends ListCrudRepository<Moto, Integer> {
    List<Moto> findByMarque(String marque);
    List<Moto> findById(int id);
}