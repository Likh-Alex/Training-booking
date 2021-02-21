package com.dev.spring.controller;

import com.dev.spring.dto.request.CinemaHallRequestDto;
import com.dev.spring.dto.response.CinemaHallResponseDto;
import com.dev.spring.model.CinemaHall;
import com.dev.spring.service.CinemaHallService;
import com.dev.spring.service.mapper.CinemaHallMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper mapper;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper mapper) {
        this.cinemaHallService = cinemaHallService;
        this.mapper = mapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid CinemaHallRequestDto requestDto) {
        CinemaHall cinemaHall = mapper.toEntity(requestDto);
        cinemaHallService.add(cinemaHall);
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService
                .getAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
