package com.example.inventorypharmacy.dto;

import com.example.inventorypharmacy.model.Producto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor



public class LoteDTO {
    private Long idLote;
    private Producto producto;
    private String codigo_lote;
    private LocalDate fecha_vencimiento;
    private Integer cantidad;
    private LocalDate fecha_ingreso;
}
