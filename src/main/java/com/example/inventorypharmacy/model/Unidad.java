package com.example.inventorypharmacy.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidad;

    private String descripcion;

    private Integer unipcaja;
}
