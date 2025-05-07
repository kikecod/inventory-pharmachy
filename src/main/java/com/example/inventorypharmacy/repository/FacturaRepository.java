package com.example.inventorypharmacy.repository;

// FacturaRepository.java


import com.example.inventorypharmacy.model.Factura;
import com.example.inventorypharmacy.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Optional<Factura> findByVenta(Venta venta);
}
