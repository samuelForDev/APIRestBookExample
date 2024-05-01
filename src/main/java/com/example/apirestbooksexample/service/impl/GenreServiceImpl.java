package com.example.apirestbooksexample.service.impl;

import com.example.apirestbooksexample.entity.Genre;
import com.example.apirestbooksexample.exception.CreateEntityException;
import com.example.apirestbooksexample.exception.UpdateEntityException;
import com.example.apirestbooksexample.repository.GenreRepository;
import com.example.apirestbooksexample.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres;
    }

    @Override
    public Genre findGenreById(UUID idGenre) {
        Optional<Genre> genreOptional = genreRepository.findById(idGenre);
        return genreOptional.orElse(null);
    }

    @Override
    public Genre saveGenre(Genre genre) throws CreateEntityException {
        try {
            return genreRepository.save(Genre.builder()
                    .idGenre(UUID.randomUUID())
                    .genreName(genre.getGenreName())
                    .build());

        } catch (Exception e) {
            throw new CreateEntityException("Failed to create genre: " + e.getMessage());
        }
    }

    @Override
    public Genre updateGenre(UUID idGenre, Genre genre) throws UpdateEntityException {
        Optional<Genre> oldGenreOptional = genreRepository.findById(idGenre);
        if (oldGenreOptional.isPresent()) {
            Genre oldGenre = oldGenreOptional.get();
            oldGenre.setGenreName(genre.getGenreName());
            return genreRepository.save(oldGenre);
        } else {
            throw new UpdateEntityException("Genre not found with ID: " + idGenre);
        }
    }
}
