package com.example.inventorypharmacy.repository;



import com.example.inventorypharmacy.model.DetalleVenta;
import com.example.inventorypharmacy.model.DetalleVentaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaId> {
    List<DetalleVenta> findByVentaIdVenta(Long idVenta);
}
