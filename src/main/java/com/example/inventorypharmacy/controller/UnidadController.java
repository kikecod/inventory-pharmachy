package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.UnidadDTO;
import com.example.inventorypharmacy.service.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/unidades")
public class UnidadController {

    @Autowired
    private UnidadService service;

    @GetMapping
    public List<UnidadDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public UnidadDTO guardar(@RequestBody UnidadDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping("/{id}")
    public UnidadDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public UnidadDTO actualizar(@PathVariable Long id, @RequestBody UnidadDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}