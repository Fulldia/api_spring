package com.example.api_spring.controllers;

import com.example.api_spring.Moto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class MotoController {
    private final MotoRepository motoRepository;
    @GetMapping("/motos")
    public String getMotos(@RequestParam(required = false) Integer id ) {
        if (id != null) {
            return motoRepository.findById(id).toString();
        }
        else {
            return motoRepository.findAll().toString();
        }
    }

    @GetMapping("/motos/yamaha")
    public String getYamahaMotos() {
        return motoRepository.findByMarque("Yamaha").toString();
    }

}
