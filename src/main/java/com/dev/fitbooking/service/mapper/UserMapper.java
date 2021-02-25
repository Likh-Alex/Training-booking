package com.dev.fitbooking.service.mapper;

import com.dev.fitbooking.dto.response.UserResponseDto;
import com.dev.fitbooking.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        dto.setId(user.getId());
        return dto;
    }
}
