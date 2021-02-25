package com.dev.fitbooking.service.mapper;

import com.dev.fitbooking.dto.request.FitnessRoomRequestDto;
import com.dev.fitbooking.dto.response.FitnessRoomResponseDto;
import com.dev.fitbooking.model.FitnessRoom;
import org.springframework.stereotype.Component;

@Component
public class FitnessRoomMapper {
    public FitnessRoomResponseDto toDto(FitnessRoom fitnessRoom) {
        FitnessRoomResponseDto dto = new FitnessRoomResponseDto();
        dto.setId(fitnessRoom.getId());
        dto.setCapacity(fitnessRoom.getCapacity());
        dto.setDescription(fitnessRoom.getDescription());
        return dto;
    }

    public FitnessRoom toEntity(FitnessRoomRequestDto dto) {
        FitnessRoom fitnessRoom = new FitnessRoom();
        fitnessRoom.setCapacity(dto.getCapacity());
        fitnessRoom.setDescription(dto.getDescription());
        return fitnessRoom;
    }
}
