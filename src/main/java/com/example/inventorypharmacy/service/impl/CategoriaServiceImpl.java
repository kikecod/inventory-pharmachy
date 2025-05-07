package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.CategoriaDTO;
import com.example.inventorypharmacy.model.Categoria;
import com.example.inventorypharmacy.repository.CategoriaRepository;
import com.example.inventorypharmacy.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> new CategoriaDTO(
                        categoria.getIdCategoria(),
                        categoria.getNombre(),
                        categoria.getDescripcion()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        return new CategoriaDTO(
                categoria.getIdCategoria(),
                categoria.getNombre(),
                categoria.getDescripcion());
    }
}