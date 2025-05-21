package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.ReporteRequestDTO;
import com.example.inventorypharmacy.dto.ResumenVentaDTO;
import com.example.inventorypharmacy.dto.VentaDTO;
import com.example.inventorypharmacy.dto.VentaRequestDTO;
import com.example.inventorypharmacy.model.Venta;
import com.example.inventorypharmacy.service.VentaService;
import org.springframework.core.io.Resource; // ✅ Correcto para usar con ResponseEntity<Resource>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/")
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
    public ResponseEntity<Map<String, Object>> registrarVenta(@RequestBody VentaRequestDTO dto) {
        Long idVenta = ventaService.registrarVenta(dto);
        Map<String, Object> response = Map.of("idVenta", idVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    @GetMapping("/dashboard/ventas-totales")
    public double obtenerTotalVentas() {
        return ventaService.obtenerTotalVentas();
    }

    @GetMapping("/dashboard/productos-stock-bajo")
    public ResponseEntity<?> obtenerProductosConStockBajo(@RequestParam(defaultValue = "40") int limiteStock) {
        return ResponseEntity.ok(ventaService.obtenerProductosConStockBajo(limiteStock));
    }
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Venta>> getVentasByCliente(@PathVariable Long idCliente) {
        List<Venta> ventas = ventaService.getVentasByCliente(idCliente);
        return ResponseEntity.ok(ventas);
    }

    // Endpoint para obtener ventas por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Venta>> getVentasByUsuario(@PathVariable Long idUsuario) {
        List<Venta> ventas = ventaService.getVentasByUsuario(idUsuario);
        return ResponseEntity.ok(ventas);
    }
    @GetMapping("/todas")
    public ResponseEntity<List<VentaDTO>> getAllVentas() {
        List<VentaDTO> ventas = ventaService.getAllVentas();
        return ResponseEntity.ok(ventas);
    }
    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/resumen")
    public ResponseEntity<List<ResumenVentaDTO>> obtenerResumenVentas() {
        return ResponseEntity.ok(ventaService.obtenerResumenVentas());
    }
    @PostMapping("/reporte")
    public ResponseEntity<Resource> generarReporte(@RequestBody ReporteRequestDTO request) {
        ByteArrayInputStream reporte;

        if (request.getFormato().equalsIgnoreCase("pdf")) {
            reporte = ventaService.generarReportePDF(request.getFechaInicio(), request.getFechaFin());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ventas.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(reporte));
        } else {
            reporte = ventaService.generarReporteCSV(request.getFechaInicio(), request.getFechaFin());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ventas.csv")
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new InputStreamResource(reporte));
        }
    }
}
