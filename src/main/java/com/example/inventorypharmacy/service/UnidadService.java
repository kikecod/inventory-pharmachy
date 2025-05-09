package com.example.inventorypharmacy.service;

import com.example.inventorypharmacy.dto.UnidadDTO;

import java.util.List;

public interface UnidadService {
    List<UnidadDTO> listar();
    UnidadDTO guardar(UnidadDTO dto);
    UnidadDTO actualizar(Long id, UnidadDTO dto);
    UnidadDTO obtenerPorId(Long id);
    void eliminar(Long id);
}
