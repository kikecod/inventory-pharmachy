package com.example.inventorypharmacy.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;


    private String fechaCreacion;


}
