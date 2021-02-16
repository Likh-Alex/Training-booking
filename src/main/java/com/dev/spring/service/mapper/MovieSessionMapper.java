package com.dev.spring.service.mapper;

import com.dev.spring.dto.request.MovieSessionRequestDto;
import com.dev.spring.dto.response.MovieSessionResponseDto;
import com.dev.spring.model.MovieSession;
import com.dev.spring.service.CinemaHallService;
import com.dev.spring.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    @Autowired
    public MovieSessionMapper(CinemaHallService cinemaHallService,
                              MovieService movieService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    public MovieSession toEntity(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.get(dto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(dto.getCinemaHallId()));
        LocalDateTime parsedDateTime = LocalDateTime.parse(dto.getShowTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        movieSession.setShowTime(parsedDateTime);
        return movieSession;
    }

    public MovieSessionResponseDto toDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setId(movieSession.getId());
        dto.setShowTime(movieSession.getShowTime().toString());

        dto.setMovieId(movieSession.getMovie().getId());
        dto.setMovieTitle(movieSession.getMovie().getTitle());

        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setCinemaHallCapacity(movieSession.getCinemaHall().getCapacity());
        dto.setCinemaHallDescription(movieSession.getCinemaHall().getDescription());

        return dto;
    }
}
