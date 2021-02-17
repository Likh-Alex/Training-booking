package com.dev.spring.service;

import com.dev.spring.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    void add(MovieSession movieSession);

    List<MovieSession> getAll();

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    void update(MovieSession movieSession);

    void delete(Long id);

    MovieSession get(Long id);
}
