package com.example.apirestbooksexample.service.impl;

import com.example.apirestbooksexample.entity.Book;
import com.example.apirestbooksexample.entity.dto.BookCustomConsultDto;
import com.example.apirestbooksexample.entity.dto.BookConsultDto;
import com.example.apirestbooksexample.entity.dto.BookCreateDto;
import com.example.apirestbooksexample.exception.CreateBookException;
import com.example.apirestbooksexample.repository.BookRepository;
import com.example.apirestbooksexample.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    private Random random = new Random();

    private final BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookConsultDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookConsultDto> booksDtos = books.stream()
                .map(this::mapToBookConsultDto)
                .collect(Collectors.toList());
        return booksDtos;
    }

    @Override
    public Book findBookById(UUID idBook) {
        Optional<Book> bookOptional = bookRepository.findById(idBook);
        return bookOptional.orElse(null);
    }

    @Override
    public Book saveBook(BookCreateDto bookData) throws CreateBookException {
        try {
            if (validateDataToCreate(bookData)) {
                Book newBook = mapToBookEntity(bookData);
                return bookRepository.save(newBook);
            } else {
                throw new CreateBookException("Invalid data provided for creating the book");
            }

        } catch (Exception e) {
            throw new CreateBookException("Failed to create book: " + e.getMessage());
        }
    }

    @Override
    public List<BookConsultDto> findBooksByAuthor(BookCustomConsultDto idAuthor) {
        List<Book> books = bookRepository.findBooksByAuthor(idAuthor.getIdAuthor());
        List<BookConsultDto> booksDtos = books.stream()
                .map(this::mapToBookConsultDto)
                .collect(Collectors.toList());
        return booksDtos;
    }

    @Override
    public List<BookConsultDto> findBooksByGenre(BookCustomConsultDto idGenre) {
        List<Book> books = bookRepository.findBooksByGenre(idGenre.getIdGenre());
        List<BookConsultDto> booksDtos = books.stream()
                .map(this::mapToBookConsultDto)
                .collect(Collectors.toList());
        return booksDtos;
    }

    private boolean validateDataToCreate(BookCreateDto bookData) {
        return Stream.of(
                bookData.getBookName() != null && !bookData.getBookName().isEmpty(),
                bookData.getPages() != null && !bookData.getPages().isEmpty(),
                bookData.getAuthor() != null,
                bookData.getGenre() != null
        ).allMatch(Boolean::booleanValue);
    }

    private Book mapToBookEntity(BookCreateDto bookData) {
        return Book.builder()
                .idBook(UUID.randomUUID())
                .bookName(bookData.getBookName())
                .isbn(generateISBN())
                .pages(bookData.getPages())
                .author(bookData.getAuthor())
                .genre(bookData.getGenre())
                .build();
    }

    private Long generateISBN() {
        Long randomNumber = Math.abs(random.nextLong() % 1000000000000L);
        return randomNumber * 10 + random.nextInt(10);
    }

    private BookConsultDto mapToBookConsultDto(Book book) {
        return BookConsultDto.builder()
                .idBook(book.getIdBook())
                .bookName(book.getBookName())
                .pages(book.getPages())
                .author(book.getAuthor().getAuthorName())
                .genre(book.getGenre().getGenreName())
                .build();
    }


}
