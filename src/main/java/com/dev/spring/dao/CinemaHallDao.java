package com.dev.spring.dao;

import com.dev.spring.model.CinemaHall;
import java.util.List;
import java.util.Optional;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    Optional<CinemaHall> get(Long id);

    List<CinemaHall> getAll();
}
