package com.example.inventorypharmacy.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Long idVenta;
    private LocalDate fecha;
    private Double total;
    private String tipoVenta;
    private Long idCliente;  // Puede ser null
    private Long idUsuario;
}
