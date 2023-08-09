package com.claudioESandrade.moviesControl.Controller;

import com.claudioESandrade.moviesControl.Entity.Movie;
import com.claudioESandrade.moviesControl.Service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Movies")
public class MovieController {
    @Autowired
    private MovieServiceImpl movieService;

    @PostMapping(value = "/save")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.save(movie));
    }
    @GetMapping(value = "/get/All")
    public ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok().body(movieService.getAll() );
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.getMovieById( id ) );
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        movieService.update( id, movie);
        return ResponseEntity.ok( ).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.delete( id );
        return ResponseEntity.ok().build();
    }
}
