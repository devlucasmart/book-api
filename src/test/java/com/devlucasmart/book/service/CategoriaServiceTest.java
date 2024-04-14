package com.devlucasmart.book.service;

import com.devlucasmart.book.comum.exception.ValidacaoException;
import com.devlucasmart.book.dto.book.BookResponse;
import com.devlucasmart.book.helper.CategoriaHelper;
import com.devlucasmart.book.mappers.CategoriaMapper;
import com.devlucasmart.book.repository.CategoriaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static com.devlucasmart.book.helper.BookHelper.umaListaBook;
import static com.devlucasmart.book.helper.CategoriaHelper.umaCategoria;
import static com.devlucasmart.book.helper.CategoriaHelper.umaCategoriaNova;
import static com.devlucasmart.book.helper.CategoriaHelper.umaCategoriaRequest;
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
public class CategoriaServiceTest {
    @MockBean
    private CategoriaMapper mapper;
    @MockBean
    private CategoriaRepository repository;
    @Autowired
    private CategoriaService service;

    @Test
    public void findAll_deveRetornarTodasCategorias() {
        var listCategoria = CategoriaHelper.umaListaCategoria();

        doReturn(listCategoria).when(repository).findAll();
        var result = service.findAll();

        verify(repository).findAll();
        verify(mapper).toListResponse(listCategoria);

        assertTrue(result.stream().allMatch(BookResponse.class::isInstance));
    }

    @Test
    public void findById_deveRetornarCategoriaPeloId() {
        var categoria = umaCategoria();

        doReturn(Optional.of(categoria)).when(repository).findById(1);

        service.findById(1);

        verify(repository).findById(1);
        verify(mapper).toResponse(categoria);
    }

    @Test
    public void findById_deveRetornarExceptionCategoriaNaoExistente() {
        assertThatThrownBy(() -> service.findById(4))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Categoria não Encontrada!!");

        verify(repository).findById(4);
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void save_deveSalvarCategoria() {
        var request = umaCategoriaRequest();
        var categoriaNova = umaCategoriaNova(request);

        doReturn(categoriaNova).when(mapper).toDomain(request);
        doReturn(categoriaNova).when(repository).save(categoriaNova);

        service.save(request);

        verify(repository).save(categoriaNova);
        verify(mapper).toDomain(request);
        verify(mapper).toResponse(categoriaNova);
    }

    @Test
    public void update_deveAtualizarCategoria() {
        var request = umaCategoriaRequest();
        var categoriaExistente = umaCategoria();
        var categoriaAtualizado = umaCategoriaNova(request);

        doReturn(Optional.of(categoriaExistente)).when(repository).findById(1);
        doReturn(categoriaAtualizado).when(mapper).toDomain(request);
        doReturn(categoriaAtualizado).when(repository).save(categoriaAtualizado);

        assertThat(categoriaExistente)
                .extracting("id", "nome", "dataCriacao")
                .containsExactly(1, "ação", LocalDate.of(2023, 1, 1));

        service.update(1, request);

        assertThat(categoriaAtualizado)
                .extracting("id", "nome", "dataCriacao")
                .containsExactly(1, "Biologia", LocalDate.of(2023, 1, 1));

        verify(repository).save(categoriaAtualizado);
        verify(mapper).toDomain(request);
        verify(mapper).toResponse(categoriaAtualizado);
    }

    @Test
    public void update_deveRetornarExceptionQuandoCategoriaNaoExistente() {
        doReturn(Optional.empty()).when(repository).findById(1);

        assertThatThrownBy(() -> service.update(1, umaCategoriaRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Categoria não Encontrada!!");

        verify(repository).findById(1);
        verify(repository, never()).save(any());
        verify(mapper, never()).toDomain(any());
        verify(mapper, never()).toResponse(any());
    }

    @Test
    public void delete_deveDeletarCategoria_quandoCategoriaSemBooks() {
        var categoria = umaCategoria();
        categoria.setBooks(new ArrayList<>());

        doReturn(Optional.of(categoria)).when(repository).findById(1);

        service.delete(1);

        verify(repository).deleteById(1);
        verify(repository).findById(1);
    }

    @Test
    public void delete_deveLancarException_quandoCategoriaComBook() {
        var categoriaComBooks = umaCategoria();
        categoriaComBooks.setBooks(umaListaBook());

        doReturn(Optional.of(categoriaComBooks)).when(repository).findById(1);

        assertThatThrownBy(() -> service.delete(1))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Categoria possui books associados!!");

        verify(repository).findById(1);
        verify(repository, never()).deleteById(1);
    }
}
