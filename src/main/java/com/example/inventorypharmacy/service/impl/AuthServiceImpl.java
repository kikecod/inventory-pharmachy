package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.*;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.security.JwtService;
import com.example.inventorypharmacy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).get();
        String token = jwtService.generateToken(usuario.getEmail());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        System.out.println("Request recibido: " + request);
        System.out.println("Rol recibido: " + request.getRol());
        if (request.getNombre() == null || request.getApellido() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new RuntimeException("Todos los campos son obligatorios");
        }
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está en uso");
        }
        Rol rol = rolRepository.findByNombre(request.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(rol);
        usuario.setFechaCreacion(LocalDate.now().toString());
        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario.getEmail());
        return new AuthResponse(token);
    }
}