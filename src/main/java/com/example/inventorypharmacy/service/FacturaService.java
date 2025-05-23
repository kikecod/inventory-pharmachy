package com.example.inventorypharmacy.service;

// FacturaService.j

import com.example.inventorypharmacy.dto.FacturaDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface FacturaService {
    List<FacturaDTO> listarTodas();
    FacturaDTO obtenerPorId(Long id);
    FacturaDTO guardar(FacturaDTO dto);
    void eliminar(Long id);
    ByteArrayInputStream generarPdf(Long id);
    ByteArrayInputStream generarFacturaPdfPorVenta(Long idVenta);
}