package com.dev.spring.service.impl;

import com.dev.spring.dto.UserResponseDto;
import com.dev.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto convertToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        return dto;
    }
}
