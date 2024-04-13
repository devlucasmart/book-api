package com.devlucasmart.book.service;

import com.devlucasmart.book.comum.exception.ValidacaoException;
import com.devlucasmart.book.dto.book.BookRequest;
import com.devlucasmart.book.dto.book.BookResponse;
import com.devlucasmart.book.mappers.BookMapper;
import com.devlucasmart.book.model.BookModel;
import com.devlucasmart.book.repository.BookRepository;
import com.devlucasmart.book.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final CategoriaRepository categoriaRepository;
    private BookMapper mapper = Mappers.getMapper(BookMapper.class);


    public List<BookResponse> findAll() {
        var books = repository.findAll();
        return mapper.toListResponse(books);
    }

    public BookResponse findById(Integer id) {
        var book = getById(id);
        return mapper.toResponse(book);
    }

    public BookResponse save(BookRequest request) {
        return null;
    }

    public BookResponse update(Integer id, BookRequest request) {
        return null;
    }

    public void delete(Integer id) {
        var book = getById(id);
        repository.deleteById(book.getId());
    }

    private BookModel getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Book não Encontrado!!"));
    }
}
