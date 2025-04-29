package com.example.inventorypharmacy.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DetalleVentaId.class)
public class DetalleVenta {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private Integer cantidad;

    private Double subtotal;
}
