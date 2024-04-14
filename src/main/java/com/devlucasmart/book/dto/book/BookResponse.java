package com.devlucasmart.book.dto.book;

import com.devlucasmart.book.model.CategoriaModel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponse {
    private Integer id;
    private String nome;
    private String autor;
    private LocalDate dataLancamento;
    private CategoriaModel categoria;
}
