package com.devlucasmart.book.service;

import com.devlucasmart.book.comum.exception.ValidacaoException;
import com.devlucasmart.book.dto.categoria.CategoriaRequest;
import com.devlucasmart.book.dto.categoria.CategoriaResponse;
import com.devlucasmart.book.mappers.CategoriaMapper;
import com.devlucasmart.book.model.CategoriaModel;
import com.devlucasmart.book.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public List<CategoriaResponse> findAll(){
        var categorias = repository.findAll();
        return mapper.toListResponse(categorias);
    }

    public CategoriaResponse findById(Integer id){
        var categoria = getById(id);
        return mapper.toResponse(categoria);
    }

    public CategoriaResponse save(CategoriaRequest request){
        var categoria = mapper.toDomain(request);
        return mapper.toResponse(repository.save(categoria));
    }
    public CategoriaResponse update(Integer id, CategoriaRequest request){
        var categoriaExistente = getById(id);
        var categoria = mapper.toDomain(request);
        categoria.setId(categoriaExistente.getId());
        categoria.setDataCriacao(categoriaExistente.getDataCriacao());
        return mapper.toResponse(repository.save(categoria));
    }

    public void delete(Integer id){
        var categoria = getById(id);
        validaCategoriaSemBooks(categoria);
        repository.deleteById(categoria.getId());
    }

    private CategoriaModel getById(Integer id){
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Categoria não Encontrada!!"));
    }

    private void validaCategoriaSemBooks(CategoriaModel categoria){
        if (!categoria.getBooks().isEmpty()) {
            throw new ValidacaoException("Categoria possui books associados!!");
        }
    }
}
