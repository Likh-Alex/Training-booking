package com.dev.spring.service.mapper;

import com.dev.spring.dto.response.UserResponseDto;
import com.dev.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        return dto;
    }
}
