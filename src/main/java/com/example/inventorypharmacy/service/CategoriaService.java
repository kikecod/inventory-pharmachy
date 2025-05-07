package com.example.inventorypharmacy.service;

import com.example.inventorypharmacy.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> obtenerTodasLasCategorias();
    CategoriaDTO obtenerCategoriaPorId(Long id);
}
