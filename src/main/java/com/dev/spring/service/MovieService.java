package com.dev.spring.service;

import com.dev.spring.model.Movie;
import java.util.List;

public interface MovieService {
    void add(Movie movie);

    Movie get(Long id);

    List<Movie> getAll();
}
