package com.example.inventorypharmacy.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoProveedorId implements Serializable {
    private Long pedido;
    private Long producto;
}