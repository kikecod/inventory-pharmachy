package com.example.inventorypharmacy.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteRequestDTO {
    private Long idProducto;
    private String codigoLote;
    private LocalDate fechaVencimiento;
    private Integer cantidad;
    private LocalDate fechaIngreso;
    private Double precioUnitario;
    private String notas;
    private Long idSucursal;
}