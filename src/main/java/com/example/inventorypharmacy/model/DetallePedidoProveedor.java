package com.example.inventorypharmacy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_pedido_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DetallePedidoProveedorId.class)
public class DetallePedidoProveedor {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoProveedor pedido;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private Integer cantidad;

    private Double precioUnitario;

    private Double subtotal;
}