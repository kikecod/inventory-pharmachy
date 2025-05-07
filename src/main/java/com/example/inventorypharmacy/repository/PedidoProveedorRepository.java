package com.example.inventorypharmacy.repository;

import com.example.inventorypharmacy.model.PedidoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoProveedorRepository extends JpaRepository<PedidoProveedor, Long> {
    List<PedidoProveedor> findByProveedorIdProveedor(Long idProveedor);
}
