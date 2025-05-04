package com.example.inventorypharmacy.repository;

import com.example.inventorypharmacy.model.Producto;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
        
    @Query("SELECT c.nombre AS categoria, SUM(p.stock) AS totalStock " +
            "FROM Producto p JOIN p.categoria c " +
            "GROUP BY c.nombre")
    List<Map<String, Object>> obtenerStockPorCategoria();
}
