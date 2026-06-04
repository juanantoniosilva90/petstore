package com.pet.portafolio.client;

import com.pet.portafolio.model.Pet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PetStoreClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public PetStoreClient(RestTemplate restTemplate, @Value("${petstore.api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    public Pet getPetById(Long petId) {
        String url = apiUrl + "/pet/" + petId;
        return restTemplate.getForObject(url, Pet.class);
    }

    public Pet createPet(Pet pet) {
        String url = apiUrl + "/pet";
        HttpEntity<Pet> request = new HttpEntity<>(pet);
        return restTemplate.postForObject(url, request, Pet.class);
    }
}
