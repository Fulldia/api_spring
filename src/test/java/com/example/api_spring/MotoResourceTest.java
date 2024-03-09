package com.example.api_spring;

import com.example.api_spring.controllers.CarRepository;
import com.example.api_spring.controllers.MotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc //permet d'importer mockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MotoResourceTest {

    //injecte ce mock dans le contexte de l'application
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MotoRepository motoRepository;
    @Test
    public void testGetAllMoto() throws Exception {
        // GIVEN
        motoRepository.save(new Moto(1, "Yamaha" , "MT-07"));
        motoRepository.save(new Moto(2, "Suzuki" , "GSX-8S"));
        // WHEN
        var actualResponse = mockMvc.perform(get("/motos"));
        // THEN
        actualResponse.andExpect(status().isOk());
        actualResponse.andExpect(content().string("[Moto(id=1, marque=Yamaha, modele=MT-07), Moto(id=2, marque=Suzuki, modele=GSX-8S)]"));
    }

    @Test
    public void testGetAllYamahaMoto() throws Exception {
        // GIVEN
        motoRepository.save(new Moto(1, "Yamaha" , "MT-07"));
        motoRepository.save(new Moto(2, "Suzuki" , "GSX-8S"));
        // WHEN
        var actualResponse = mockMvc.perform(get("/motos/yamaha"));
        // THEN
        actualResponse.andExpect(status().isOk());
        actualResponse.andExpect(content().string("[Moto(id=1, marque=Yamaha, modele=MT-07)]"));
    }

}
