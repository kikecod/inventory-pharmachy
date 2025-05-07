package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.SucursalDTO;
import com.example.inventorypharmacy.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "http://localhost:5173/")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @GetMapping
    public List<SucursalDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public SucursalDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public SucursalDTO guardar(@RequestBody SucursalDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public SucursalDTO actualizar(@PathVariable Long id, @RequestBody SucursalDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}