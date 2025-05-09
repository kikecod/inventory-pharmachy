package com.example.inventorypharmacy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSucursalRequestDTO {
    private ProductoDTO producto;
    private Long idSucursal;
}
