package com.example.inventorypharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "precio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Precio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrecio;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    LocalDate fecha_vigencia;

    Double precio_unitario;
}
