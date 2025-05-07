package com.example.inventorypharmacy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venta_sucursal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaSucursal {

    @EmbeddedId
    private VentaSucursalId id;

    @ManyToOne
    @MapsId("idVenta")
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @MapsId("idSucursal")
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;
}
