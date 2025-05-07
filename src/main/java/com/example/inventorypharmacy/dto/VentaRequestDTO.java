package com.example.inventorypharmacy.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDTO {
    private Long idCliente;
    private Long idUsuario;
    private String tipoVenta;
    private Double total;
    private List<DetalleVentaDTO> detalle;
}