package com.example.inventorypharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteRequestDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String formato; // "pdf" o "csv"
}
