package com.example.inventorypharmacy.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteResponseDTO {
    private Long idLote;
    private Long idProducto;
    private String nombreProducto;
    private String categoria;
    private String unidad;
    private String proveedor;
    private String codigoLote;
    private LocalDate fechaVencimiento;
    private Integer cantidad;
    private LocalDate fechaIngreso;
    private Double precioUnitario;
    private String sucursal;
    private String notas;
}
