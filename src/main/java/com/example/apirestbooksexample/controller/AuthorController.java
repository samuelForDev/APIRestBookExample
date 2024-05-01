package com.example.apirestbooksexample.controller;

import com.example.apirestbooksexample.entity.Author;
import com.example.apirestbooksexample.exception.CreateEntityException;
import com.example.apirestbooksexample.exception.UpdateEntityException;
import com.example.apirestbooksexample.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("api/v1/author")
public class AuthorController {

    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Author> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author findAuthorById(@PathVariable UUID id) {
        return authorService.findAuthorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author author)
            throws CreateEntityException {
        return authorService.saveAuthor(author);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(
            @PathVariable UUID id,
            @RequestBody Author author) throws UpdateEntityException {
        return authorService.updateAuthor(id, author);
    }

}
