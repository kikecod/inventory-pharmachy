package com.example.inventorypharmacy.service;

// VentaService.java


import com.example.inventorypharmacy.dto.ResumenVentaDTO;
import com.example.inventorypharmacy.dto.VentaDTO;
import com.example.inventorypharmacy.model.Venta;

import java.util.List;
import java.util.Map;

public interface VentaService {
    List<VentaDTO> listarTodas();
    VentaDTO obtenerPorId(Long id);
    VentaDTO guardar(VentaDTO ventaDTO);
    void eliminar(Long id);
    List<VentaDTO> obtenerReporteDiario();
    List<VentaDTO> obtenerReporteMensual();
    List<VentaDTO> obtenerReporteAnual();
    Map<String, Integer> obtenerStockPorCategoria();
    double obtenerTotalVentas();
    List<Map<String, Object>> obtenerProductosConStockBajo(int limiteStock);
    List<Venta> getVentasByCliente(Long idCliente);
    List<Venta> getVentasByUsuario(Long idUsuario);
    List<VentaDTO> getAllVentas();
    List<ResumenVentaDTO> obtenerResumenVentas();
}
