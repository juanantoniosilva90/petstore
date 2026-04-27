package com.example.petstore.client;

import com.example.petstore.model.Pet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PetClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://petstore.swagger.io/v2/pet/";

    public Pet getPetById(Long id) {
        return restTemplate.getForObject(BASE_URL + id, Pet.class);
    }
}