package com.example.dierenarts.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OwnerRequestDto {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
