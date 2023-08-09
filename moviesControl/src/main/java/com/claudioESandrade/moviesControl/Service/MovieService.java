package com.claudioESandrade.moviesControl.Service;

import com.claudioESandrade.moviesControl.Entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);
    List<Movie> getAll();
    Optional<Movie> getMovieById(Integer id);
    void update(Integer id, Movie movie);
    void delete(Integer id);

}
