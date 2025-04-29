package com.example.inventorypharmacy.service;

// VentaService.java


import com.example.inventorypharmacy.dto.VentaDTO;
import java.util.List;

public interface VentaService {
    List<VentaDTO> listarTodas();
    VentaDTO obtenerPorId(Long id);
    VentaDTO guardar(VentaDTO ventaDTO);
    void eliminar(Long id);
}
