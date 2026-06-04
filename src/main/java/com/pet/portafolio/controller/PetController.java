package com.pet.portafolio.controller;

import com.pet.portafolio.model.Pet;
import com.pet.portafolio.model.PetRequest;
import com.pet.portafolio.model.PetResponse;
import com.pet.portafolio.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/api/pet/{petId}")
    public ResponseEntity<?> getPetById(@PathVariable("petId") Long idPet) {
        try {
            Pet pet = petService.getPetById(idPet);
            return ResponseEntity.ok(pet);
        } catch (HttpClientErrorException.NotFound e) {
            logger.warn("Mascota con id {} no encontrada en Petstore", idPet);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Pet not found", "id", idPet));
        } catch (Exception e) {
            logger.error("Error al obtener mascota con id {}: {}", idPet, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al obtener la mascota", "message", e.getMessage()));
        }
    }

    @PostMapping("/api/pet")
    public ResponseEntity<?> createPet(@RequestBody PetRequest request) {
        try {
            PetResponse response = petService.createPet(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error al crear mascota: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear la mascota", "message", e.getMessage()));
        }
    }
}
