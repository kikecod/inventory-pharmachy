package com.example.inventorypharmacy.controller;



import com.example.inventorypharmacy.dto.RolDTO;
import com.example.inventorypharmacy.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping
    public RolDTO crearRol(@RequestBody RolDTO rolDto) {
        return rolService.crearRol(rolDto);
    }

    @GetMapping("/{id}")
    public RolDTO obtenerRol(@PathVariable Long id) {
        return rolService.obtenerRolPorId(id);
    }

    @GetMapping
    public List<RolDTO> listarRoles() {
        return rolService.listarRoles();
    }

    @PutMapping("/{id}")
    public RolDTO actualizarRol(@PathVariable Long id, @RequestBody RolDTO rolDto) {
        return rolService.actualizarRol(id, rolDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
    }
}
