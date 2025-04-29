package com.example.inventorypharmacy.controller;


import com.example.inventorypharmacy.dto.UsuarioDTO;
import com.example.inventorypharmacy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public UsuarioDTO guardar(@RequestBody UsuarioDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}