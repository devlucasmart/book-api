package com.devlucasmart.book.service;

import com.devlucasmart.book.comum.exception.ValidacaoException;
import com.devlucasmart.book.dto.categoria.CategoriaRequest;
import com.devlucasmart.book.dto.categoria.CategoriaResponse;
import com.devlucasmart.book.mappers.CategoriaMapper;
import com.devlucasmart.book.model.CategoriaModel;
import com.devlucasmart.book.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;
    private CategoriaMapper mapper = Mappers.getMapper(CategoriaMapper.class);


    public List<CategoriaResponse> findAll(){
        var categorias = repository.findAll();
        return mapper.toListResponse(categorias);
    }

    public CategoriaResponse findById(Integer id){
        var categoria = getById(id);
        return mapper.toResponse(categoria);
    }

    public CategoriaResponse save(CategoriaRequest request){
        return null;
    }
    public CategoriaResponse update(Integer id, CategoriaRequest request){
        return null;
    }

    //TODO Implementar validação se categoria estar vazia antes de deletar
    public void delete(Integer id){
        var categoria = getById(id);
        repository.deleteById(categoria.getId());
    }

    private CategoriaModel getById(Integer id){
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Categoria não Encontrada!!"));
    }
}
