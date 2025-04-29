package com.example.inventorypharmacy.controller;



import com.example.inventorypharmacy.dto.LoginRequest;
import com.example.inventorypharmacy.dto.AuthResponse;
import com.example.inventorypharmacy.model.Usuario;
import com.example.inventorypharmacy.repository.UsuarioRepository;
import com.example.inventorypharmacy.security.JwtUtil;
import com.example.inventorypharmacy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService AuthService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(AuthService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Usuario nuevoUsuario) {
        return ResponseEntity.ok(AuthService.register(nuevoUsuario));
    }
}