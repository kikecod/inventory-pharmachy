package com.example.inventorypharmacy.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Integer stock;
    private Long idUnidad;
    private Long idProveedor;
    private Long idCategoria;
}