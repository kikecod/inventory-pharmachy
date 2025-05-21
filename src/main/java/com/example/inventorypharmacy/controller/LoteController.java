package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.LoteRequestDTO;
import com.example.inventorypharmacy.dto.LoteResponseDTO;
import com.example.inventorypharmacy.service.LoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "https://inventory-pharmacy.vercel.app") // ajusta si usas otro frontend
public class LoteController {

    @Autowired
    private LoteService loteService;

    // ✅ 1. GET /api/inventario/lotes
    @GetMapping("/lotes")
    public ResponseEntity<List<LoteResponseDTO>> listarLotes() {
        return ResponseEntity.ok(loteService.listarLotes());
    }

    // ✅ 2. POST /api/inventario/lotes
    @PostMapping("/lotes")
    public ResponseEntity<Map<String, Object>> registrarLote(@RequestBody LoteRequestDTO dto) {
        LoteResponseDTO nuevo = loteService.registrarLote(dto);
        return ResponseEntity.ok(Map.of(
                "idLote", nuevo.getIdLote(),
                "message", "Lote registrado exitosamente"
        ));
    }

    // ✅ 3. PUT /api/inventario/lotes/{id}
    @PutMapping("/lotes/{id}")
    public ResponseEntity<Map<String, String>> actualizarLote(@PathVariable Long id, @RequestBody LoteRequestDTO dto) {
        loteService.actualizarLote(id, dto);
        return ResponseEntity.ok(Map.of("message", "Lote actualizado correctamente"));
    }

    // ✅ 4. DELETE /api/inventario/lotes/{id}
    @DeleteMapping("/lotes/{id}")
    public ResponseEntity<Map<String, String>> eliminarLote(@PathVariable Long id) {
        loteService.eliminarLote(id);
        return ResponseEntity.ok(Map.of("message", "Lote eliminado correctamente"));
    }

    // ✅ 5. GET /api/inventario/stock?sucursal=1
    @GetMapping("/stock")
    public ResponseEntity<List<Map<String, Object>>> stockPorSucursal(@RequestParam Long sucursal) {
        return ResponseEntity.ok(loteService.obtenerStockPorSucursal(sucursal));
    }
}