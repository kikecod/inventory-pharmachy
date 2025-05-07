package com.example.inventorypharmacy.repository;

import com.example.inventorypharmacy.model.DetallePedidoProveedor;
import com.example.inventorypharmacy.model.DetallePedidoProveedorId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoProveedorRepository extends JpaRepository<DetallePedidoProveedor, DetallePedidoProveedorId> {
    List<DetallePedidoProveedor> findByPedidoIdPedido(Long idPedido);
}