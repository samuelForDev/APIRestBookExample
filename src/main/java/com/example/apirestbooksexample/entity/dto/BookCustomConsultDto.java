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
public class BookCustomConsultDto {
    private UUID idAuthor;
    private UUID idGenre;
}
