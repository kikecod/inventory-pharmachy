package com.example.inventorypharmacy.dto;

import com.example.inventorypharmacy.model.Producto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor



public class SucursalDTO {
    private Long idSucursal;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaCreacion;
}