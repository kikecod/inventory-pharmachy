package com.example.inventorypharmacy.service;

import com.example.inventorypharmacy.dto.LoteRequestDTO;
import com.example.inventorypharmacy.dto.LoteResponseDTO;

import java.util.List;
import java.util.Map;

public interface LoteService {
    List<LoteResponseDTO> listarLotes();
    LoteResponseDTO registrarLote(LoteRequestDTO dto);
    LoteResponseDTO actualizarLote(Long idLote, LoteRequestDTO dto);
    void eliminarLote(Long idLote);
    List<Map<String, Object>> obtenerStockPorSucursal(Long idSucursal);
}
