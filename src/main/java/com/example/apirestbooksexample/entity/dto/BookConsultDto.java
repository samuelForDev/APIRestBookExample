package com.example.apirestbooksexample.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookConsultDto {

    private UUID idBook;
    private String bookName;
    private Long isnb;
    private String pages;
    private String author;
    private String genre;

}
