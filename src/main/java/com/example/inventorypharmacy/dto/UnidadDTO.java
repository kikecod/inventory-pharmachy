package com.example.inventorypharmacy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDTO {
    private Long idUnidad;
    private String descripcion;
    private Integer unipcaja;
}