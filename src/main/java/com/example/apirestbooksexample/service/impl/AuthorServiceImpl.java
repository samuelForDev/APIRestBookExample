package com.example.apirestbooksexample.service.impl;

import com.example.apirestbooksexample.entity.Author;
import com.example.apirestbooksexample.exception.CreateEntityException;
import com.example.apirestbooksexample.exception.UpdateEntityException;
import com.example.apirestbooksexample.repository.AuthorRepository;
import com.example.apirestbooksexample.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }

    @Override
    public Author findAuthorById(UUID idAuthor) {
        Optional<Author> author = authorRepository.findById(idAuthor);
        return author.orElse(null);
    }

    @Override
    public Author saveAuthor(Author author) throws CreateEntityException {
        try {
            Author newAuthor = Author.builder()
                    .idAuthor(UUID.randomUUID())
                    .authorName(author.getAuthorName())
                    .build();
            return authorRepository.save(newAuthor);
        } catch (Exception e) {
            throw new CreateEntityException("Failed to create author: " + e.getMessage());
        }
    }

    @Override
    public Author updateAuthor(UUID idAuthor, Author author) throws UpdateEntityException {
        Optional<Author> oldAuthorOptional = authorRepository.findById(idAuthor);
        if (oldAuthorOptional.isPresent()) {
            Author oldAuthor = oldAuthorOptional.get();
            oldAuthor.setAuthorName(author.getAuthorName());
            return authorRepository.save(oldAuthor);
        } else {
            throw new UpdateEntityException("Author not found with ID: " + idAuthor);
        }
    }

}
