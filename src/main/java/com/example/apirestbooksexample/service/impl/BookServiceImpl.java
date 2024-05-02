package com.example.apirestbooksexample.service.impl;

import com.example.apirestbooksexample.entity.Book;
import com.example.apirestbooksexample.entity.dto.BookCustomConsultDto;
import com.example.apirestbooksexample.entity.dto.BookConsultDto;
import com.example.apirestbooksexample.entity.dto.BookCreateAndUpdateDto;
import com.example.apirestbooksexample.exception.CreateEntityException;
import com.example.apirestbooksexample.exception.UpdateEntityException;
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

    private final Random random = new Random();

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
    public Book saveBook(BookCreateAndUpdateDto bookData) throws CreateEntityException {
        try {
            if (validateDataToCreateOrUpdate(bookData)) {
                Book newBook = mapToBookEntity(bookData);
                return bookRepository.save(newBook);
            } else {
                throw new CreateEntityException("Invalid data provided for creating the book");
            }

        } catch (Exception e) {
            throw new CreateEntityException("Failed to create book: " + e.getMessage());
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

    @Override
    public List<BookConsultDto> findBooksByAuthorAndGenre(BookCustomConsultDto book) {
        List<Book> books = bookRepository.findBooksByAuthorAndGenre(
                book.getIdAuthor(),
                book.getIdGenre());
        List<BookConsultDto> booksDtos = books.stream()
                .map(this::mapToBookConsultDto)
                .collect(Collectors.toList());
        return booksDtos;
    }

    @Override
    public Book updateBook(UUID idBook, BookCreateAndUpdateDto book)
            throws UpdateEntityException {

        Optional<Book> oldBookOptional = bookRepository.findById(idBook);
        if (oldBookOptional.isPresent()) {
            Book oldBook = oldBookOptional.get();

            if(validateDataToCreateOrUpdate(book)) {
                oldBook.setBookName(book.getBookName());
                oldBook.setPages(book.getPages());
                oldBook.setAuthor(book.getAuthor());
                oldBook.setGenre(book.getGenre());
                return bookRepository.save(oldBook);
            } else {
                throw new UpdateEntityException("Invalid data provided for updating the book");
            }
        } else {
            throw new UpdateEntityException("Book not found with ID: " + idBook);
        }

    }

    @Override
    public String deleteBook(UUID idBook) {
        Optional<Book> bookOptional = bookRepository.findById(idBook);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(idBook);
            return "Delete book: " + idBook;
        }
        return "Book Not found";
    }

    private boolean validateDataToCreateOrUpdate(BookCreateAndUpdateDto bookData) {
        return Stream.of(
                bookData.getBookName() != null && !bookData.getBookName().isEmpty(),
                bookData.getPages() != null && !bookData.getPages().isEmpty(),
                bookData.getAuthor() != null,
                bookData.getGenre() != null
        ).allMatch(Boolean::booleanValue);
    }

    private Book mapToBookEntity(BookCreateAndUpdateDto bookData) {
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
                .isnb(book.getIsbn())
                .pages(book.getPages())
                .author(book.getAuthor().getAuthorName())
                .genre(book.getGenre().getGenreName())
                .build();
    }


}
