package com.example.petstore.api;

import com.example.petstore.model.Pet;
import com.example.petstore.model.PetRequest;
import com.example.petstore.model.PetResponse;
import com.example.petstore.service.PetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pet/{id}")
    public Pet getPet(@PathVariable("id") Long id) {
        return petService.getPet(id);
    }
    @PostMapping("/pet")
    public PetResponse createPet(@RequestBody PetRequest request) {
        return petService.createPet(request);
    }


}