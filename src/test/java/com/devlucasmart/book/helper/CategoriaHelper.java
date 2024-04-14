package com.devlucasmart.book.helper;

import com.devlucasmart.book.dto.categoria.CategoriaRequest;
import com.devlucasmart.book.model.CategoriaModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CategoriaHelper {
    public static CategoriaModel umaCategoria() {
        return CategoriaModel.builder()
                .id(1)
                .nome("ação")
                .dataCriacao(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static CategoriaModel duasCategoria() {
        return CategoriaModel.builder()
                .id(2)
                .nome("Romance")
                .dataCriacao(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static CategoriaModel tresCategoria() {
        return CategoriaModel.builder()
                .id(3)
                .nome("Terror")
                .dataCriacao(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static CategoriaRequest umaCategoriaRequest() {
        return CategoriaRequest.builder()
                .nome("Biologia")
                .dataCriacao(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static CategoriaModel umaCategoriaNova(CategoriaRequest request) {
        return CategoriaModel.builder()
                .nome(request.getNome())
                .dataCriacao(request.getDataCriacao())
                .build();
    }

    public static List<CategoriaModel> umaListaCategoria(){
        var listCategoria = new ArrayList<CategoriaModel>();
        listCategoria.add(umaCategoria());
        listCategoria.add(duasCategoria());
        listCategoria.add(tresCategoria());

        return listCategoria;
    }
}
