package com.devlucasmart.book.service;

import com.devlucasmart.book.dto.book.BookRequest;
import com.devlucasmart.book.dto.book.BookResponse;
import com.devlucasmart.book.repository.BookRepository;
import com.devlucasmart.book.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final CategoriaRepository categoriaRepository;

    public List<BookResponse> findAll(){
        return null;
    }

    public BookResponse findById(Integer id){
        return null;
    }

    public BookResponse save(BookRequest request){
        return null;
    }
    public BookResponse update(Integer id, BookRequest request){
        return null;
    }

    public void delete(Integer id){
    }
}
