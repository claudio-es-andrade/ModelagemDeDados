package com.claudioESandrade.moviesControl.Service;

import com.claudioESandrade.moviesControl.Entity.Movie;
import com.claudioESandrade.moviesControl.Entity.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

   // @Query(value = "select M.id, M.type, M.name, M.totalEp, M.atualEp, M.lastView from Movie M ")
    @Override
    public Movie save(Movie movie) {
        return movieRepository.save( movie ) ;
    }

    //@Query(value = "select M.id, M.type, M.name, M.totalEp, M.atualEp, M.lastView from Movie M ")
    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    //@Query(value = "select M.id, M.type, M.name, M.totalEp, M.atualEp, M.lastView from Movie M  where id = ?")
    @Override
    public Optional<Movie> getMovieById(Integer id) {
        Optional<Movie> movie=movieRepository.findById(id);
        Movie movieFull=null;
        if(movie.isPresent()) {
            movieFull=movie.get();
        }else {
            throw new RuntimeException("Movie wasn't found"+ id);
        }
        return Optional.of(movieFull );
    }

    //@Query(value = "select M.id, M.type, M.name, M.totalEp, M.atualEp, M.lastView from Movie M where id = ?")
    @Override
    public void update(Integer id, Movie movie) {
        Optional<Movie> movieBd = movieRepository.findById(id);
        if (movieBd.isPresent())
            save(movie);
        else
            throw new RuntimeException("Movie wasn't found"+ id);

    }


    //@Query(value = "select M.id, M.type, M.name, M.totalEp, M.atualEp, M.lastView from Movie M where id = ? ")
    @Override
    public void delete(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);
        Movie movieFull = null;
        if(movie.isPresent()) {
            movieFull=movie.get();
            movieRepository.delete( movieFull );
        }else {
            throw new RuntimeException("Movie wasn't found"+ id);
        }
    }
}


