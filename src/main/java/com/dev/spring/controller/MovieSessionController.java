package com.dev.spring.controller;

import com.dev.spring.dto.request.MovieSessionRequestDto;
import com.dev.spring.dto.response.MovieSessionResponseDto;
import com.dev.spring.model.MovieSession;
import com.dev.spring.service.MovieSessionService;
import com.dev.spring.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionMapper mapper;
    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionMapper mapper,
                                  MovieSessionService service) {
        this.mapper = mapper;
        movieSessionService = service;
    }

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto requestDto) {
        MovieSession movieSession = mapper.toEntity(requestDto);
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllByDate(
            @RequestParam Long id,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        List<MovieSession> availableSessions = movieSessionService.findAvailableSessions(id, date);
        return availableSessions.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<MovieSessionResponseDto> getAll() {
        return movieSessionService.getAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody MovieSessionRequestDto requestDto) {
        MovieSession movieSession = mapper.toEntity(requestDto);
        movieSession.setId(id);
        movieSessionService.update(movieSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieSessionService.delete(id);
    }
}
