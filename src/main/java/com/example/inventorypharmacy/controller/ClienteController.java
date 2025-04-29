package com.example.inventorypharmacy.controller;



import com.example.inventorypharmacy.dto.ClienteDTO;
import com.example.inventorypharmacy.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<ClienteDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public ClienteDTO guardar(@RequestBody ClienteDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping("/{id}")
    public ClienteDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
