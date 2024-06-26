package com.example.apirestbooksexample.controller;

import com.example.apirestbooksexample.entity.Genre;
import com.example.apirestbooksexample.exception.CreateEntityException;
import com.example.apirestbooksexample.exception.UpdateEntityException;
import com.example.apirestbooksexample.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/genre")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Genre> findAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Genre findGenreById(@PathVariable UUID id) {
        return genreService.findGenreById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre saveGenre(@RequestBody Genre genre)
            throws CreateEntityException {
        return genreService.saveGenre(genre);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Genre updateGenre(
            @PathVariable UUID id,
            @RequestBody Genre genre) throws UpdateEntityException {
        return genreService.updateGenre(id, genre);
    }

}
