package com.example.inventorypharmacy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto_sucursal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSucursal {

    @EmbeddedId
    private ProductoSucursalId id;

    @ManyToOne
    @MapsId("producto")
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @MapsId("sucursal")
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;
}