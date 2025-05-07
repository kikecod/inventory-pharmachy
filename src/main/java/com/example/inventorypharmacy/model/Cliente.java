package com.example.inventorypharmacy.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private Long ci;

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;

    private String direccion;
}