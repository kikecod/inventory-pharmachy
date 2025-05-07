package com.example.inventorypharmacy.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProveedorRequestDTO {
    private Long idProveedor;
    private Long idUsuario;
    private Double total;
    private List<DetallePedidoProveedorDTO> detalle;
}