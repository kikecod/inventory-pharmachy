package com.example.inventorypharmacy.repository;


import com.example.inventorypharmacy.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
