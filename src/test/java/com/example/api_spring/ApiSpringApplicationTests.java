package com.example.api_spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //permet d'importer mockMvc
class ApiSpringApplicationTests {

    //injecte ce mock dans le contexte de l'application
    @Autowired
    private MockMvc mockMvc;

    //permet de tester si l'application se lance correctement
    @Test
    void contextLoads() {
    }

    @Test
    void whenHelloApiCalledThenContentIsHelloWorld() throws Exception {
        // Given

        // When
        var actualResponse = mockMvc.perform(get("/hello").queryParam("name", "bg"));
        // Then
        actualResponse.andExpect(content().string("Hello bg !"));
        actualResponse.andExpect(status().isOk());
    }

    @Test
    void whenHelloApiCalledWithCamThenContentFailed() throws Exception {
        // Given

        // When
        var actualResponse = mockMvc.perform(get("/hello").queryParam("name", "Cam"));
        // Then
        actualResponse.andExpect(status().isBadRequest());
    }

    @Test
    void whenHelloApiCalledObjectThenContentIsJson() throws Exception {
        // Given

        // When
        var actualResponse = mockMvc.perform(get("/hello")
                .queryParam("name", "Didier")
                .queryParam("langue", "en"));
        // Then
        actualResponse.andExpect(content().json("{\"langue\":\"en\", \"message\":\"Hello Didier !\"}"));
        actualResponse.andExpect(status().isOk());
    }
}

