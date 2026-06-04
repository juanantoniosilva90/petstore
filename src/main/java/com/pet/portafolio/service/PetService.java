package com.pet.portafolio.service;

import com.pet.portafolio.client.PetStoreClient;
import com.pet.portafolio.model.Pet;
import com.pet.portafolio.model.PetRequest;
import com.pet.portafolio.model.PetResponse;
import com.pet.portafolio.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PetService {

    private static final Logger logger = LoggerFactory.getLogger(PetService.class);

    private final PetStoreClient petStoreClient;
    private final PetRepository petRepository;

    public PetService(PetStoreClient petStoreClient, PetRepository petRepository) {
        this.petStoreClient = petStoreClient;
        this.petRepository = petRepository;
    }

    public Pet getPetById(Long petId) {
        logger.info("Obteniendo mascota con id: {}", petId);
        Pet pet = petStoreClient.getPetById(petId);
        logger.info("Mascota obtenida de Petstore: id={}, name={}, status={}", pet.getId(), pet.getName(), pet.getStatus());
        return pet;
    }

    public PetResponse createPet(PetRequest request) {
        logger.info("Creando mascota en Petstore: id={}, name={}, status={}", request.getId(), request.getName(), request.getStatus());

        Pet pet = new Pet(request.getId(), request.getName(), request.getStatus());
        Pet createdPet = petStoreClient.createPet(pet);

        petRepository.save(createdPet);

        PetResponse response = new PetResponse(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                createdPet.getStatus(),
                createdPet.getName()
        );

        logger.info("Mascota creada en Petstore: transactionId={}, dateCreated={}, status={}, name={}",
                response.getTransactionId(), response.getDateCreated(), response.getStatus(), response.getName());

        return response;
    }
}
