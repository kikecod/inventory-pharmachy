package com.example.inventorypharmacy.dto;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private Long idVenta;
    private Long idProducto;
    private Integer cantidad;
    private Double subtotal;
}