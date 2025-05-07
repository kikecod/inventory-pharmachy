package com.example.inventorypharmacy.repository;

import com.example.inventorypharmacy.model.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecioRepository extends JpaRepository<Precio, Long> {

    @Query("""
        SELECT p FROM Precio p
        WHERE p.producto.idProducto = :idProducto
        AND p.fecha_vigencia <= CURRENT_DATE
        ORDER BY p.fecha_vigencia DESC
    """)
    List<Precio> findPrecioActualByProductoId(@Param("idProducto") Long idProducto);
}
