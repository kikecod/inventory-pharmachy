package com.example.inventorypharmacy.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSucursalId implements Serializable {
    private Long producto;
    private Long sucursal;
}