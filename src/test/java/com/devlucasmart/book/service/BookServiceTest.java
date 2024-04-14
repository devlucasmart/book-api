package com.devlucasmart.book.service;

import com.devlucasmart.book.comum.exception.ValidacaoException;
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

import java.time.LocalDate;
import java.util.Optional;

import static com.devlucasmart.book.helper.BookHelper.umBook;
import static com.devlucasmart.book.helper.BookHelper.umBookNovo;
import static com.devlucasmart.book.helper.BookHelper.umBookRequest;
import static com.devlucasmart.book.helper.BookHelper.umaListaBook;
import static com.devlucasmart.book.helper.CategoriaHelper.umaCategoria;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
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
    private BookService service;

    @Test
    public void findAll_deveRetornarTodosBooks() {
        var listBook = umaListaBook();

        doReturn(listBook).when(repository).findAll();
        var result = service.findAll();

        verify(repository).findAll();
        verify(mapper).toListResponse(listBook);

        assertTrue(result.stream().allMatch(BookResponse.class::isInstance));
    }

    @Test
    public void findById_deveRetornarBookPeloId() {
        var book = umBook();
        var response = new BookResponse();
        doReturn(Optional.of(book)).when(repository).findById(1);

        BeanUtils.copyProperties(book, response);
        service.findById(1);

        verify(repository).findById(1);
        verify(mapper).toResponse(book);
    }

    @Test
    public void findById_deveRetornarExceptionBookNaoExistente() {
        assertThatThrownBy(() -> service.findById(4))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Book não Encontrado!!");

        verify(repository).findById(4);
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void save_deveSalvarBook() {
        var request = umBookRequest();
        var bookNovo = umBookNovo(request);

        doReturn(Optional.of(umaCategoria())).when(categoriaRepository).findById(1);
        doReturn(bookNovo).when(mapper).toDomain(request);
        doReturn(bookNovo).when(repository).save(bookNovo);

        service.save(request);

        verify(repository).save(bookNovo);
        verify(mapper).toDomain(request);
        verify(mapper).toResponse(bookNovo);
    }

    @Test
    public void save_deveRetornarExceptionQuandoCategoriaNaoExistente() {
        assertThatThrownBy(() -> service.save(umBookRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Categoria Não encontrada!!");

        verify(categoriaRepository).findById(1);
        verify(repository, never()).findById(any());
        verify(repository, never()).save(any());
        verify(mapper, never()).toDomain(any());
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void update_deveAtualizarBook() {
        var request = umBookRequest();
        var categoria = umaCategoria();
        var bookExistente = umBook();
        var bookAtualizado = umBookNovo(request);

        doReturn(Optional.of(umaCategoria())).when(categoriaRepository).findById(1);
        doReturn(Optional.of(bookExistente)).when(repository).findById(1);
        doReturn(bookAtualizado).when(mapper).toDomain(request);
        doReturn(bookAtualizado).when(repository).save(bookAtualizado);

        assertThat(bookExistente)
                .extracting("id", "nome", "autor", "categoria", "dataLancamento")
                .containsExactly(1, "UM programador 1", "Lucas Martins Arruda", categoria, LocalDate.of(2023, 1, 1));

        service.update(1, request);

        assertThat(bookAtualizado)
                .extracting("id", "nome", "autor", "categoria", "dataLancamento")
                .containsExactly(1, "UM programador 4", "Pedro Teste", categoria, LocalDate.of(2023, 1, 1));

        verify(repository).save(bookAtualizado);
        verify(mapper).toDomain(request);
        verify(mapper).toResponse(bookAtualizado);
    }

    @Test
    public void update_deveRetornarExceptionQuandoBookNaoExistente() {
        doReturn(Optional.of(umaCategoria())).when(categoriaRepository).findById(1);
        doReturn(Optional.empty()).when(repository).findById(1);

        assertThatThrownBy(() -> service.update(1, umBookRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Book não Encontrado!!");

        verify(categoriaRepository).findById(1);
        verify(repository).findById(1);
        verify(repository, never()).save(any());
        verify(mapper, never()).toDomain(any());
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void update_deveRetornarExceptionQuandoCategoriaNaoExistente() {
        doReturn(Optional.empty()).when(categoriaRepository).findById(1);

        assertThatThrownBy(() -> service.update(1, umBookRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Categoria Não encontrada!!");

        verify(categoriaRepository).findById(1);
        verify(repository, never()).findById(1);
        verify(repository, never()).save(any());
        verify(mapper, never()).toDomain(any());
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void delete_deveDeletarBook() {
        doReturn(Optional.of(umBook())).when(repository).findById(1);

        service.delete(1);

        verify(repository).deleteById(1);
        verify(repository).findById(1);
    }
}
