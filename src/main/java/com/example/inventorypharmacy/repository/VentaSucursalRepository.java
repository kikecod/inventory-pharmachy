package com.example.inventorypharmacy.repository;

import com.example.inventorypharmacy.model.Venta;
import com.example.inventorypharmacy.model.VentaSucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentaSucursalRepository extends JpaRepository<VentaSucursal, Long> {
    Optional<VentaSucursal> findByVenta(Venta venta);
}
