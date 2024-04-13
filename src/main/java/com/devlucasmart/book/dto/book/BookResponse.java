package com.devlucasmart.book.dto.book;

import com.devlucasmart.book.model.CategoriaModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookResponse {
    private Integer id;
    private String nome;
    private String autor;
    private LocalDateTime dataLancamento;
    private CategoriaModel categoria;
}
