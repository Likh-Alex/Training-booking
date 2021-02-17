package com.dev.spring.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CinemaHallRequestDto {
    @NotEmpty
    @Size(min = 10, max = 100)
    private int capacity;
    private String description;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
