package com.example.petstore.service;

import com.example.petstore.model.Pet;
import com.example.petstore.model.PetRequest;
import com.example.petstore.model.PetResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PetService {

        public PetResponse createPet(PetRequest request) {

        PetResponse response = new PetResponse();
        response.setTransactionId(UUID.randomUUID().toString());
        response.setDateCreated(LocalDateTime.now());
        response.setName(request.getName());
        response.setStatus(request.getStatus());

        // imprimir en consola
        System.out.println("CREATE PET -> " +
                "TransactionId: " + response.getTransactionId() +
                ", Name: " + response.getName() +
                ", Status: " + response.getStatus());

        return response;
    }

    public Pet getPet(Long id) {
        Pet response = new Pet();
        return response;
    }


}