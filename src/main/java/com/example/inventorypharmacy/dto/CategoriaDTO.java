package com.example.inventorypharmacy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    private Long idCategoria;
    private String nombre;
    private String descripcion;
}