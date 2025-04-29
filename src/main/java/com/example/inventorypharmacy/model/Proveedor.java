package com.example.inventorypharmacy.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    private String nombre;

    private String telefono;

    private String email;

    private String direccion;
}
