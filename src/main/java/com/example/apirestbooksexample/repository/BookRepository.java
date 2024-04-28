package com.example.apirestbooksexample.repository;

import com.example.apirestbooksexample.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b WHERE b.author.idAuthor = :idAuthor")
    List<Book> findBooksByAuthor(
            @Param("idAuthor") UUID idAuthor);

    @Query("select b from Book b where b.genre.idGenre = :idGenre")
    List<Book> findBooksByGenre(
            @Param("idGenre") UUID idGenre);


}
