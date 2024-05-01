package com.example.apirestbooksexample.repository;

import com.example.apirestbooksexample.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
}
