package com.example.inventorypharmacy.dto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResumenVentaDTO {
    private Long idVenta;
    private String nombreCliente;
    private LocalDate fechaVenta;
    private Integer cantidadProductos;
    private String tipoVenta;
    private Double total;
}