package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.VentaDTO;
import com.example.inventorypharmacy.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<VentaDTO> listarTodas() {
        return ventaService.listarTodas();
    }

    @GetMapping("/{id}")
    public VentaDTO obtener(@PathVariable Long id) {
        return ventaService.obtenerPorId(id);
    }

    @PostMapping
    public VentaDTO guardar(@RequestBody VentaDTO dto) {
        return ventaService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
    }

    @GetMapping("/reportes/venta")
    public ResponseEntity<?> obtenerReporteVentas(@RequestParam String tipo) {
        switch (tipo.toLowerCase()) {
            case "diario":
                return ResponseEntity.ok(ventaService.obtenerReporteDiario());
            case "mensual":
                return ResponseEntity.ok(ventaService.obtenerReporteMensual());
            case "anual":
                return ResponseEntity.ok(ventaService.obtenerReporteAnual());
            default:
                return ResponseEntity.badRequest()
                        .body("Tipo de reporte no válido. Use 'diario', 'mensual' o 'anual'.");
        }
    }

    @GetMapping("/reportes/stock-por-categoria")
    public ResponseEntity<?> obtenerStockPorCategoria() {
        return ResponseEntity.ok(ventaService.obtenerStockPorCategoria());
    }

    @GetMapping("dashboard/ventas-totales")
    public double obtenerTotalVentas() {
        return ventaService.obtenerTotalVentas();
    }

    @GetMapping("dashboard/productos-stock-bajo")
    public ResponseEntity<?> obtenerProductosConStockBajo(@RequestParam(defaultValue = "40") int limiteStock) {
        return ResponseEntity.ok(ventaService.obtenerProductosConStockBajo(limiteStock));
    }
}
