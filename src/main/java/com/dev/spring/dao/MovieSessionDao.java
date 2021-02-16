package com.dev.spring.dao;

import com.dev.spring.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> getAll();

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession update(MovieSession movieSession);

    boolean delete(Long id);

    Optional<MovieSession> get(Long id);
}
