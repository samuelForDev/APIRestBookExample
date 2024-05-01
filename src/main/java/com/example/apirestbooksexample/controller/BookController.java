package com.example.apirestbooksexample.controller;

import com.example.apirestbooksexample.entity.Book;
import com.example.apirestbooksexample.entity.dto.BookCustomConsultDto;
import com.example.apirestbooksexample.entity.dto.BookConsultDto;
import com.example.apirestbooksexample.entity.dto.BookCreateAndUpdateDto;
import com.example.apirestbooksexample.exception.CreateEntityException;
import com.example.apirestbooksexample.exception.UpdateEntityException;
import com.example.apirestbooksexample.service.BookService;
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
@RequestMapping("api/v1/book")
public class BookController {

    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookConsultDto> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book findBookById(@PathVariable UUID id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@RequestBody BookCreateAndUpdateDto book)
            throws CreateEntityException {
        return bookService.saveBook(book);
    }

    @PostMapping("/getByAuthor")
    @ResponseStatus(HttpStatus.OK)
    public List<BookConsultDto> findBooksByAuthor(
            @RequestBody BookCustomConsultDto idAuthor) {
        return bookService.findBooksByAuthor(idAuthor);
    }

    @PostMapping("/getByGenre")
    @ResponseStatus(HttpStatus.OK)
    public List<BookConsultDto> findBooksByGenre(
            @RequestBody BookCustomConsultDto idGenre) {
        return bookService.findBooksByGenre(idGenre);
    }

    @PostMapping("/AuthorAndGenre")
    @ResponseStatus(HttpStatus.OK)
    public List<BookConsultDto> findBooksByAuthorAndGenre(
            @RequestBody BookCustomConsultDto book) {
        return bookService.findBooksByAuthorAndGenre(book);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(
            @PathVariable UUID id,
            @RequestBody BookCreateAndUpdateDto book) throws UpdateEntityException {

        return bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBook(@PathVariable UUID id) {
        return bookService.deleteBook(id);
    }

}
