package com.example.inventorypharmacy.repository;

// VentaRepository.java
import com.example.inventorypharmacy.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
