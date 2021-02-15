package com.dev.spring.service.impl;

import com.dev.spring.dao.CinemaHallDao;
import com.dev.spring.model.CinemaHall;
import com.dev.spring.service.CinemaHallService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    @Autowired
    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public void add(CinemaHall cinemaHall) {
        cinemaHallDao.add(cinemaHall);
    }

    @Override
    public CinemaHall get(Long id) {
        return cinemaHallDao.get(id).orElseThrow(()
                -> new RuntimeException("Can not get cinema hall with id: " + id));
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
