package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.UsuarioDTO;
import com.example.inventorypharmacy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // Listar todos los usuarios
    @GetMapping
    public List<UsuarioDTO> listar() {
        return service.listar();
    }

    // Crear usuario
    @PostMapping
    public UsuarioDTO guardar(@RequestBody UsuarioDTO dto) {
        return service.guardar(dto);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // Actualizar usuario (recibe nombre del rol)
    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        dto.setIdUsuario(id); // asegura que se use el ID de la URL
        return service.actualizar(id, dto);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/{id}/rol")
    public void asignarRol(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String nombreRol = body.get("rol");
        service.asignarRol(id, nombreRol);
    }

    @PutMapping("/{id}/cambiar-password")
    public void cambiarPassword(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String nueva = body.get("nuevaPassword");
        service.cambiarPassword(id, nueva);
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<?> cambiarPasswordAutenticado(@RequestBody Map<String, String> body) {
        String passwordActual = body.get("passwordActual");
        String nuevaPassword = body.get("nuevaPassword");

        // Obtener email desde el token JWT
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean resultado = service.cambiarPasswordAutenticado(email, passwordActual, nuevaPassword);

        if (resultado) {
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña actual incorrecta");
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        return service.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




    @GetMapping("/rol/{nombreRol}")
    public List<UsuarioDTO> listarPorRol(@PathVariable String nombreRol) {
        return service.listarPorRol(nombreRol);
    }
}