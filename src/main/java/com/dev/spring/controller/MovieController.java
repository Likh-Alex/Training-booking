package com.dev.spring.controller;

import com.dev.spring.dto.request.MovieRequestDto;
import com.dev.spring.dto.response.MovieResponseDto;
import com.dev.spring.model.Movie;
import com.dev.spring.service.MovieService;
import com.dev.spring.service.mapper.MovieMapper;
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
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper mapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper mapper) {
        this.movieService = movieService;
        this.mapper = mapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid MovieRequestDto requestDto) {
        Movie movie = mapper.toEntity(requestDto);
        movieService.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
