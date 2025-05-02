package com.example.inventorypharmacy.service;

import com.example.inventorypharmacy.dto.*;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}