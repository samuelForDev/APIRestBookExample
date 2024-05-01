package com.example.apirestbooksexample.repository;

import com.example.apirestbooksexample.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
