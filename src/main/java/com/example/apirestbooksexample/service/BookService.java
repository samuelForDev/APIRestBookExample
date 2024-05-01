package com.example.apirestbooksexample.service;

import com.example.apirestbooksexample.entity.Book;
import com.example.apirestbooksexample.entity.dto.BookCustomConsultDto;
import com.example.apirestbooksexample.entity.dto.BookConsultDto;
import com.example.apirestbooksexample.entity.dto.BookCreateDto;
import com.example.apirestbooksexample.exception.CreateBookException;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<BookConsultDto> findAllBooks();
    Book findBookById(UUID idBook);
    Book saveBook (BookCreateDto book) throws CreateBookException;
    List<BookConsultDto> findBooksByAuthor(BookCustomConsultDto idAuthor);
    List<BookConsultDto> findBooksByGenre(BookCustomConsultDto idGenre);
    List<BookConsultDto> findBooksByAuthorAndGenre(BookCustomConsultDto book);

}