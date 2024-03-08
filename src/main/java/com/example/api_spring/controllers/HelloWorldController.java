package com.example.api_spring.controllers;

import com.example.api_spring.Salutations;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public Salutations HelloWorld(@RequestParam String name, @Parameter(description = "coucou", example = "exemple") @RequestParam(defaultValue = "fr") String langue) {
        if (Objects.equals(name, "Clem")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cam is not allowed");
        }
        if (Objects.equals(langue, "es")) {
            return new Salutations(langue, "Holà " + name + " !");
        }
        if (Objects.equals(langue, "fr")) {
            return new Salutations(langue, "Bonjour " + name + " !");
        }
        else {
            return new Salutations(langue, "Hello " + name + " !");
        }
    }

}
