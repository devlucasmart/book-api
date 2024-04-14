package com.devlucasmart.book.service;

import com.devlucasmart.book.dto.book.BookResponse;
import com.devlucasmart.book.mappers.BookMapper;
import com.devlucasmart.book.repository.BookRepository;
import com.devlucasmart.book.repository.CategoriaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.devlucasmart.book.helper.BookHelper.umBook;
import static com.devlucasmart.book.helper.BookHelper.umaListaBook;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class BookServiceTest {
    @MockBean
    private BookMapper mapper;
    @MockBean
    private BookRepository repository;
    @MockBean
    private CategoriaRepository categoriaRepository;
    @Autowired
    private BookService bookService;

    @Test
    public void findAll_deveRetornarTodosBooks() {
        var listBook = umaListaBook();

        doReturn(listBook).when(repository).findAll();

        var result = bookService.findAll();

        verify(repository).findAll();
        verify(mapper).toListResponse(listBook);

        assertTrue(result.stream().allMatch(BookResponse.class::isInstance));
    }

    @Test
    public void findById_deveRetornarBookPeloId(){
        var book = umBook();
        var response = new BookResponse();
        doReturn(Optional.of(book)).when(repository).findById(1);

        BeanUtils.copyProperties(book, response);
        bookService.findById(1);

        verify(repository).findById(1);
        verify(mapper).toResponse(book);
    }
}
