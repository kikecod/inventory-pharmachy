package com.example.inventorypharmacy.repository;

// FacturaRepository.java


import com.example.inventorypharmacy.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
