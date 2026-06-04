package com.pet.portafolio.model;

import java.time.LocalDateTime;

public class PetResponse {

    private String transactionId;
    private LocalDateTime dateCreated;
    private String status;
    private String name;

    public PetResponse() {}

    public PetResponse(String transactionId, LocalDateTime dateCreated, String status, String name) {
        this.transactionId = transactionId;
        this.dateCreated = dateCreated;
        this.status = status;
        this.name = name;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
