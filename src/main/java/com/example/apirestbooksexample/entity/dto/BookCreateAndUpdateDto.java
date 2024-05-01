package com.example.apirestbooksexample.entity.dto;

import com.example.apirestbooksexample.entity.Author;
import com.example.apirestbooksexample.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateAndUpdateDto {

    private String bookName;
    private String pages;
    private Author author;
    private Genre genre;

}
