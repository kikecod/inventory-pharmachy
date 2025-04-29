package com.example.inventorypharmacy.dto;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
}
