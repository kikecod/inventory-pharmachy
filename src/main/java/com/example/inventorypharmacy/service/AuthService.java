package com.example.inventorypharmacy.service;




import com.example.inventorypharmacy.dto.AuthResponse;
import com.example.inventorypharmacy.dto.LoginRequest;
import com.example.inventorypharmacy.dto.RolDTO;
import com.example.inventorypharmacy.model.Usuario;

import java.util.List;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(Usuario nuevoUsuario);
}