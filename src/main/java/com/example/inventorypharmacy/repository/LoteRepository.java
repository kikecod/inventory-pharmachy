package com.example.inventorypharmacy.repository;

import com.example.inventorypharmacy.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    List<Lote> findBySucursalIdSucursal(Long idSucursal);
}