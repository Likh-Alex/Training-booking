package com.dev.spring.controller;

import com.dev.spring.dto.UserResponseDto;
import com.dev.spring.model.User;
import com.dev.spring.service.UserService;
import com.dev.spring.service.impl.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public void get() {
        User mykola = new User();
        mykola.setName("mykola");
        mykola.setEmail("myk@mail.com");
        User petro = new User();
        petro.setName("petro");
        petro.setEmail("pet@mail.com");
        User olek = new User();
        olek.setName("olek");
        olek.setEmail("ole@mail.com");
        User pavel = new User();
        pavel.setName("pavel");
        pavel.setEmail("pav@mail.com");
        for (User user : List.of(mykola, petro, olek, pavel)) {
            userService.add(user);
        }
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.convertToDto(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
