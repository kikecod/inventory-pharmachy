package com.example.inventorypharmacy.repository;

import com.example.inventorypharmacy.model.ProductoSucursal;
import com.example.inventorypharmacy.model.Sucursal;
import com.example.inventorypharmacy.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoSucursalRepository extends JpaRepository<ProductoSucursal, Long> {
    List<ProductoSucursal> findBySucursal_IdSucursal(Long idSucursal);
}