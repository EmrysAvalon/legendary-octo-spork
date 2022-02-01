package com.example.dierenarts.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PetRequestDto {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    private String dateOfBirth;

    @NotBlank
    private String ownerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
