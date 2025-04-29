package com.example.inventorypharmacy.service;


import com.example.inventorypharmacy.dto.DetalleVentaDTO;

import java.util.List;

public interface DetalleVentaService {
    List<DetalleVentaDTO> listarPorVenta(Long idVenta);
    DetalleVentaDTO guardar(DetalleVentaDTO dto);
    void eliminar(Long idVenta, Long idProducto);
}
