package com.example.apirestbooksexample.service;

import com.example.apirestbooksexample.entity.Author;
import com.example.apirestbooksexample.exception.CreateEntityException;
import com.example.apirestbooksexample.exception.UpdateEntityException;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    List<Author> findAllAuthors();
    Author findAuthorById(UUID idAuthor);
    Author saveAuthor(Author author) throws CreateEntityException;
    Author updateAuthor(UUID idAuthor, Author author) throws UpdateEntityException;

}
