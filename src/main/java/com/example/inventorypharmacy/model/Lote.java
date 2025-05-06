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
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private String codigo_lote;

    private LocalDate fecha_vencimiento;

    private Integer cantidad;

    private LocalDate fecha_ingreso;
}
