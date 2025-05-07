package com.example.inventorypharmacy.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaSucursalId implements Serializable {
    private Long idVenta;
    private Long idSucursal;
}