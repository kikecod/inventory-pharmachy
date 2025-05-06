package com.example.inventorypharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor  // Necesario para Spring y Jackson
@AllArgsConstructor  // Opcional, pero útil para constructores manuales
public class AuthResponse {
    private String token;
    private UsuarioResponseDTO usuario;  // Nuevo campo con datos del usuario
}