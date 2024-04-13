package com.devlucasmart.book.service;

import com.devlucasmart.book.dto.categoria.CategoriaRequest;
import com.devlucasmart.book.dto.categoria.CategoriaResponse;
import com.devlucasmart.book.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;

    public List<CategoriaResponse> findAll(){
        return null;
    }

    public CategoriaResponse findById(Integer id){
        return null;
    }

    public CategoriaResponse save(CategoriaRequest request){
        return null;
    }
    public CategoriaResponse update(Integer id, CategoriaRequest request){
        return null;
    }

    public void delete(Integer id){
    }
}
