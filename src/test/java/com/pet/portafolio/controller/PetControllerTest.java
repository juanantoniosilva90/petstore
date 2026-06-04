package com.pet.portafolio.controller;

import com.pet.portafolio.model.Pet;
import com.pet.portafolio.model.PetRequest;
import com.pet.portafolio.model.PetResponse;
import com.pet.portafolio.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    void getPetByIdShouldReturn200() throws Exception {
        Pet pet = new Pet(1L, "Fido", "available");
        when(petService.getPetById(1L)).thenReturn(pet);

        mockMvc.perform(get("/api/pet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Fido"))
                .andExpect(jsonPath("$.status").value("available"));
    }

    @Test
    void createPetShouldReturn201() throws Exception {
        PetResponse response = new PetResponse(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                "available",
                "Rex"
        );
        when(petService.createPet(any(PetRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Rex\",\"status\":\"available\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transactionId").isNotEmpty())
                .andExpect(jsonPath("$.dateCreated").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Rex"))
                .andExpect(jsonPath("$.status").value("available"));
    }
}
