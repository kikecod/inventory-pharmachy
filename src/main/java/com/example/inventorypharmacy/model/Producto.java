package com.example.inventorypharmacy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;

    private String descripcion;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "id_unidad")
    private Unidad unidad;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}