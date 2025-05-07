package com.example.inventorypharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "lote")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLote;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private String codigoLote;

    private LocalDate fechaVencimiento;

    private Integer cantidad;

    private LocalDate fechaIngreso;

    private Double precioUnitario; // 👈 necesario para "valor inventario" y frontend

    private String notas; // 👈 opcional: para referencias

    @ManyToOne
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal sucursal;//👈 para registrar la sucursal a la que llega el lote
}