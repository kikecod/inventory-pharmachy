package com.example.inventorypharmacy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoProveedorDTO {
    private Long idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}