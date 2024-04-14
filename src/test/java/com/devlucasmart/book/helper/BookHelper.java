package com.devlucasmart.book.helper;

import com.devlucasmart.book.dto.book.BookRequest;
import com.devlucasmart.book.dto.book.BookResponse;
import com.devlucasmart.book.model.BookModel;
import com.devlucasmart.book.model.CategoriaModel;
import org.springframework.beans.BeanUtils;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookHelper {
    public static BookModel umBook() {
        return BookModel.builder()
                .id(1)
                .nome("UM programador 1")
                .autor("Lucas Martins Arruda")
                .categoria(umaCategoria())
                .dataLancamento(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static BookModel umBookNovo(BookRequest request) {
        return BookModel.builder()
                .nome(request.getNome())
                .autor(request.getAutor())
                .categoria(request.getCategoria())
                .dataLancamento(request.getDataLancamento())
                .build();
    }

    public static BookRequest umBookRequest() {
        return BookRequest.builder()
                .nome("UM programador 1")
                .autor("Lucas Martins Arruda")
                .categoria(umaCategoria())
                .dataLancamento(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static BookModel doisBook() {
        return BookModel.builder()
                .id(2)
                .nome("UM programador 2")
                .autor("Lucas Martins Arruda")
                .categoria(umaCategoria())
                .dataLancamento(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static BookModel tresBook() {
        return BookModel.builder()
                .id(3)
                .nome("UM programador 3")
                .autor("Lucas Martins Arruda")
                .categoria(umaCategoria())
                .dataLancamento(LocalDate.of(2023, 1, 1))
                .build();
    }

    public static List<BookModel> umaListaBook(){
        var listBook = new ArrayList<BookModel>();
        listBook.add(umBook());
        listBook.add(doisBook());
        listBook.add(tresBook());

        return listBook;
    }

    public static List<BookResponse> umaListaBookResponse(){
        var umResponse = new BookResponse();
        BeanUtils.copyProperties(umBook(), umResponse);
        var doisResponse = new BookResponse();
        BeanUtils.copyProperties(doisBook(), doisResponse);
        var tresResponse = new BookResponse();
        BeanUtils.copyProperties(tresBook(), tresResponse);

        var listBookResponse = new ArrayList<BookResponse>();
        listBookResponse.add(umResponse);
        listBookResponse.add(doisResponse);
        listBookResponse.add(tresResponse);

        return listBookResponse;
    }

    public static CategoriaModel umaCategoria() {
        return CategoriaModel.builder()
                .id(1)
                .nome("Ação")
                .dataCriacao(LocalDate.of(2023, 1, 1))
                .build();
    }
}
