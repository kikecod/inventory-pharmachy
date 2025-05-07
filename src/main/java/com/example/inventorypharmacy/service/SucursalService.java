package com.example.inventorypharmacy.service;

import com.example.inventorypharmacy.dto.SucursalDTO;

import java.util.List;

public interface SucursalService {
    List<SucursalDTO> listar();
    SucursalDTO obtenerPorId(Long id);
    SucursalDTO guardar(SucursalDTO dto);
    SucursalDTO actualizar(Long id, SucursalDTO dto);
    void eliminar(Long id);
}