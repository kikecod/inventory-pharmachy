package com.example.inventorypharmacy.service;

import com.example.inventorypharmacy.dto.ProveedorDTO;

import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO> listar();
    ProveedorDTO obtenerPorId(Long id);
    ProveedorDTO guardar(ProveedorDTO dto);
    ProveedorDTO actualizar(Long id, ProveedorDTO dto);
    void eliminar(Long id);
}