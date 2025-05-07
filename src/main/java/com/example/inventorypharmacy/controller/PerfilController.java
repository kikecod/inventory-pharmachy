package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.UsuarioDTO;
import com.example.inventorypharmacy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public UsuarioDTO obtenerPerfil() {
        // Obtener el email del usuario autenticado desde el contexto de seguridad
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authenticatedUser.getUsername();

        // Obtener y retornar el perfil
        return usuarioService.obtenerPerfil(email);
    }

    @PutMapping
    public UsuarioDTO actualizarPerfil(@RequestBody UsuarioDTO usuarioDTO) {
        // Obtener el email del usuario autenticado desde el contexto de seguridad
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authenticatedUser.getUsername();

        // Actualizar el perfil y retornar la respuesta
        return usuarioService.actualizarPerfil(usuarioDTO, email);
    }
}