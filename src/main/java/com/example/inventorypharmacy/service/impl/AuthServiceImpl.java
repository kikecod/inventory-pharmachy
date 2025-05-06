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
        // 1. Autenticar al usuario
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. Obtener el usuario desde la DB
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Generar el token JWT
        String token = jwtService.generateToken(usuario.getEmail());

        // 4. Construir la respuesta con el token + datos del usuario
        return AuthResponse.builder()
                .token(token)
                .usuario(UsuarioResponseDTO.builder()
                        .id(usuario.getIdUsuario())
                        .nombre(usuario.getNombre())
                        .apellido(usuario.getApellido())
                        .email(usuario.getEmail())
                        .rol(usuario.getRol().getNombre())  // Asume que Rol tiene un campo "nombre"
                        .build())
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        // 1. Validar campos obligatorios
        if (request.getNombre() == null || request.getApellido() == null ||
                request.getEmail() == null || request.getPassword() == null) {
            throw new RuntimeException("Todos los campos son obligatorios");
        }

        // 2. Verificar si el email ya existe
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está en uso");
        }

        // 3. Obtener el rol desde la DB
        Rol rol = rolRepository.findByNombre(request.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // 4. Crear y guardar el nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(rol);
        usuario.setFechaCreacion(LocalDate.now().toString());

        usuarioRepository.save(usuario);

        // 5. Generar el token JWT
        String token = jwtService.generateToken(usuario.getEmail());

        // 6. Devolver respuesta con token + datos del usuario
        return AuthResponse.builder()
                .token(token)
                .usuario(UsuarioResponseDTO.builder()
                        .id(usuario.getIdUsuario())
                        .nombre(usuario.getNombre())
                        .apellido(usuario.getApellido())
                        .email(usuario.getEmail())
                        .rol(usuario.getRol().getNombre())
                        .build())
                .build();
    }
}