package com.dev.spring.service;

import com.dev.spring.model.CinemaHall;
import java.util.List;

public interface CinemaHallService {
    void add(CinemaHall cinemaHall);

    CinemaHall get(Long id);

    List<CinemaHall> getAll();
}
