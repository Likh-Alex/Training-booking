package com.dev.spring.service.impl;

import com.dev.spring.dao.MovieDao;
import com.dev.spring.model.Movie;
import com.dev.spring.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public void add(Movie movie) {
        movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(()
                -> new RuntimeException("Can not get movie with id: " + id));
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
