package com.example.apirestbooksexample.service;

import com.example.apirestbooksexample.entity.Genre;
import com.example.apirestbooksexample.exception.CreateEntityException;
import com.example.apirestbooksexample.exception.UpdateEntityException;

import java.util.List;
import java.util.UUID;

public interface GenreService {
    List<Genre> findAllGenres();
    Genre findGenreById(UUID idGenre);
    Genre saveGenre(Genre genre) throws CreateEntityException;
    Genre updateGenre(UUID idGenre, Genre genre) throws UpdateEntityException;
}
