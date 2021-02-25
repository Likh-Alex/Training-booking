package com.dev.fitbooking.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WorkoutRequestDto {
    @NotNull
    @Size(min = 3, max = 100)
    private String type;
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
