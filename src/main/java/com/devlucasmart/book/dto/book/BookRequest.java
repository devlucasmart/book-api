package com.devlucasmart.book.dto.book;

import com.devlucasmart.book.model.CategoriaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    private String nome;
    private String autor;
    private LocalDate dataLancamento;
    private CategoriaModel categoria;
}
