package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.AuthResponse;
import com.example.inventorypharmacy.dto.LoginRequest;
import com.example.inventorypharmacy.model.Usuario;
import com.example.inventorypharmacy.repository.UsuarioRepository;
import com.example.inventorypharmacy.security.JwtUtil;
import com.example.inventorypharmacy.service.AuthService;
import com.example.inventorypharmacy.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().getNombre());
        return new AuthResponse(token);
    }

    public AuthResponse register(Usuario nuevoUsuario) {
        nuevoUsuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
        usuarioRepository.save(nuevoUsuario);
        String token = jwtUtil.generateToken(nuevoUsuario.getEmail(), nuevoUsuario.getRol().getNombre());
        return new AuthResponse(token);
    }
}
