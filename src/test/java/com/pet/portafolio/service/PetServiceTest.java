package com.pet.portafolio.service;

import com.pet.portafolio.client.PetStoreClient;
import com.pet.portafolio.model.Pet;
import com.pet.portafolio.model.PetRequest;
import com.pet.portafolio.model.PetResponse;
import com.pet.portafolio.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetStoreClient petStoreClient;

    @Mock
    private PetRepository petRepository;

    private PetService petService;

    @BeforeEach
    void setUp() {
        petService = new PetService(petStoreClient, petRepository);
    }

    @Test
    void getPetByIdShouldReturnPetFromExternalApi() {
        Long petId = 1L;
        Pet expectedPet = new Pet(1L, "Fido", "available");
        when(petStoreClient.getPetById(petId)).thenReturn(expectedPet);

        Pet result = petService.getPetById(petId);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Fido", result.getName());
        assertEquals("available", result.getStatus());
        verify(petStoreClient).getPetById(petId);
    }

    @Test
    void createPetShouldDelegateToPetstoreAndReturnResponse() {
        PetRequest request = new PetRequest(1L, "Rex", "sold");
        Pet petFromStore = new Pet(1L, "Rex", "sold");

        when(petStoreClient.createPet(any(Pet.class))).thenReturn(petFromStore);
        when(petRepository.save(any(Pet.class))).thenReturn(petFromStore);

        PetResponse response = petService.createPet(request);

        assertNotNull(response);
        assertNotNull(response.getTransactionId());
        assertNotNull(response.getDateCreated());
        assertEquals("Rex", response.getName());
        assertEquals("sold", response.getStatus());
        verify(petStoreClient).createPet(any(Pet.class));
        verify(petRepository).save(any(Pet.class));
    }
}
