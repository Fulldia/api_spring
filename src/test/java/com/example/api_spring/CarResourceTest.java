package com.example.api_spring;


import com.example.api_spring.controllers.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc //permet d'importer mockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CarResourceTest {

    //injecte ce mock dans le contexte de l'application
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;


    @Test
    public void whenGetResourceThenContentIsAsExpected() throws Exception {
        // Given
        carRepository.save(new Car(1, "Ferrari"));
        carRepository.save(new Car(2, "Mercedes"));
        // When
        var actualResponse = mockMvc.perform(get("/cars"));
        // Then
        actualResponse.andExpect(status().isOk());
        actualResponse.andExpect(jsonPath("$[0].id").isNumber());
        actualResponse.andExpect(jsonPath("$[0].marque").value("Ferrari"));
    }

    @Test
    public void whenGetResourceById() throws Exception {
        // Given
        carRepository.save(new Car(1, "Ferrari"));
        carRepository.save(new Car(2, "Mercedes"));
        // When
        var actualResponse = mockMvc.perform(get("/cars/ferrari").queryParam("marque", "Ferrari"));
        // Then
        actualResponse.andExpect(status().isOk());
        actualResponse.andExpect(content().json("[{\"id\":1,\"marque\":\"Ferrari\"}]"));
    }

    @Test
    public void whenResourcePostThenContentIsAsExpected() throws Exception {
        // Given

        // When
        var actualResponse = mockMvc.perform(post("/cars")
                .contentType("application/json")
                .content("{\"marque\":\"Alpine\"}"));
        // Then
        actualResponse.andExpect(status().isCreated());
        actualResponse.andExpect(jsonPath("$.marque").value("Alpine"));

        Assertions.assertThat(carRepository.findAll())
                .hasSize(1)
                .first()
                .satisfies(car -> {
                    Assertions.assertThat(car.getId()).isNotNull();
                    Assertions.assertThat(car.getMarque()).isEqualTo("Alpine");
                });
    }
}


