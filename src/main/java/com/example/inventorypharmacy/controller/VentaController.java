package com.example.inventorypharmacy.controller;


import com.example.inventorypharmacy.dto.VentaDTO;
import com.example.inventorypharmacy.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
